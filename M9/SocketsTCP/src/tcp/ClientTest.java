package tcp;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClientTest {

	private Client client;

	@Before
	public void setup() throws UnknownHostException, IOException {
		client = new Client();
		client.startConnection("127.0.0.1", 6666);
	}

	@Test
	public void givenClient1_whenServerResponds_thenCorrect() throws IOException {
		Client client1 = new Client();
		client1.startConnection("127.0.0.1", 6666);
		String msg1 = client1.sendMessage("hello");
		String msg2 = client1.sendMessage("world");
		String terminate = client1.sendMessage(".");

		assertEquals(msg1, "hello");
		assertEquals(msg2, "world");
		assertEquals(terminate, "bye");
	}

	@Test
	public void givenClient2_whenServerResponds_thenCorrect() throws IOException {
		Client client2 = new Client();
		client2.startConnection("127.0.0.1", 6666);
		String msg1 = client2.sendMessage("hello");
		String msg2 = client2.sendMessage("world");
		String terminate = client2.sendMessage(".");

		assertEquals(msg1, "hello");
		assertEquals(msg2, "world");
		assertEquals(terminate, "bye");
	}

	@After
	public void tearDown() throws IOException {
		client.stopConnection();
	}
}
