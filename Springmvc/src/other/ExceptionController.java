package other;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {
	
	@RequestMapping("/test1")
	public String test1() throws IOException{
//测试	int a=10/0;
		//测试
		int b=0;
		if(b==0){
			throw new IOException("我错了");
		}
		return "hello";
	}

}
