package com.example.passbook.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.passbook.constant.Constants;
import com.example.passbook.constant.ErrorCode;
import com.example.passbook.dao.MerchantsDao;
import com.example.passbook.entity.Merchants;
import com.example.passbook.service.IMerchantsServ;
import com.example.passbook.vo.CreateMerchantsRequest;
import com.example.passbook.vo.CreateMerchantsResponse;
import com.example.passbook.vo.PassTemplate;
import com.example.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Writer;
import java.util.Optional;

/**
 * 商户服务接口实现
 */
@Slf4j
@Service
public class MerchantsServImpl implements IMerchantsServ {
    /** Merchants 数据库接口 */
    private final MerchantsDao merchantsDao;
    /** Kafka 消息队列接口 */
    private KafkaTemplate<String, String> kafkaTemplate;

    /** 以构造函数的形式注入bean */
    @Autowired
    public MerchantsServImpl(MerchantsDao merchantsDao, KafkaTemplate kafkaTemplate){
        this.merchantsDao = merchantsDao;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public Response createMerchants(CreateMerchantsRequest request) {
        Response response = new Response();
        CreateMerchantsResponse merchantsResponse = new CreateMerchantsResponse();

        ErrorCode errorCode = request.validate(merchantsDao);
        if(errorCode != ErrorCode.SUCCESS){
            merchantsResponse.setId(-1);
            response.setErrorCode((errorCode.getCode()));
            response.setErrorMsg((errorCode.getDesc()));
        }else{
            //save()方法保存成功后，会返回这个merchants对象
            merchantsResponse.setId(merchantsDao.save(request.toMerchants()).getId());
        }
        response.setData(merchantsResponse);
        return response;
    }

    @Override
    public Response buildMerchantsInfoById(Integer id) {
        Response response = new Response();
        //通过id，从数据库中获取商户信息
        Optional<Merchants> merchants = merchantsDao.findById(id);
        if(null == merchants){
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
        }
        response.setData(merchants);
        return response;
    }

    /** 将优惠券对象序列化后发送到 kafka 供用户消费 */
    @Override
    public Response dropPassTemplate(PassTemplate passTemplate) {
        Response response = new Response();
        ErrorCode errorCode = passTemplate.validate(merchantsDao);

        if(errorCode != ErrorCode.SUCCESS){
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        }else{
            /** 序列化优惠券信息 */
            String template = JSON.toJSONString(passTemplate);
            /** topic, key, data */
            kafkaTemplate.send(
                    Constants.TEMPLATE_TOPIC,
                    Constants.TEMPLATE_TOPIC,
                    template
            );
            log.info("DropPassTemplate: {}", template);
        }
        return null;
    }
}
