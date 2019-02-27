package com.fqz.sku.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fqz.sku.api.SkuService;
import com.fqz.sku.dto.SkuDto;
import com.fqz.sku.entity.Sku;
import com.fqz.sku.mapper.SkuMapper;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;

/**
 * @author fuqianzhong
 * @date 2019/2/24
 */
@Service
public class SkuServiceImpl implements SkuService {

    @Resource
    private SkuMapper skuMapper;

    @Override
    public SkuDto loadSkuById(Integer skuId) {
        if(skuId != null && skuId > 0){
            Sku sku = skuMapper.selectByPrimaryKey(skuId);
            if(sku != null){
                SkuDto skuDto = new SkuDto();
                BeanUtils.copyProperties(sku,skuDto);
                return skuDto;
            }
        }
        return null;
    }

    @Override
    public int updateSkuById(SkuDto skuDto) {
        if(skuDto != null && skuDto.getId() != null && skuDto.getId() > 0){
            Sku sku = new Sku();
            BeanUtils.copyProperties(skuDto, sku);
            return skuMapper.updateByPrimaryKeySelective(sku);
        }
        return 0;
    }

    @Override
    public int updateStockByIdOptimistic(int skuId, int skuCountNew, int skuCountOld) {
        if(skuId > 0){
            return skuMapper.updateSkuCount(skuId,skuCountNew,skuCountOld);
        }
        return 0;
    }
}
