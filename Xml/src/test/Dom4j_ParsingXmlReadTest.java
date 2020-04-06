package test;

import bean_xml.Contact;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description:Dom4j解析XML--读信息的测试类
 * @Author:BigRedCaps
 */
public class Dom4j_ParsingXmlReadTest
{
    /**
     * 读取XML文档,获取文档对象
     */
    @Test
    public void test_Dom4jReadXml()
    {
        try {
            //1.创建一个xml解析器对象
            SAXReader reader = new SAXReader();
            //2.读取xml文档，返回Document对象
            Document doc = reader.read(new File("./src/contact.xml"));
            System.out.println(doc);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过文档对象，获取节点信息
     */
    @Test
    public void test_Dom4jGetChildNodeInfo() throws Exception{
        //1.读取xml文档，返回Document对象
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File("./src/contact.xml"));

        //2.nodeIterator: 得到当前节点下的所有子节点对象(不包含孙子以下的节点)
        Iterator<Node> it = doc.nodeIterator();
        while(it.hasNext()){//判断是否有下一个元素
            Node node = it.next();//取出元素
            String name = node.getName();//得到节点名称
            System.out.println(name);

            System.out.println(node.getClass());
            //继续取出其下面的子节点
            //只有标签节点才有子节点
            //判断当前节点是否是标签节点
            if(node instanceof Element){
                Element elem = (Element)node;
                Iterator<Node> it2 = elem.nodeIterator();
                while(it2.hasNext()){
                    Node n2 = it2.next();
                    System.out.println(n2.getName());
                }
            }
        }
    }

    /**
     * 遍历xml文档的所有节点
     * @throws Exception
     */
    @Test
    public void test_GetAllNodeInfo() throws Exception{
        //1.读取xml文档，返回Document对象
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File("./src/contact.xml"));

        //得到根标签
        Element rooElem = doc.getRootElement();
        getChildNodes(rooElem);

    }

    /**
     * 获取 传入的标签下的所有子节点
     * @param elem
     */
    private void getChildNodes(Element elem){
        System.out.println(elem.getName());
        //得到子节点
        Iterator<Node> it = elem.nodeIterator();
        while(it.hasNext()){
            Node node = it.next();

            //1.判断是否是标签节点
            if(node instanceof Element){
                Element el = (Element)node;
                //递归
                getChildNodes(el);
            }
        };
    }

    /**
     * 获取标签
     */
    @Test
    public void test_GetTagInfo() throws Exception{
        //1.读取xml文档，返回Document对象
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File("./src/contact.xml"));

        //2.得到根标签
        Element  rootElem = doc.getRootElement();
        //得到标签名称
        String name = rootElem.getName();
        System.out.println(name);

        //3.得到当前标签下指定名称的第一个子标签
		/*
		Element contactElem = rootElem.element("contact");
		System.out.println(contactElem.getName());
		*/

        //4.得到当前标签下指定名称的所有子标签
		Iterator<Element> it = rootElem.elementIterator("contact");
		while(it.hasNext()){
			Element elem = it.next();
			System.out.println(elem.getName());
		}

        //5.得到当前标签下的的所有子标签
        List<Element> list = rootElem.elements();
        //遍历List的方法
        //1)传统for循环  2）增强for循环 3）迭代器
		/*for(int i=0;i<list.size();i++){
			Element e = list.get(i);
			System.out.println(e.getName());
		}*/

	/*	for(Element e:list){
			System.out.println(e.getName());
		}*/
		/*
		Iterator<Element> it = list.iterator();
		while(it.hasNext()){
			Element elem = it.next();
			System.out.println(elem.getName());
		}*/

