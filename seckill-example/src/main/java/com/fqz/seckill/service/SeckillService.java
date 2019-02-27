package com.fqz.seckill.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fqz.order.api.OrderService;
import com.fqz.order.dto.OrderDto;
import com.fqz.seckill.model.Result;
import com.fqz.seckill.model.ResultCodeEnum;
import com.fqz.seckill.locks.DistributeLocks;
import com.fqz.sku.api.SkuService;
import com.fqz.sku.dto.SkuDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author fuqianzhong
 * @date 2019/2/26
 */
@Service
public class SeckillService {
    @Reference
    private OrderService orderService;
    @Reference
    private SkuService skuService;
    @Resource
    private DistributeLocks distributeLocks;

    private static Logger logger = LoggerFactory.getLogger(SeckillService.class);

    public Result buy(Integer userId, Integer skuId, Integer skuCount){

        try {
            //distribute lock by ZK
//            distributeLocks.lock();//ZK lock
            //1. query stock for sku
            SkuDto skuDto = skuService.loadSkuById(skuId);
            //2. validate skuCount
            if(skuDto == null){
                return new Result(ResultCodeEnum.BAD_REQUEST,null);
            }
            Integer skuStock = skuDto.getStock();
            if(skuStock == null || skuStock < skuCount){
                return new Result(ResultCodeEnum.NO_RESOURCE,null);
            }
            //3. substract stock for sku
            skuDto.setStock(skuStock - skuCount);
//            int row = skuService.updateSkuById(skuDto);//ZK lock
            //update sku stock by OptimisticLock, db X lock
            int row = skuService.updateStockByIdOptimistic(skuId,skuStock-skuCount,skuStock);
            //4. create order when updating stock successfully
            if(row == 1){
                OrderDto orderDto = new OrderDto();
                orderDto.setUserId(userId);
                orderDto.setSkuId(skuId);
                orderDto.setSkuCount(skuCount);
                orderDto.setAddTime(new Date());
                orderDto.setUpdateTime(new Date());
                orderDto.setStatus(1);
                int orderId = orderService.createOrder(orderDto);
                if(orderId <= 0){
                    return new Result(ResultCodeEnum.SERVER_ERROR,null);
                }
                return new Result(ResultCodeEnum.SUCCESS,orderId);
            }else {
                return new Result(ResultCodeEnum.NO_RESOURCE,null);
            }
        } catch (Exception e) {
            logger.error("error buy , " , e);
            return new Result(ResultCodeEnum.LOCK_ERROR,null);
        } finally {
            try {
//                distributeLocks.unlock();//ZK lock
            } catch (Exception e) {
                logger.error("error release lock , " , e);
            }
        }

    }

}
