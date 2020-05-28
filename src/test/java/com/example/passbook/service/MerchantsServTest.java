package com.example.passbook.service;

import com.alibaba.fastjson.JSON;
import com.example.passbook.vo.CreateMerchantsRequest;
import com.example.passbook.vo.PassTemplate;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 商户服务测试类
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MerchantsServTest {

    @Autowired
    private IMerchantsServ merchantsServ;

    /**
     * {"data":{"id":18},"errorCode":0,"errorMsg":""}
     */
    @Test
    @Transactional
    public void testCreateMerchantServ() {

        CreateMerchantsRequest request = new CreateMerchantsRequest();
        request.setName("TDH-merchant");
        request.setLogoUrl("www.tdh.com");
        request.setBusinessLicenseUrl("www.tdh.com");
        request.setPhone("1234567890");
        request.setAddress("南京市");

        System.out.println(JSON.toJSONString(merchantsServ.createMerchants(request)));
    }

    /**
     * {"data":{"address":"深圳市","businessLicenseUrl":"www.tdh.com","id":20,
     * "isAudit":true,"logoUrl":"www.tdh.com","name":"xiaott","phone":"13245678909"},
     * "errorCode":0,"errorMsg":""}
     */
    @Test
    public void testBuildMerchantsInfoById(){
        System.out.println(JSON.toJSONString(merchantsServ.buildMerchantsInfoById(20)));
    }

    @Test
    public void testDropPassTemplate(){
        PassTemplate passTemplate = new PassTemplate();
        passTemplate.setId(19);
        passTemplate.setTitle("Title: tdh-1");
        passTemplate.setSummary("简介: tdh");
        passTemplate.setDesc("详情: tdh");
        passTemplate.setLimit(1000L);
        passTemplate.setHasToken(false);
        passTemplate.setBackground(2);
        passTemplate.setStart(DateUtils.addDays(new Date(),-10));
        passTemplate.setEnd(DateUtils.addDays(new Date(),10));

        System.out.println(JSON.toJSONString(
                merchantsServ.dropPassTemplate(passTemplate)
        ));
    }
}
