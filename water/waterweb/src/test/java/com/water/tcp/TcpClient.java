package com.water.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class TcpClient {
	public static void main(String[] args) throws IOException {
		Socket socket = null;
		try {
			socket = new Socket("192.168.1.125", 8000);
			new Thread(new SendM(socket)).run();
			new ReadM(socket).start();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				//socket.close();
			}
		}
	}

}

class SendM implements Runnable {
	private Socket socket = null;

	public SendM(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		OutputStream outputStream = null;
		try {
			outputStream = socket.getOutputStream();
			
			outputStream.write("tcpclient send context:中文言".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

}

class ReadM extends Thread {

	private Socket socket = null;

	public ReadM(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		InputStream inputStream = null;
		StringBuffer sb = new StringBuffer();
		try {
			boolean isavailable = false;
			while (!isavailable) {
				inputStream = socket.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char chars[] = new char[1024];
				while (bufferedReader.read(chars) > -1) {
					//System.out.println("1111");
					sb.append(chars);
					isavailable = true;
				}
				System.out.println("从服务器获取内容：" + sb.toString());

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
