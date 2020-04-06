package test;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;

/**
 * @Description:Dom4j解析XML--写信息的测试类
 * @Author:BigRedCaps
 */
public class Dom4j_ParsingXmlWriteTest
{
    /**
     * 将某一个xml文件写到其它地方
     * @throws Exception
     */
    @Test
    public void test_Dom4jWriteXmlTest() throws Exception
    {
        //1.读取或创建一个Document对象
        Document doc = new SAXReader().read(new File("./src/contact.xml"));

        //2.修改Document对象内容

        //3.把修改后的Document对象写出到xml文档中
        //指定文件输出的位置
        FileOutputStream out = new FileOutputStream("d:/contact.xml");
        //1.创建写出对象
        XMLWriter writer = new XMLWriter(out);

        //2.写出对象
        writer.write(doc);
        //3.关闭流
        writer.close();

    }

    /**
     * Dom4j写入文件时的细节
     */
    @Test
    public void test_Dom4jWriteDetailTest() throws Exception{
        Document doc = new SAXReader().read(new File("./src/contact.xml"));
        //指定文件输出的位置
        FileOutputStream out = new FileOutputStream("d:/contact.xml");
        /**
         * 1.指定写出的格式
         */
        OutputFormat format = OutputFormat.createCompactFormat(); //紧凑的格式.去除空格换行.项目上线的时候
        //OutputFormat format = OutputFormat.createPrettyPrint(); //漂亮的格式.有空格和换行.开发调试的时候
        /**
         * 2.指定生成的xml文档的编码
         *    同时影响了xml文档保存时的编码  和  xml文档声明的encoding的编码（xml解析时的编码）
         *    结论： 使用该方法生成的xml文档避免中文乱码问题。
         */
        format.setEncoding("utf-8");

        //1.创建写出对象
        XMLWriter writer = new XMLWriter(out,format);

        //2.写出对象
        writer.write(doc);
        //3.关闭流
        writer.close();
    }

    /**
     * Dom4j解析xml文档：增加文档，标签 ，属性
     */
    @Test
    public void test_Dom4jWriteAddOperation() throws Exception{
        /**
         * 1.创建文档
         */
        Document doc = DocumentHelper.createDocument();
        /**
         * 2.增加标签
         */
        Element rootElem = doc.addElement("contactList");
        //doc.addElement("contactList");
        Element contactElem = rootElem.addElement("contact");
        contactElem.addElement("name");
        /**
         * 3.增加属性
         */
        contactElem.addAttribute("id", "001");
        contactElem.addAttribute("name", "eric");

        //把修改后的Document对象写出到xml文档中
        FileOutputStream out = new FileOutputStream("d:/contact.xml");
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        XMLWriter writer = new XMLWriter(out,format);
        writer.write(doc);
        writer.close();
    }

    /**
     * Dom4j解析xml文档:修改属性值，文本
     */
    @Test
    public void test_Dom4jWriteAlterOperation() throws Exception{
        Document doc = new SAXReader().read(new File("./src/contact.xml"));

        /**
         * 方案一： 修改属性值   1.得到标签对象 2.得到属性对象 3.修改属性值
         */
        //1.1  得到标签对象
		/*
		Element contactElem = doc.getRootElement().element("contact");
		//1.2 得到属性对象
		Attribute idAttr = contactElem.attribute("id");
		//1.3 修改属性值
		idAttr.setValue("003");
		*/
        /**
         * 方案二： 修改属性值
         */
        //1.1  得到标签对象
		/*
		Element contactElem = doc.getRootElement().element("contact");
		//1.2 通过增加同名属性的方法，修改属性值
		contactElem.addAttribute("id", "004");
		*/

        /**
         * 修改文本 1.得到标签对象 2.修改文本
         */
        Element nameElem = doc.getRootElement().element("contact").element("name");
        nameElem.setText("李四");

        FileOutputStream out = new FileOutputStream("d:/contact.xml");
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        XMLWriter writer = new XMLWriter(out,format);
        writer.write(doc);
        writer.close();
    }


