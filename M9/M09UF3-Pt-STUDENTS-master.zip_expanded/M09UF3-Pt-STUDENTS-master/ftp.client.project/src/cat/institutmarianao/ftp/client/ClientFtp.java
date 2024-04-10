package cat.institutmarianao.ftp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import cat.institutmarianao.ftp.client.sync.DataChannelSynchronizer;

/**
 * @author Josep Cañellas <jcanell4@ioc.cat>
 * @author Toni Moreno <amoren86@xtec.cat>
 */
public class ClientFtp implements Runnable {
	public static final String USER = "USER ";
	public static final String PASS = "PASS ";
	public static final String PASV = "PASV";
	public static final String LIST = "LIST";
	public static final String RETR = "RETR ";
	public static final String CDUP = "CDUP";
	public static final String PORT = "PORT ";
	public static final String CWD = "CWD ";
	public static final String PWD = "PWD";
	public static final String QUIT = "QUIT";

	private DataChannelSynchronizer dataChannelSynchronizer;
	private Socket controlChannelSocket;
	private Socket dataChannelSocket = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private boolean end = false;
	private PrintWriter systemOutput;
	private boolean passiveError = false;

	public void init(OutputStream systemOutput) {
		this.systemOutput = new PrintWriter(systemOutput);
		this.dataChannelSynchronizer = new DataChannelSynchronizer();
	}

	public void connectTo(String server, int port) throws IOException {
		// TODO - initialise controlChannelSocket, in, out attributes from server and
		// port parameters
		controlChannelSocket = new Socket(server, port);
		in = new BufferedReader(new InputStreamReader(controlChannelSocket.getInputStream()));
		out = new PrintWriter(controlChannelSocket.getOutputStream());

		// TODO - start the listener thread
		Thread thread = new Thread(this);
		thread.start();
	}

	public void authenticate(String user, String pass) {
		sendUser(user);
		sendPass(pass);
	}

	public String sendPassv() {
		// TODO send corresponding command
		send(PASV);
		return "PASV sent";
	}

	public String sendList(OutputStream out, boolean closeOutput) throws IOException {
		// TODO send corresponding command
		// TODO print to out the response from the server
		send(LIST);
		return "LIST sent";
	}

	public String sendCdup() {
		// TODO send corresponding command
		send(CDUP);
		return "CDUP sent";
	}

	public String sendCwd(String down) {
		// TODO send corresponding command
		send(CWD + down);
		return "CWD sent";
	}

	public String sendPwd() {
		// TODO send corresponding command
		send(PWD);
		return "PWD sent";
	}

	public String sendQuit() {
		// TODO send corresponding command
		send(QUIT);
		return "QUIT sent";
	}

	public String sendRetr(String remote, OutputStream out, boolean closeOutput) throws IOException {
		// TODO send corresponding command
		send(RETR);
		// TODO print to out the response from the server
		processInputData(out, closeOutput);
		return "RETR sent";
	}

	public String sendUser(String user) {
		// TODO send corresponding command
		send(USER + user);
		return "USER sent";
	}

	public String sendPass(String pass) {
		// TODO send corresponding command
		send(PASS + pass);
		return "PASS sent";
	}

	public void close() {
		if (!controlChannelSocket.isClosed()) {
			sendQuit();
		}
	}

	/**
	 * Run method for the listener thread. It listen all the server responses
	 */
	@Override
	public void run() {
		try {
			listen();
		} catch (IOException ex) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {
			close(controlChannelSocket);
			close(dataChannelSocket);
		}

	}

	/*
	 * Reads and process server response lines
	 */
	private void listen() throws IOException {
		String inputLine = readLine();
		end = inputLine == null;
		while (!end) {
			processResponse(inputLine);
			inputLine = readLine();
			end = inputLine == null;
		}
	}

	/*
	 * Process a response line
	 */
	private void processResponse(String ret) throws IOException {
		if (ret.startsWith("227")) {
			/* Entering Passive Mode */
			passiveService(ret.substring(ret.indexOf('(') + 1, ret.indexOf(')')));
		}
	}

	/*
	 * Gets the ip and port from the byte string and creates a data socket
	 */
	private void passiveService(String str) throws IOException {
		byte[] adress = new byte[4];
		String[] strBytes = str.split(",");
		for (int i = 0; i < 4; i++) {
			adress[i] = (byte) Integer.parseInt(strBytes[i]);
		}
		int port = Integer.parseInt(strBytes[4]) * 256;
		port += Integer.parseInt(strBytes[5]);

		createDataSocket(adress, port);
	}

	/*
	 * creates a data socket
	 */
	private void createDataSocket(byte[] address, int port) {
		try {
			// TODO Create datatChannelSocket and uncomment the try/catch block
			dataChannelSocket = new Socket(InetAddress.getByAddress(address), port);
			passiveError = false;
		} catch (IOException ex) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
			passiveError = true;
		}
		// Data channel is ready
		dataChannelSynchronizer.setReady();
	}

	/*
	 * Receive input data from data channel
	 */
	private void processInputData(OutputStream out, boolean closeOutput) throws IOException {
		// TODO - Wait for the data channel is ready. After that, check that there is
		// no error (passiveError) and download the input data from the server
		dataChannelSynchronizer.waitToReady();
		if (!passiveError) {
			byte[] buffer = new byte[1024];
			int bytesToRead = dataChannelSocket.getInputStream().read(buffer);
			if (bytesToRead != -1) {
				out.write(buffer);
			}
			if (closeOutput) {
				out.close();
			}
		}
	}

	private void send(String message) {
		out.println(message);
		out.flush();
	}

	private String readLine() throws IOException {
		String ret = in.readLine();
		if (ret != null) {
			systemOutput.println(ret);
			systemOutput.flush();
		}
		return ret;
	}

	private void close(Socket socket) {
		// TODO close properly all socket streams before closing the socket
		try {
			if (socket != null) {
				socket.shutdownInput();
			}
			if (!socket.isOutputShutdown()) {
				socket.shutdownInput();
			}
			if (!socket.isClosed()) {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
