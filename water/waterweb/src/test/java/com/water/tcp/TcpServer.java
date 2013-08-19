package com.water.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Before;

public class TcpServer {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8000);
			int index = 1;
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("starting ......" + index);
				new TcpThreed(socket).start();
				index++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
