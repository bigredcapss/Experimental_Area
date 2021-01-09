package design.structuralmodel.decorator.logger;

import org.slf4j.Logger;

import java.io.*;

/**
 * 将项目中的日志封装成json格式再打印Demo测试
 * 注：现有日志体系采用log4j+slf4j框架搭建而成
 */
public class Test {
//    private static final Logger logger = LoggerFactory.getLogger(Test.class);
    private static final Logger logger = JsonLoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        logger.error("系统错误");


        try {
            InputStream in = new FileInputStream("");

            BufferedInputStream bis = new BufferedInputStream(in);

            bis.read();
            bis.close();

            BufferedReader br = new BufferedReader(new FileReader(""));
            br.readLine();

            BufferedReader bs = new BufferedReader(new StringReader(""));
            bs.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
