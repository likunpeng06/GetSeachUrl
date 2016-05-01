package me.sheep3;

import junit.framework.TestCase;
import me.sheep3.utils.IOUtils;
import me.sheep3.utils.PrintUtils;
import me.sheep3.utils.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static me.sheep3.utils.IOUtils.toLines;

/**
 * String工具类测试
 *
 * @author 社ep
 * @create 2016--05--01--11:33
 */
public class StringTest extends TestCase{
    public void test_UrlFilter(){
        String url = "wwww.xuxinsongyilin";
        String[] white= {".xuxin","songyilin"};
        String[] black = {"wwwww"};
        assertEquals(true, StringUtils.UrlFilter(url,white,black));

    }

    public void test_UrlListFilter(){
        File file = new File("D:\\logs\\urls.txt");
        String[] white= {".action"};
        String[] black = {".php",".asp"};
        try {
            InputStream is = new FileInputStream(file);
            List<String> list = IOUtils.toLines(is,"utf-8");
            List<String> stringList = StringUtils.UrlListFilter(list,white,black);
            PrintUtils.printList(stringList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