    /**
     * Dom4j解析xml文档：删除标签，属性
     * @throws Exception
     */
    @Test
    public void test_Dom4jWriteDeleteOperation() throws Exception{
        Document doc = new SAXReader().read(new File("./src/contact.xml"));

        /**
         * 1.删除标签     1.1 得到标签对象  1.2 删除标签对象
         */
		/*
		// 1.1 得到标签对象
		Element ageElem = doc.getRootElement().element("contact").element("age");

		//1.2 删除标签对象
		ageElem.detach();
		//ageElem.getParent().remove(ageElem);
		*/
        /**
         * 2.删除属性   2.1得到属性对象  2.2 删除属性
         */
        //2.1得到属性对象
        //得到第二个contact标签
        Element contactElem = (Element)doc.getRootElement().elements().get(1);
        //2.2 得到属性对象
        Attribute idAttr = contactElem.attribute("id");
        //2.3 删除属性
        idAttr.detach();
        //idAttr.getParent().remove(idAttr);

        FileOutputStream out = new FileOutputStream("d:/contact.xml");
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        XMLWriter writer = new XMLWriter(out,format);
        writer.write(doc);
        writer.close();
    }

    /**
     * 1.生成指定xml文档
     */
    @Test
    public void test_Dom4jWriteNewXmlDoc() throws Exception{
        //1.内存创建xml文档
        Document doc = DocumentHelper.createDocument();

        //2.写入内容
        Element rootElem = doc.addElement("Students");

        //2.1 增加标签
        Element studentElem1 = rootElem.addElement("Student");
        //2.2 增加属性
        studentElem1.addAttribute("id", "1");
        //2.3 增加标签，同时设置文本
        studentElem1.addElement("name").setText("张三");
        studentElem1.addElement("gender").setText("男");
        studentElem1.addElement("grade").setText("计算机1班");
        studentElem1.addElement("address").setText("广州天河");

        //2.1 增加标签
        Element studentElem2 = rootElem.addElement("Student");
        //2.2 增加属性
        studentElem2.addAttribute("id", "2");
        //2.3 增加标签，同时设置文本
        studentElem2.addElement("name").setText("李四");
        studentElem2.addElement("gender").setText("女");
        studentElem2.addElement("grade").setText("计算机2班");
        studentElem2.addElement("address").setText("广州越秀");

        //3.内容写出到xml文件
        //3.1 输出位置
        FileOutputStream out = new FileOutputStream("d:/student.xml");
        //3.2 指定格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        // 设置编码
        format.setEncoding("utf-8");
        XMLWriter writer = new XMLWriter(out,format);
        //3.3 写出内容
        writer.write(doc);
        //3.4关闭资源
        writer.close();

    }

    /**
     * 2.修改id为2的学生姓名
     * @throws Exception
     */
    @Test
    public void test_Dom4jWriteAlterStudentName() throws Exception{
        //1.查询到id为2的学生
        Document doc = new SAXReader().read(new File("d:/student.xml"));
        //1.1 找到所有的Student标签
        Iterator<Element> it = doc.getRootElement().elementIterator("Student");
        while(it.hasNext()){
            Element stuElem = it.next();
            //1.2 查询id为id的学生标签
            if(stuElem.attributeValue("id").equals("2")){
                stuElem.element("name").setText("王丽");
                break;
            }
        }

        //3.1 输出位置
        FileOutputStream out = new FileOutputStream("d:/student.xml");
        //3.2 指定格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        // 设置编码
        format.setEncoding("utf-8");
        XMLWriter writer = new XMLWriter(out,format);
        //3.3 写出内容
        writer.write(doc);
        //3.4关闭资源
        writer.close();
    }

    /**
     * 3.删除id为2的学生
     * @throws Exception
     */
    @Test
    public void test_Dom4jWriteDeleteStudent() throws Exception{
        //1.查询到id为2的学生
        Document doc = new SAXReader().read(new File("d:/student.xml"));
        //1.1 找到所有的Student标签
        Iterator<Element> it = doc.getRootElement().elementIterator("Student");
        while(it.hasNext()){
            Element stuElem = it.next();
            //1.2 查询id为id的学生标签
            if(stuElem.attributeValue("id").equals("2")){
                //1.3 删除该学生标签
                stuElem.detach();
                break;
            }
        }

        //3.1 输出位置
        FileOutputStream out = new FileOutputStream("d:/student.xml");
        //3.2 指定格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        // 设置编码
        format.setEncoding("utf-8");
        XMLWriter writer = new XMLWriter(out,format);
        //3.3 写出内容
        writer.write(doc);
        //3.4关闭资源
        writer.close();
    }
}
