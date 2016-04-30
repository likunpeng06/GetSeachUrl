package me.sheep3;

import me.sheep3.key.Key;
import me.sheep3.utils.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 尝试访问百度搜索并获取连接
 *
 * @author 社ep
 * @create 2016--04--29--0:25
 */
public class ClientBaidu {
    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private List<String> list = new ArrayList<String>();
    Key key;

    public ClientBaidu(){

    }

    /**
     * 根据目标连接获取内容
     * @param url
     * @return
     */
    public String getHtmlString(String url){
        // 请求起始行
        HttpGet get = new HttpGet(url);
        // 设置请求头
        get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Firefox/39.0");
        // 执行请求
        CloseableHttpResponse response = null;
        try {

            response = httpClient.execute(get);
            //获得起始行
            //System.out.println(response.getStatusLine().toString()+"\n");
            // 获得首部---当然也可以使用其他方法获取
            Header[] hs=response.getAllHeaders();
            //for(Header h:hs){ System.out.println(h.getName()+":\t"+h.getValue()+"\n"); }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取实体
        HttpEntity ety=response.getEntity();
        String content = null;
        try {
            content = EntityUtils.toString(ety,"utf8");
            EntityUtils.consume(ety);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("转码获取内容失败!");
        }

        //释放实体
        try {
            response.close();//关闭响应
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("相应关闭失败!");
        }
        System.out.println("成功获得html内容!");
        return content;
    }

    /**
     * 生成搜索的目标url
     * @param keyword 搜索关键字
     * @param page 搜索的目标页面
     * @return
     */
    public String createUrl(String keyword , int page){
        String url = null;
        try {
            url = "http://soguge.com/search?hl=zh-CN&q="+ URLEncoder.encode(keyword, "UTF-8")+"&start="+page;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 根据Html内容获得结果连接列表
     * @param html
     */
    public int getResultByHtml(String html){
        Document doc = Jsoup.parse(html);
        Elements links = doc.select("h3.r > a");
        // class="r" h3 > a > data-href 值
        //List<String> urlList = new ArrayList<String>(); 将url存在list中
        System.out.println("获取连接数:" + links.size());
        for (Element link : links) {
            String url = link.attr("href");
            //String url = link.attr("data-href");
            System.out.println(url);
            list.add(url);
        }
        return links.size();
    }

}
