package me.sheep3.key;

import me.sheep3.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * 搜索key列表的包装类，各种重载
 *
 * @author 社ep
 * @create 2016--04--30--15:53
 */
public class Key {
    private List<String> key;

    public List<String> getKey() {
        return key;
    }

    /**
     * 手动设置keylist
     * @param key
     */
    public void setKey(List<String> key) {
        this.key.addAll(key);
    }

    /**
     * 只有一个key的情况
     * @param key
     */
    public void setKey(String key) {
        this.key.add(key);
    }

    /**
     * 从文件中获取key
     * @param filepath
     */
    public void setKeyInFile(String filepath) {
        System.out.println("------>开始获取key!");
        File file = new File(filepath);
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            key.addAll(IOUtils.toLines(is,"utf-8"));
            System.out.println("一共获取"+key.size()+"个key:");
            //for (int i =0 ; i <Key.size();i ++){
            //    System.out.println(Key.get(i));
            //}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
