package UDPClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getLocalHost();
			DatagramSocket datagramSocket = new DatagramSocket();
			
			Scanner scanner = new Scanner(System.in);
			String echoString;
			
			do {
				System.out.println("Enter string to be echoed: ");
				echoString = scanner.nextLine();
				
				byte[] bufferWrite = echoString.getBytes();
				DatagramPacket packet = new DatagramPacket(bufferWrite, bufferWrite.length, address, 5000);
				datagramSocket.send(packet);
				
				byte[] bufferRead = new byte[50];
				packet = new DatagramPacket(bufferRead, bufferRead.length);
				datagramSocket.receive(packet);
				System.out.println("Text received:"+ new String(bufferRead, 0, packet.getLength()));
			} while (!echoString.equals("exit"));
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
