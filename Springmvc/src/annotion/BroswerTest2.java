package annotion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class BroswerTest2 {
	public static void main(String[] args) {
		String url = "http://localhost:8888/SpringMVC/user/jsonhead";
		try {
			ClientHttpRequest request = new SimpleClientHttpRequestFactory().createRequest(new URI(url), HttpMethod.POST);
			//获取请求的头部
			HttpHeaders head =  request.getHeaders(); 
			//Accept和producers配对
			head.set("Accept","application/json");
			//发送请求并得到响应
			ClientHttpResponse response = request.execute();
			//获取响应的头部信息
			System.out.println(response.getHeaders());
			//获取交互的状态码
			System.out.println(response.getStatusCode());
			//服务器给浏览器写回时，都是写到响应体中的
			//获取内容的长度
			int length = (int) response.getHeaders().getContentLength();
			//获取响应的编码
			Charset charset = response.getHeaders().getContentType().getCharset();
			byte[] b = new byte[length];
			//获取响应的体部
			InputStream is = response.getBody();
			is.read(b);
			//将字节数组按照特定的编码格式转化为字符串
			System.out.println(new String(b,charset));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
