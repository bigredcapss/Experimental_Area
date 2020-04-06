package saxhandler;

import bean_xml.Contact;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:Sax处理程序
 * @Author:BigRedCaps
 */
public class MyDefaultHandler3 extends DefaultHandler
{
    //存储所有联系人对象
    private List<Contact> list = new ArrayList<Contact>();

    public List<Contact> getList(){
        return list;
    }
    //保存一个联系人信息
    private Contact contact;
    /**
     * 思路：
     * 	1）创建Contact对象
     *  2）把每个contact标签内容存入到Contact对象
     *  3）把Contact对象放入List中
     */
    //用于临时存储当前读到的标签名
    private String curTag;

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException
    {
        curTag = qName;
        //读取到contact的开始标签创建Contact对象
        if("contact".equals(qName)){
            contact = new Contact();

            //设置id值
            contact.setId(attributes.getValue("id"));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        //当前文本内容
        String content = new String(ch,start,length);

        if("name".equals(curTag)){
            contact.setName(content);
        }

        if("age".equals(curTag)){
            contact.setAge(content);
        }

        if("phone".equals(curTag)){
            contact.setPhone(content);
        }

        if("email".equals(curTag)){
            contact.setEmail(content);
        }

        if("qq".equals(curTag)){
            contact.setQq(content);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        //设置空时为了避免空格换行设置到对象的属性中
        curTag = null;
        //读到contact的结束标签放入List中
        if("contact".equals(qName)){
            list.add(contact);
        }
    }
}
