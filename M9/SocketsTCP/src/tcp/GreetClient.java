package tcp;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class GreetClient {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private GreetClient client;

	public void startConnection(String ip, int port) throws UnknownHostException, IOException {
		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	public String sendMessage(String msg) throws IOException {
		out.println(msg);
		String resp = in.readLine();
		return resp;
	}

	public void stopConnection() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}

	@Before
	public void setup() throws UnknownHostException, IOException {
		client = new GreetClient();
		client.startConnection("127.0.0.1", 4444);
	}

	@Test
	public void givenClient_whenServerEchosMessage_thenCorrect() throws IOException {
		String resp1 = client.sendMessage("hello");
		String resp2 = client.sendMessage("world");
		String resp3 = client.sendMessage("!");
		String resp4 = client.sendMessage(".");

		assertEquals("hello", resp1);
		assertEquals("world", resp2);
		assertEquals("!", resp3);
		assertEquals("good bye", resp4);
	}

	@After
	public void tearDown() throws IOException {
		client.stopConnection();
	}
}
