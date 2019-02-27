package com.fqz.order.api;

import com.fqz.order.dto.OrderDto;

/**
 * @author fuqianzhong
 * @date 2019/2/24
 */
public interface OrderService {
    /**
     * 创建订单
     * @param orderDto
     * @return 订单ID
     */
    int createOrder(OrderDto orderDto);

    /**
     * 根据订单ID更新订单
     * @param orderDto
     * @return 影响行数
     */
    int updateOrder(OrderDto orderDto);
}
