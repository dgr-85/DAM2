package udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import lib.ConsoleInterface;

public class Client {
	private static final int BUFFER_SIZE = 1024;
	private InetAddress serverAddress;
	private int serverPort;
	private DatagramSocket socket;
	private ConsoleInterface consoleInterface;

	public void init(String host, int port) throws SocketException, UnknownHostException {
		serverAddress = InetAddress.getByName(host);
		serverPort = port;
		socket = new DatagramSocket();
	}

	public void runClient() throws IOException {
		byte[] arrayBytes = new byte[BUFFER_SIZE];
		consoleInterface = new ConsoleInterface();
		Request req = new Request(null);
	}

	private void close() {
		if (socket != null && !socket.isClosed()) {
			socket.close();
		}
	}
}