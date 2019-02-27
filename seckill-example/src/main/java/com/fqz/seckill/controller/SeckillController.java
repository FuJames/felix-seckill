package com.fqz.seckill.controller;

import com.fqz.order.dto.OrderDto;
import com.fqz.seckill.model.Result;
import com.fqz.seckill.model.ResultCodeEnum;
import com.fqz.seckill.service.SeckillService;
import com.fqz.sku.dto.SkuDto;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author fuqianzhong
 * @date 2019/2/25
 */
@Controller
public class SeckillController {
    @Resource
    private SeckillService seckillService;

    private Logger logger = LoggerFactory.getLogger(SeckillController.class);

    @RequestMapping("/buy")
    @ResponseBody
    public Result buy(@RequestParam Integer skuId, @RequestParam Integer skuCount){
        if(skuId == null || skuId <= 0 || skuCount == null || skuCount <= 0){
            return new Result(ResultCodeEnum.BAD_REQUEST,null);
        }
        Integer userId = 166;//mock userId

        return seckillService.buy(userId,skuId,skuCount);

    }
}
