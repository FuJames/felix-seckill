package com.fqz.sku.api;

import com.fqz.sku.dto.SkuDto;

/**
 * @author fuqianzhong
 * @date 2019/2/24
 */
public interface SkuService {
    /**
     * 查询sku详情
     * @param skuId
     * @return sku详情
     */
    SkuDto loadSkuById(Integer skuId);

    /**
     * 根据skuId更新sku详情
     * @param skuDto
     * @return 影响行数
     */
    int updateSkuById(SkuDto skuDto);

    /**
     * 根据skuId更新sku库存,使用乐观锁,当库存值等于skuCountOld时,将库存更新为skuCountNew
     * update sku set sku_count = ${skuCountNew} where sku_count = ${skuCountOld}
     * @param skuId
     * @param skuCountNew
     * @param skuCountOld
     * @return
     */
    int updateStockByIdOptimistic(int skuId, int skuCountNew, int skuCountOld);

}
