package com.example.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建商户响应
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsResponse {
    /**
     * 商户 id：创建失败则为 -1
     * 告诉商户它的注册信息对应的 id
     */
    private Integer id;
}
