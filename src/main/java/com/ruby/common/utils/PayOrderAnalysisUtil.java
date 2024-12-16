package com.ruby.common.utils;


import cn.hutool.core.text.csv.*;
import cn.hutool.core.util.CharsetUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayOrderAnalysisUtil {

    //解析微信账单excel csv 返回解析后的数据
    public static List<Map<String, Object>> analysisWechatPayOrder(MultipartFile file) {
        //2. 进行配置
        CsvReadConfig csvReadConfig=new CsvReadConfig();
        // 是否跳过空白行
        csvReadConfig.setSkipEmptyRows(true);
        // 是否设置首行为标题行
        csvReadConfig.setContainsHeader(true);
        //构建 CsvReader 对象
        CsvReader csvReader = CsvUtil.getReader(csvReadConfig);
        // 这里转了下 可能会产生临时文件，临时文件目录可以设置，也可以立马删除
        CsvData read = csvReader.read(multipartFile2File(file), CharsetUtil.CHARSET_UTF_8);
        List<Map<String,Object>> mapList = new ArrayList<>();
        List<CsvRow> rows = read.getRows();
        CsvRow header = rows.get(15);

        for (CsvRow row : rows) {
            if(row.getOriginalLineNumber() > 16){
                Map<String,Object> map = new HashMap<>();
                for (int i = 0; i < header.size(); i++) {
                    map.put(String.valueOf(i), row.get(i));
                }
                mapList.add(map);
            }
        }
        return mapList;
    }
    /**
     * multipartFile转File
     **/
    public static File multipartFile2File(MultipartFile multipartFile){
        File file = null;
        if (multipartFile != null){
            try {
                file=File.createTempFile("tmp", null);
                multipartFile.transferTo(file);
                System.gc();
                file.deleteOnExit();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return file;
    }

    public static List<Map<String, Object>> analysisAlipayPayOrder(MultipartFile file) {
//2. 进行配置
        CsvReadConfig csvReadConfig=new CsvReadConfig();
        // 是否跳过空白行
        csvReadConfig.setSkipEmptyRows(true);
        // 是否设置首行为标题行
        csvReadConfig.setContainsHeader(true);
        //构建 CsvReader 对象
        CsvReader csvReader = CsvUtil.getReader(csvReadConfig);
        // 这里转了下 可能会产生临时文件，临时文件目录可以设置，也可以立马删除
        CsvData read = csvReader.read(multipartFile2File(file), CharsetUtil.CHARSET_GBK);
        List<Map<String,Object>> mapList = new ArrayList<>();
        List<CsvRow> rows = read.getRows();
        CsvRow header = rows.get(21);

        for(int j = 0; j < rows.size(); j++){
            if(j > 21){
                Map<String,Object> map = new HashMap<>();
                for (int i = 0; i < header.size(); i++) {
                    map.put(String.valueOf(i), rows.get(j).get(i));
                }
                mapList.add(map);
            }
        }
        return mapList;
    }
}
