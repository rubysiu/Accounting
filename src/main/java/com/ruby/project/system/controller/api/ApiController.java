package com.ruby.project.system.controller.api;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.ruby.common.utils.PayOrderAnalysisUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static com.ruby.common.utils.Threads.sleep;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/importExcel")
    public List<Map<String, Object>> test(MultipartFile file,String type) throws Exception {
        if (type.equals("wechat")) {
            return PayOrderAnalysisUtil.analysisWechatPayOrder(file);
        }else if (type.equals("alipay")) {
            return PayOrderAnalysisUtil.analysisAlipayPayOrder(file);
        }
        return null;
    }

    /**
     * 抖音弹幕获取
     */
    @PostMapping("/getDouyinDanmu/{roomId}")
    public JSONObject getDouyinDanmu(@PathVariable String roomId) throws Exception {
        String url = "https://webcast.amemv.com/webcast/reflow/room/?room_id="+roomId;
        //模拟抖音内部请求
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("User-Agent", "com.ss.android.ugc.aweme/100201 (Linux; U; Android 5.1.1; zh_CN; OPPO A33m; Build/LMY47V; Cronet/58.0.2991.0)");
        String body = HttpUtil.createGet(url).addHeaders(headerMap).execute().body();
        return JSONUtil.parseObj(body);
    }

    /**
     * 获取coupang 根据类别
     */
    @PostMapping("/getCoupangGoodsInfo/{type}/{num}")
    public JSONObject getCoupangGoodsInfo(@PathVariable String type,@PathVariable String num) throws Exception {
        if(StrUtil.isBlankIfStr(type)){
            type = "498917";
        }
        if(StrUtil.isBlankIfStr(num)){
            num = "100";
        }
        Integer pageNum = 1;
        String url = "https://www.coupang.com/np/categories/{}?page={}";
        // 模拟 coupang 内部请求
        HashMap<String, String> headerMap = new HashMap<>();
//        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36 Edg/89.0.774.54");
//        headerMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//        headerMap.put("Accept-Encoding", "gzip, deflate, br");
//        headerMap.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
//        headerMap.put("Connection", "keep-alive");
//        headerMap.put("Host", "www.coupang.com");
//        headerMap.put("Upgrade-Insecure-Requests", "1");
//        headerMap.put("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"90\", \"Google Chrome\";v=\"90\"");
//        headerMap.put("sec-ch-ua-mobile", "?0");
//        headerMap.put("sec-ch-ua-platform", "\"Windows\"");
        url = StrUtil.format(url, type, pageNum);
        String body = HttpUtil.createGet(url).addHeaders(headerMap).execute().body();
        return JSONUtil.parseObj(body);
    }

    public static void main(String[] args) {
        List<Object> list = getList(1, new ArrayList<>());
        System.out.println(list);
        // 生成excel
        ExcelWriter writer = ExcelUtil.getWriter("coupang1.xlsx");
        writer.write(list, true);
        writer.close();
        System.out.println("生成成功");
//        Elements jsonLdScripts = document.select("script[type=application/ld+json]");
//        String jsonLdScript = jsonLdScripts.get(1).html();
//        //转为json对象
//        JSONObject jsonObject = JSONUtil.parseObj(jsonLdScript);
//        JSONArray itemListElement = jsonObject.getJSONArray("itemListElement");
//        List<Object> list = new ArrayList<>();
//        for (int i = 0; i <itemListElement.size(); i++) {
//            if (!dataProductIds.containsKey(i)){
//                continue;
//            }
//            JSONObject itemElement = itemListElement.getJSONObject(i);
//            JSONObject itemObj = itemElement.getJSONObject("item");
//            Map<String, Object> itemMap = new HashMap<>();
//            itemMap.put("name", itemObj.get("name"));
//            itemMap.put("price", itemObj.getJSONObject("offers").getDouble("price"));
//            itemMap.put("url", itemObj.get("url"));
//            itemMap.put("image", itemObj.getJSONArray("image").get(0));
//            // 评论
//            itemMap.put("reviewCount", itemObj.getJSONObject("aggregateRating").getInt("reviewCount"));
//            // 星级
//            Double rating = itemObj.getJSONObject("aggregateRating").getDouble("ratingValue");
//            if(!StrUtil.isBlankIfStr(rating)&&rating.doubleValue() != 5){
//                continue;
//            }
//            itemMap.put("ratingValue", rating);
//            // 根据url中的products后的数字获取商品id
//            String productId = itemObj.get("url").toString().split("products/")[1].split("\\?")[0];
//            if(!StrUtil.isBlankIfStr(productId)){
//                //是否以84 85开头 如果不是就跳过
//                if(!productId.startsWith("84")&&!productId.startsWith("85")){
//                    continue;
//                }
//            }
//            itemMap.put("productId", productId);
//            list.add(itemMap);
//        }
//        // 生成excel
//        ExcelWriter writer = ExcelUtil.getWriter("coupang.xlsx");
//        writer.write(list, true);
//        writer.close();
//        System.out.println("生成成功");
    }

    private static List<Object> getList(Integer pageNum,List<Object> list) {
        String type = "498917";
        String num = "100";
        String url = "https://www.coupang.com/np/categories/{}?page={}&listSize=120";
        // 模拟 coupang 内部请求
        HashMap<String, String> headerMap = new HashMap<>();
        url = StrUtil.format(url, type, pageNum);
        // 模拟ip
        headerMap.put("X-Forwarded-For", getIp());
        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36 Edg/89.0.774.54");
        sleep(1000);
        String body = null;
        try {
            System.out.println("采集"+pageNum+"页数据");
            body = HttpUtil.createGet(url).addHeaders(headerMap).timeout(1000*10).execute().body();
        }catch (Exception e){
            // 重试
            System.out.println("采集"+pageNum+"页数据失败，正在重试...");
            getList(pageNum,list);
        }
        if(StrUtil.isBlankIfStr(body)){
            return list;
        }
        // 解析html
        Document document = Jsoup.parse(body);
        Element ulProductList = document.selectFirst("ul#productList");
        HashMap<Object, Object> dataProductIds = new HashMap<>();
        if (ulProductList != null) {
            // 获取所有的 li 子元素
            Elements liItems = ulProductList.children();
            for (int i = 0; i < liItems.size(); i++) {
                Element li = liItems.get(i);
                // 检查每个 li 是否包含 class 为 badge rocket 的元素
                Elements badgeRocketElements = li.select(".badge.rocket");
                if (badgeRocketElements.isEmpty()) {
                    Map<String, Object> itemMap = new LinkedHashMap<>();
                    Elements babyProductLink = li.select(".baby-product-link");
                    Elements nameElement = li.select(".name");
                    String name = nameElement.text();

                    Elements priceElement = li.select(".price-value");
                    String price = priceElement.text();

                    Elements ratingElement = li.select(".rating");
                    String rating = ratingElement.text();

                    if(StrUtil.isBlankIfStr(rating)||Double.valueOf(rating).doubleValue() != 5){
                        continue;
                    }
                    Elements ratingTotalCountElement = li.select(".rating-total-count");
                    String ratingTotalCount = ratingTotalCountElement.text().replace("(","").replace(")", "");

                    Elements imageElement = li.select(".image img");
                    String image = imageElement.attr("src");
                    String dataProductId = babyProductLink.attr("data-product-id");
                    if(!StrUtil.isBlankIfStr(dataProductId)){
                        //是否以84 85开头 如果不是就跳过
                        if(!dataProductId.startsWith("84")&&!dataProductId.startsWith("85")){
                            continue;
                        }
                    }
                    String href = babyProductLink.attr("href");
                    itemMap.put("name", name);
                    itemMap.put("price", price);
                    itemMap.put("rating", rating);
                    itemMap.put("reviewCount", ratingTotalCount);
                    itemMap.put("productId", dataProductId);
                    itemMap.put("href", "https://www.coupang.com"+href);
                    itemMap.put("image", image);
                    dataProductIds.put(i, dataProductId);
                    list.add(itemMap);
                    System.out.println(itemMap);
                }
            }
            if(list.size() < Integer.valueOf(num)&&pageNum <= 20){
                pageNum++;
                getList(pageNum, list);
            }

        } else {
            System.out.println("没有找到 id 为 'productList' 的 ul 元素");
        }
        return list;
    }

    /**
     * 模拟ip池
     */
    public static String getIp() {
        //随机生成一个ip
        return "116.25.218." + RandomUtil.randomInt(1, 255);
    }
}
