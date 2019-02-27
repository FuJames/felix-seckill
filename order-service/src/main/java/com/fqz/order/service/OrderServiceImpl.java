package com.fqz.order.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fqz.order.api.OrderService;
import com.fqz.order.dto.OrderDto;
import com.fqz.order.entity.Order;
import com.fqz.order.mapper.OrderMapper;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;

/**
 * @author fuqianzhong
 * @date 2019/2/25
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public int createOrder(OrderDto orderDto) {
        if(orderDto != null){
            Order order = new Order();
            BeanUtils.copyProperties(orderDto,order);
            int row = orderMapper.insert(order);
            if(row == 1){
                return order.getId();
            }
        }
        return 0;
    }

    @Override
    public int updateOrder(OrderDto orderDto) {
        if(orderDto != null && orderDto.getId() != null && orderDto.getId() > 0){
            Order order = new Order();
            BeanUtils.copyProperties(orderDto, order);
            return orderMapper.updateByPrimaryKeySelective(order);
        }
        return 0;
    }
}
