package other;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	/**
	 * MultipartFile[]:使用该类型接收多个上传的文件
	 * @param file
	 * @return
	 */
	@RequestMapping("/uploadCon")
	public String upload(@RequestParam("file") MultipartFile[] files,HttpServletRequest req){
		if(files!=null&&files.length>0){
			//作路径拼接
			for(MultipartFile file:files){
				//获取项目在服务器中的绝对路径：/盘符/XXX/SpringMVC...
				System.out.println(req.getServletContext().getRealPath("/"));
				String path =req.getServletContext().getRealPath("/")+"upload/"+file.getOriginalFilename();
				System.out.println("realPath:"+path);
				File f = new File(path);
//				if(!f.getParentFile().exists()){
//					f.getParentFile().mkdirs();
//				}
				try {
					file.transferTo(f);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "upup";
	}

}
