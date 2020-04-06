package test;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @Description:使用XPath技术快速获取所需的节点对象测试
 * @Author:BigRedCaps
 */
public class XPath_IteratorDeepNodesTest
{
    /**
     * 删除id值为2的学生标签
     */
    @Test
    public void test() throws Exception
    {
        Document doc = new SAXReader().read(new File("d:/student.xml"));

        //1.查询id为2的学生标签
        //使用xpath技术
        Element stuElem = (Element)doc.selectSingleNode("//Student[@id='1']");

        //2.删除标签
        stuElem.detach();

        //3.写出xml文件
        FileOutputStream out = new FileOutputStream("d:/student.xml");
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        XMLWriter writer = new XMLWriter(out,format);
        writer.write(doc);
        writer.close();
    }

    /**
     * XPath表达式语法测试
     */
    @Test
    public void test_XPathExpression() throws Exception
    {
        Document doc = new SAXReader().read(new File("./src/contact.xml"));

        String xpath = "";

        /**
         * 1.  	/  绝对路径   表示从xml的根位置开始或子元素（一个层次结构）
         */
        xpath = "/contactList";
        xpath = "/contactList/contact";

        /**
         * 2. //   相对路径  表示不分任何层次结构的选择元素。
         */
        xpath = "//contact/name";
        xpath = "//name";

        /**
         * 3. *    通配符   表示匹配所有元素
         */
        xpath = "/contactList/*"; //根标签contactList下的所有子标签
        xpath = "/contactList//*";//根标签contactList下的所有标签（不分层次结构）

        /**
         * 4. []    条件   表示选择什么条件下的元素
         */
        //带有id属性的contact标签
        xpath = "//contact[@id]";
        //第二个的contact标签
        xpath = "//contact[2]";
        //选择最后一个contact标签
        xpath = "//contact[last()]";

        /**
         * 5. @   属性   表示选择属性节点
         */
        xpath = "//@id"; //选择id属性节点对象，返回的是Attribute对象
        xpath = "//contact[not(@id)]";//选择不包含id属性的contact标签节点
        xpath = "//contact[@id='002']";//选择id属性值为002的contact标签
        xpath = "//contact[@id='001' and @name='eric']";//选择id属性值为001，且name属性为eric的contact标签

        /**
         *6.  text()   表示选择文本内容
         */
        //选择name标签下的文本内容，返回Text对象
        xpath = "//name/text()";
        xpath = "//contact/name[text()='张三']";//选择姓名为张三的name标签

        List<Node> list = doc.selectNodes(xpath);
        for (Node node : list) {
            System.out.println(node);
        }
    }

    /**
     * XPath模拟登录测试
     */
    @Test
    public void test_XpathSimulationLogin() throws Exception
    {
        //1.获取用户输入的用户名和密码
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("请输入用户名：");
        String name = br.readLine();

        System.out.println("请输入密码：");
        String password = br.readLine();

        //2.到“数据库”中查询是否有对应的用户
        //对应的用户：  在user.xml文件中找到一个
        //name属性值为‘用户输入’，且password属性值为‘用户输入’的user标签
        Document doc = new SAXReader().read(new File("./src/user.xml"));
        Element userElem = (Element)doc.selectSingleNode("//user[@name='" +name +"' and @password='"+password+"']");

        if(userElem!=null){
            //登录成功
            System.out.println("登录成功");
        }else{
            //登录失败
            System.out.println("登录失败");
        }
    }

    /**
     * 练习：读取联系人的所有信息
     * 按照以下格式输出：
     * 		 编号:001 姓名:张三 性别:男 年龄:18 地址：xxxx 电话： xxxx
     *       编号:002 姓名:李四 性别:女 年龄:20 地址：xxxx 电话： xxxx
     *       ......
     */
    @Test
    public void test_XPathReadHtml() throws Exception
    {
        Document doc = new SAXReader().read(new File("./src/personList.html"));
        //System.out.println(doc);

        //读取title标签
        Element titleElem = (Element)doc.selectSingleNode("//title");
        String title = titleElem.getText();
        System.out.println(title);

        //1.读取出所有tbody中的tr标签
        List<Element> list = (List<Element>)doc.selectNodes("//tbody/tr");
        //2.遍历
        for (Element elem : list) {
            //编号
            //String id = ((Element)elem.elements().get(0)).getText();
            String id = elem.selectSingleNode("td[1]").getText();
            //姓名
            String name = ((Element)elem.elements().get(1)).getText();
            //性别
            String gender = ((Element)elem.elements().get(2)).getText();
            //年龄
            String age = ((Element)elem.elements().get(3)).getText();
            //地址
            String address = ((Element)elem.elements().get(4)).getText();
            //电话
            String phone = ((Element)elem.elements().get(5)).getText();

            System.out.println("编号："+id+"\t姓名："+name+"\t性别："+
                    gender+"\t年龄："+
                    age+"\t地址："+address+
                    "\t电话："+phone);
        }
    }
}
