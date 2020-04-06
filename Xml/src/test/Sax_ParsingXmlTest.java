package test;

import bean_xml.Contact;
import org.junit.Test;
import saxhandler.MyDefaultHandler1;
import saxhandler.MyDefaultHandler2;
import saxhandler.MyDefaultHandler3;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

/**
 * @Description:Sax解析Xml文档测试
 * @Author:BigRedCaps
 */
public class Sax_ParsingXmlTest
{
    /**
     * 读取contact.xml文件
     */
    @Test
    public void test_SaxReadXml() throws Exception
    {
        //1.创建SAXParser对象
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();

        //2.调用parse方法
        /**
         * 参数一： xml文档
         * 参数二： DefaultHandler的子类
         */
        parser.parse(new File("./src/contact.xml"), new MyDefaultHandler1());

    }

    /**
     * 读取contact.xml文件并完整输出
     */
    @Test
    public void test_SaxReadXmlOutput() throws Exception
    {
        //1.创建SAXParser
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        //2.读取xml文件
        MyDefaultHandler2 handler = new MyDefaultHandler2();
        parser.parse(new File("./src/contact.xml"), handler);
        String content = handler.getContent();
        System.out.println(content);
    }

    /**
     * sax解析把 xml文档封装成对象
     */
    @Test
    public void test_SaxPackageObject() throws Exception
    {
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        MyDefaultHandler3 handler = new MyDefaultHandler3();
        parser.parse(new File("./src/contact.xml"), handler);
        List<Contact> list = handler.getList();
        for (Contact contact : list) {
            System.out.println(contact);
        }
    }




}
