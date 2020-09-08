package regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:Java的正则表达式
 * @Author:BigRedCaps
 */
public class RegexTest
{
    public static void main (String[] args)
    {
        String str = "hello ,java";
        //正则表达式匹配时默认为贪婪模式
        System.out.println(str.replaceFirst("\\w*","√"));
        //勉强模式匹配
        System.out.println(str.replaceFirst("\\w*?","√"));

        System.out.println("*****************Pattern&Matcher***************");
        System.out.println("********示例一*********");
        String tele = "张三的电话：13525778058；李四的电话：15682348058；";
        Matcher matcher = Pattern.compile("((13\\d)|(15\\d))\\d{8}").matcher(tele);
        while(matcher.find())
        {
            System.out.println(matcher.group());
        }
        System.out.println("********示例二**********");
        String normal = "Java is very easy";
        Matcher m = Pattern.compile("\\w+").matcher(normal);
        while (m.find()){
            System.out.println(m.group()+"子串的起始位置:"+m.start()+",其结束位置："+m.end());
        }

        System.out.println("********示例三**********");
        String[] mails = {
                "2465054772@qq.com",
                "123456@gmail.com",
                "1373241256@163.com",
                "hellojava@lancoo.org",
                "konkong@china.gov",
        };
        String mailRegex = "\\w{3,20}@\\w+\\.(com|org|cn|net|gov)";
        Pattern pattern = Pattern.compile(mailRegex);
        Matcher matcher1 = null;
        for (String mail :mails)
        {
            if(matcher1==null){
                matcher1 = pattern.matcher(mail);
            }else{
                matcher1.reset(mail);
            }
            String result = mail+(matcher1.matches() ? "是":"不是" )+"一个有效的邮件地址！";
            System.out.println(result);
        }

        System.out.println("********示例四**********");
        System.out.println("利用正则表达式对字符串进行分割查找替换操作");
        String[] arrayStr = {
                "java regex expression in java 1.4",
                "regular expression is very strong",
                "String class has a method replaceAll"
        };
        Pattern p = Pattern.compile("re\\w*");
        Matcher matcher2 = null;
        for (int i = 0; i < arrayStr.length ; i++)
        {
            if(matcher2==null){
                matcher2 = p.matcher(arrayStr[i]);
            }else{
                matcher2.reset(arrayStr[i]);
            }
            System.out.println(matcher2.replaceAll("哈哈"));
        }
        System.out.println("String类的拆分，替换方法");
        for (String s : arrayStr)
        {
            System.out.println(s.replaceAll("re\\w*","哈哈"));
            System.out.println(Arrays.toString(s.split(" ")));
        }


    }
}
