package com.water.tcp;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class TcpThreed extends Thread{
	private Socket socket;
	
	public TcpThreed(Socket socket){
		this.socket = socket;
	}

	@Override
	public void run() {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		StringBuffer sb = new StringBuffer();
		try {
			inputStream = this.socket.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			
			char cbuf[] = new char[1024];
			while(bufferedReader.read(cbuf) > -1){
				sb.append(cbuf);
			}
			System.out.println("获取内容："+sb.toString());
			
			outputStream = this.socket.getOutputStream();
			outputStream.write("服务器返回的内容".getBytes("UTF-8"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(inputStream != null){
					inputStream.close();
				}
				if(outputStream != null){
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
