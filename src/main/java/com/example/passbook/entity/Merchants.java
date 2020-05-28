package com.example.passbook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *  商户对象模型
 */
@Data /** 提供get、set方法 */
@NoArgsConstructor /** 提供无参构造方法 */
@AllArgsConstructor /** 提供全参构造方法 */
@Entity /** 声明实体对象 */
@Table(name = "merchants") /** 声明此实体对象映射到数据库中的表 */
public class Merchants {
    /** 商户 id, 主键*/
    @Id /** 声明此属性为主键 */
    @GeneratedValue(strategy = GenerationType.IDENTITY) /** 指定主键的生成策略 */
    @Column(name = "id", nullable = false) /** 声明该属性与数据库字段的映射关系 */
    private Integer id;

    /** 商户名称 需要全局唯一 */
    @Basic /** 表示这是一个基础字段 */
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    /** 商户 logo*/
    @Basic
    @Column(name = "logo_url", nullable = false)
    private String logoUrl;

    /** 商户营业执照 */
    @Basic
    @Column(name = "business_license_url", nullable = false)
    private String businessLicenseUrl;

    /** 商户联系电话*/
    @Basic
    @Column(name = "phone", nullable = false)
    private String phone;

    /** 商户地址 */
    @Basic
    @Column(name = "address", nullable = false)
    private String address;

    /** 商户是否通过审核 */
    @Basic
    @Column(name = "is_audit", nullable = false)
    private Boolean isAudit = false;
}
