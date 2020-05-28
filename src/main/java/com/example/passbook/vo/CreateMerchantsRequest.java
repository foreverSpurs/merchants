package com.example.passbook.vo;

import com.example.passbook.constant.ErrorCode;
import com.example.passbook.dao.MerchantsDao;
import com.example.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建商户请求
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsRequest {
    /** 商户名称 */
    private String name;

    /** 商户 logo */
    private String logoUrl;

    /** 商户营业执照 */
    private String businessLicenseUrl;

    private String phone;

    private String address;

    /**
     * 验证请求有效性
     * @param merchantsDao {@link MerchantsDao}
     * @return {@link ErrorCode}
     */
    public ErrorCode validate(MerchantsDao merchantsDao){
        /** 这里的信息校验只是示例，可以根据实际情况完善 */
        if(merchantsDao.findByName(this.name) != null){
            return ErrorCode.DUPLICATE_NAME;
        }
        if(null == this.logoUrl){
            return ErrorCode.EMPTY_LOGO;
        }
        if(null == this.businessLicenseUrl){
            return ErrorCode.EMPTY_BUSSINESS_LICENSE;
        }
        if(null == this.address){
            return ErrorCode.EMPTY_ADDRESS;
        }
        if(null == this.phone){
            return ErrorCode.ERROR_PHONE;
        }

        return ErrorCode.SUCCESS;
    }

    /**
     * 将请求对象转换为 Merchants 的数据库对象
     * @return {@link Merchants}
     */
    public Merchants toMerchants(){
        Merchants merchants = new Merchants();

        merchants.setName(name);
        merchants.setLogoUrl(logoUrl);
        merchants.setBusinessLicenseUrl(businessLicenseUrl);
        merchants.setPhone(phone);
        merchants.setAddress(address);

        return merchants;
    }
}
