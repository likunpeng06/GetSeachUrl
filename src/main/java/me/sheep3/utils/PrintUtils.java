package me.sheep3.utils;

import java.util.List;

/**
 * 打印工具类，各种格式化打印
 *
 * @author 社ep
 * @create 2016--05--01--11:43
 */
public class PrintUtils {

    public static void printList(List<String> list){
        for (int i = 0 ; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }

}
