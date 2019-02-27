package com.fqz.sku.mapper;

import com.fqz.sku.entity.Sku;
import org.apache.ibatis.annotations.Param;

public interface SkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sku record);

    int insertSelective(Sku record);

    Sku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sku record);

    int updateByPrimaryKey(Sku record);

    int updateSkuCount(@Param("skuId") int skuId,
                       @Param("skuCountNew")int skuCountNew,
                       @Param("skuCountOld")int skuCountOld);
}