package me.sheep3;

import junit.framework.TestCase;
import me.sheep3.utils.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static javafx.scene.input.KeyCode.O;
import static me.sheep3.utils.IOUtils.toLines;

/**
 * ClientBaidu测试类
 *
 * @author 社ep
 * @create 2016--04--29--0:35
 */
public class ClientBaiduTest extends TestCase{

    public void testUrl(){
        ClientBaidu clientBaidu = new ClientBaidu();
        String string = clientBaidu.createUrl("徐鑫", 0);
        System.out.println(string);
    }
    public void test_getseach(){
        ClientBaidu clientBaidu = new ClientBaidu();
        String string = clientBaidu.createUrl("徐鑫", 0);
        String html = clientBaidu.getHtmlString(string);
        System.out.println("解析数据");
        Document doc = Jsoup.parse(html);
        Elements links = doc.select("h3.r > a");
        // class="r" h3 > a > data-href 值

        for (Element link : links) {
            String url = link.attr("href");
            //String url = link.attr("data-href");
            System.out.println(url);
        }

    }

    public void test_moreConnect(){
        ClientBaidu clientBaidu = new ClientBaidu();
        String key = "inurl:(login.action) 娱乐";

        String path ="D:\\Tools\\Struts2016\\strutskey.txt";
        //clientBaidu.getKeyListInFile(path);
        for (int j = 0 ; j < clientBaidu.keyList.size(); j++){
            int i = 0;
            while (true){
                System.out.println("第"+(i+1)+"面:");
                String url = clientBaidu.createUrl(clientBaidu.keyList.get(j),i*10);
                String html = clientBaidu.getHtmlString(url);
                //System.out.println(html);
                int resultCount = clientBaidu.getResultByHtml(html);
                if (resultCount < 10 ){
                    System.out.println("无后续连接!");
                    break;
                }
                i++;
                if (i > 5 ){
                    break;
                }
            }
        }

        System.out.println("连接获取完毕!");
        System.out.println("将连接存入文件中!");
        File file = new File("D://logs//urls2.txt");
        IOUtils.toFileWithLine(file,clientBaidu.list);
        System.out.println("存入成功!");
    }

    public void test_File2Line(){
        String path ="D:\\Tools\\Struts2016\\strutskey.txt";
        ClientBaidu clientBaidu = new ClientBaidu();
        //clientBaidu.getKeyListInFile(path);

    }
}
