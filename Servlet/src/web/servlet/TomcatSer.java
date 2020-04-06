package web.servlet;
/**
 * 接收Http协议请求
 * 请求行
 * 请求头
 * 
 * 请求体
 * @author WE
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TomcatSer 
{
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8080);
			Socket socket = server.accept();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str =null;
			//请求行
			str=br.readLine();
			System.out.println("请求行");
			System.out.println(str);
			System.out.println("请求头");
			while((str=br.readLine())!=null){
				System.out.println(str);
			}
			str=br.readLine();
			System.out.println("请求体");
			System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
