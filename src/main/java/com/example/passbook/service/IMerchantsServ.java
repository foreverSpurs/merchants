package com.example.passbook.service;

import com.example.passbook.vo.CreateMerchantsRequest;
import com.example.passbook.vo.PassTemplate;
import com.example.passbook.vo.Response;

/**
 * 商户服务接口定义
 */
public interface IMerchantsServ {
    /**
     * 创建商户服务
     * @param request {@link CreateMerchantsRequest}创建商户请求
     * @return {@link Response}
     */
    Response createMerchants(CreateMerchantsRequest request);

    /**
     * 根据 id 构造商户信息，即商户可以通过拿到的id来查看自己的商户注册信息
     * @param id 商户 id
     * @return {@link Response}
     */
    Response buildMerchantsInfoById(Integer id);

    /**
     * 投放优惠券
     * @param passTemplate {@link PassTemplate}
     * @return {@link Response}
     */
    Response dropPassTemplate(PassTemplate passTemplate);
}
