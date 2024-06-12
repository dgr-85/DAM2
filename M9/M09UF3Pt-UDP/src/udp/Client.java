package udp;

import java.io.IOException;
import java.net.DatagramPacket;
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
		// Bàsicament s'inicialitzen els atributs especificats al principi. És com si
		// fes de constructor
		serverAddress = InetAddress.getByName(host);
		serverPort = port;
		socket = new DatagramSocket();
		consoleInterface = new ConsoleInterface();
		System.out.println("Iniciant calculadora...");
	}

	public void runClient() throws IOException {
		// Totes les tasques del client mentre l'aplicació (i el socket) estiguin oberts
		// (bucle while)
		// 1- Recull dades del client, crea paquet i socket associat i l'envia al
		// servidor
		// 2- Crea socket per a recollir paquet del servidor, comprova que no hi ha
		// errors i pinta resultat

		boolean finished = false;
		while (!finished) {
			byte[] dataToSend;

			short op1 = consoleInterface.readShort("Primer número:");
			char opSymbol = consoleInterface.readOperation("Tipus d'operació:");
			short op2 = consoleInterface.readShort("Segon número:");

			// ENVIAR
			Request request = new Request(opSymbol, op1, op2);
			// Array de bytes que aniran al paquet a enviar s'instancia directament amb
			// missatge a enviar
			dataToSend = request.getBytes();
			// Constructor habitual de DatagramPacket: requereix buffer i length del buffer
			// a llegir, i si és per a enviar, ip i port
			DatagramPacket packetToSend = new DatagramPacket(dataToSend, dataToSend.length, serverAddress, serverPort);
			socket.send(packetToSend);

			// REBRE
			// Array de bytes del paquet per a rebre s'instancia buit (però amb mida
			// necessària)
			byte[] dataToReceive = new byte[BUFFER_SIZE];
			DatagramPacket packetToReceive = new DatagramPacket(dataToReceive, dataToReceive.length);
			// DatagramSocket s'instancia amb port només quan pertany al servidor
			socket.receive(packetToReceive);
			Response response = new Response(dataToReceive);
			if (dataToReceive[0] == Response.ERROR_DIV_0) {
				System.out.println("No es pot dividir entre 0");
			} else if (dataToReceive[0] == Response.ERROR) {
				System.out.println("Error");
			} else if (dataToReceive[0] == Response.OK) {
				System.out.println("Resultat: " + response.getResult());
			} else {
				System.out.println("Situació desconeguda.");
			}

			// MÉS OPERACIONS?
			char no = "N".charAt(0);
			boolean performNewOperation = consoleInterface.readYesNo("Vols realitzar una altra operació? (S / N)", no,
					2);
			if (performNewOperation) {
				finished = true;
			}
		}
		close();
	}

	private void close() {
		// Es limita a tancar el DatagramSocket
		if (socket != null && !socket.isClosed()) {
			socket.close();
		}
	}
}