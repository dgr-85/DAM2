package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	private ServerSocket serverSocket;

	public static void main(String[] args) throws IOException {
		EchoServer server = new EchoServer();
		server.start(6666);
	}

	public void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		while (true)
			new EchoClientHandler(serverSocket.accept()).start();
	}

	public void stop() throws IOException {
		serverSocket.close();
	}

	private static class EchoClientHandler extends Thread {
		private Socket clientSocket;
		private PrintWriter out;
		private BufferedReader in;

		public EchoClientHandler(Socket socket) {
			this.clientSocket = socket;
		}

		@Override
		public void run() {
			try {
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					if (".".equals(inputLine)) {
						out.println("bye");
						break;
					}
					out.println(inputLine);
				}

				in.close();
				out.close();
				clientSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
