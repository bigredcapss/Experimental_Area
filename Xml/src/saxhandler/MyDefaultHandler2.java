package saxhandler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @Description:Sax处理程序
 * @Author:BigRedCaps
 */
public class MyDefaultHandler2 extends DefaultHandler
{
    //存储xml文档信息
    private StringBuffer sb = new StringBuffer();

    //获取xml信息
    public String getContent(){
        return sb.toString();
    }

    /**
     * 开始标签
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        sb.append("<"+qName);
        //判断是否有属性
        if(attributes!=null){
            for(int i=0;i<attributes.getLength();i++){
                //得到属性名称
                String attrName = attributes.getQName(i);
                //得到属性值
                String attrValue = attributes.getValue(i);
                sb.append(" "+attrName+"=\""+attrValue+"\"");
            }
        }
        sb.append(">");
    }

    /**
     * 文本内容
     */
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        //得到当前读取的文本
        String content = new String(ch,start,length);
        sb.append(content);
    }

    /**
     * 结束标签
     */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        sb.append("</"+qName+">");
    }
}
