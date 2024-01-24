package direccions;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExemple {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress[] addresses = new InetAddress[2];

		addresses[0] = InetAddress.getLoopbackAddress();
		addresses[1] = InetAddress.getByName("google.com");

		for (InetAddress ad : addresses) {
			if (ad.isLoopbackAddress()) {
				System.out.println(ad.getHostName() + " és loopback.");
			} else {
				System.out.println(ad.getHostName() + " no és loopback.");
			}
		}
	}
}
