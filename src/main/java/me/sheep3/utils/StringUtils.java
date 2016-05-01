package me.sheep3.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串操作工具类
 *
 * @author 社ep
 * @create 2016--05--01--11:31
 */
public class StringUtils {
    /**
     * 根据黑白名单的字符串列表过滤
     * @param list
     * @param WhiteKey
     * @param BlackKey
     * @return
     */
    public static List<String> UrlListFilter(List<String> list, String[] WhiteKey, String[] BlackKey){
        List<String> stringList = new ArrayList<String>();
        stringList.addAll(list);
        for (int i = 0 ; i < list.size() ; i++ ) {
            if (!UrlFilter(list.get(i),WhiteKey,BlackKey)) stringList.remove(list.get(i));
        }
        return stringList;
    }

    /**
     * 根据黑白名单过滤过滤字符
     * @param str
     * @param WhiteKey
     * @param BlackKey
     * @return
     */
    public static boolean UrlFilter(String str,String[] WhiteKey,String[] BlackKey){
        for (int i = 0 ; i < BlackKey.length ; i++ ) {
            if (str.contains(BlackKey[i])) return false;
        }
        for (int i = 0 ; i < WhiteKey.length ; i++ ) {
            if (!str.contains(WhiteKey[i])) return false;
        }
        return true;
    }

}
