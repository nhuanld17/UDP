package UDPServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Main {
	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(5000);
			
			while (true) {
				byte[] bufferRead = new byte[50];
				DatagramPacket packet = new DatagramPacket(bufferRead, bufferRead.length);
				socket.receive(packet);
				System.out.println("Text received: "+ new String(bufferRead, 0, packet.getLength()));
				
				String returnString = new String(bufferRead, 0,packet.getLength());
				byte[] bufferWrite = returnString.getBytes();
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				
				packet = new DatagramPacket(bufferWrite, bufferWrite.length, address, port);
				socket.send(packet);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
