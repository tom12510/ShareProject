import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

/**
 * @author miaomiaole
 * @date 2020/4/24 21:37
 * @DESCRIBE
 */
public class XMLProject {
    public static void main(String[] args) throws DocumentException, InterruptedException {
        SAXReader reader = new SAXReader();
        //读取XML文件 生成Document对象
        Document document = reader.read("D:\\ShareProject\\XMLProject\\src\\main\\resources\\XMLTest.xml");
        //通过rootElement可以获取该XML的所有元素
        
        Element rootElement = document.getRootElement();

        System.out.println(rootElement.getName());

        String text = rootElement.getText();
        System.out.println(text);
        System.out.println(rootElement);
       // XPATH也是DOM4J的一部分
        //参数传入XPATH语法
        List books = document.selectNodes("/books");
        for (Object book : books) {
            System.out.println(book);
        }
    }
}
