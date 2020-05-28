package com.example.passbook.dao;

import com.example.passbook.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *  Merchants Dao 接口：通过Jpa自动生成SQL语句完成查询
 *  Merchants：表的类型
 *  Integer：主键类型
 */
public interface MerchantsDao extends JpaRepository<Merchants, Integer> {
    /**
     * 根据Id获取商户对象
     * @param id 商户id
     * @return {@link Merchants} @link可以帮助直接跳转到Merchants对象查看
     */
    Optional<Merchants> findById(Integer id);

    /**
     * 根据
     * @param name 商户名称
     * @return {@link Merchants}
     */
    Merchants findByName(String name);


}