        //获取更深层次的标签(方法只能一层层地获取)
        Element nameElem = doc.getRootElement().
                element("contact").element("name");
        System.out.println(nameElem.getName());

    }

    /**
     * 获取属性
     */
    @Test
    public void test_GetAttributesInfo() throws Exception{
        //1.读取xml文档，返回Document对象
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File("./src/contact.xml"));

        //获取属性：（先获得属性所在的标签对象，然后才能获取属性）
        //1.得到标签对象
        Element contactElem = doc.getRootElement().element("contact");
        //2.得到属性
        //2.1  得到指定名称的属性值
		/*
		String idValue = contactElem.attributeValue("id");
		System.out.println(idValue);
		*/

        //2.2 得到指定属性名称的属性对象
		/*Attribute idAttr = contactElem.attribute("id");
		//getName： 属性名称    getValue：属性值
		System.out.println(idAttr.getName() +"=" + idAttr.getValue());*/

        //2.3 得到所有属性对象,返回LIst集合
		/*List<Attribute> list = contactElem.attributes();
		//遍历属性
		for (Attribute attr : list) {
			System.out.println(attr.getName()+"="+attr.getValue());
		}*/

        //2.4 得到所有属性对象，返回迭代器
        Iterator<Attribute> it = contactElem.attributeIterator();
        while(it.hasNext()){
            Attribute attr = it.next();
            System.out.println(attr.getName()+"="+attr.getValue());
        }

    }

    /**
     * 获取标签中的文本信息
     */
    @Test
    public void test_GetContentInTagInfo() throws Exception{
        //1.读取xml文档，返回Document对象
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File("./src/contact.xml"));

        /**
         * 注意: 空格和换行也是xml的内容
         */
        String content = doc.getRootElement().getText();
        System.out.println(content);

        //获取文本（先获取标签，再获取标签上的文本）
        Element nameELem =
                doc.getRootElement().element("contact").element("name");
        //1. 得到文本
        String text = nameELem.getText();
        System.out.println(text);

        //2. 得到指定子标签名的文本内容
        String text2 =
                doc.getRootElement().element("contact").elementText("phone");
        System.out.println(text2);

    }

    /**
     * 把xml文档信息封装到对象中
     * @throws Exception
     */
    @Test
    public void test_PackageObjectByReadXml() throws Exception{
        List<Contact> list = new ArrayList<Contact>();

        //读取xml，封装对象
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File("./src/contact.xml"));
        //读取contact标签
        Iterator<Element> it = doc.getRootElement().elementIterator("contact");
        while(it.hasNext()){
            Element elem = it.next();
            //创建Contact
            Contact contact = new Contact();
            contact.setId(elem.attributeValue("id"));
            contact.setName(elem.elementText("name"));
            contact.setAge(elem.elementText("age"));
            contact.setPhone(elem.elementText("phone"));
            contact.setEmail(elem.elementText("email"));
            contact.setQq(elem.elementText("qq"));
            list.add(contact);
        }

        for (Contact contact : list) {
            System.out.println(contact);
        }


    }

    /**
     * 综合测试
     * @throws Exception
     */
    @Test
    public void test() throws Exception{
        //读取xml文档
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File("./src/contact.xml"));

        //读取根标签
        Element rootELem = doc.getRootElement();
        StringBuffer sb = new StringBuffer();

        getChildNodes(rootELem,sb);
        System.out.println(sb.toString());
    }

    /**
     * 获取当前标签的所有子标签
     */
    private void getChildNodes(Element elem,StringBuffer sb){
        //System.out.println(elem.getName());
        //开始标签
        sb.append("<"+elem.getName());

        //得到标签的属性列表
        List<Attribute> attrs = elem.attributes();
        if(attrs!=null){
            for (Attribute attr : attrs) {
                //System.out.println(attr.getName()+"="+attr.getValue());
                sb.append(" "+attr.getName()+"=\""+attr.getValue()+"\"");
            }
        }
        sb.append(">");

        //得到文本
        //String content = elem.getText();
        //System.out.println(content);
        Iterator<Node> it = elem.nodeIterator();
        while(it.hasNext()){
            Node node = it.next();
            //标签
            if(node instanceof Element){
                Element el = (Element)node;
                getChildNodes(el,sb);
            }
            //文本
            if(node instanceof Text){
                Text text = (Text)node;
                sb.append(text.getText());
            }
        }
        //结束标签
        sb.append("</"+elem.getName()+">");
    }


}
