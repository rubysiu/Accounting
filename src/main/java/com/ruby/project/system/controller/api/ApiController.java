package com.ruby.project.system.controller.api;

import com.ruby.common.utils.PayOrderAnalysisUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
}
