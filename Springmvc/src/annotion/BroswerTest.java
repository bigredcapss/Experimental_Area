package annotion;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

/**
 * 模拟浏览器发送请求的过程
 * @author WE
 *
 */
public class BroswerTest {
	public static void main(String[] args) {
		String url = "http://localhost:8888/SpringMVC/user/jsonhead";
		try {
			ClientHttpRequest request = new SimpleClientHttpRequestFactory().createRequest(new URI(url), HttpMethod.POST);
			//获取请求的头部
			HttpHeaders head =  request.getHeaders();
			//设置请求头内容类型和内容编码；告诉服务器发送的数据类型
			head.set("Content-Type", "application/json;charset=UTF-8");
//			request.getHeaders().set("Accept", "application/json;charset=UTF-8");
			//写出请求内容体
			String jsonData = "{\"username\":\"zhang\", \"password\":\"123\"}";
			//通过网络流中传输数据；设置请求头，发出去内容的长度
			head.set("Content-Length", jsonData.getBytes().length+"");
			//获取请求体内容
			OutputStream os = request.getBody();
			os.write(jsonData.getBytes());
			os.flush();
			os.close();
			//发送请求并得到响应
			ClientHttpResponse response = request.execute();
			System.out.println(response);
			System.out.println(response.getStatusCode());
			System.out.println(response.getHeaders());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
