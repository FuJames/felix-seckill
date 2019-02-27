import com.fqz.order.OrderServiceApplication;
import com.fqz.order.api.OrderService;
import com.fqz.order.dto.OrderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author fuqianzhong
 * @date 2019/2/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderServiceApplication.class)
public class ServiceTester {
    @Autowired
    private OrderService orderService;

    @Test
    public void testOrderInsert(){
        OrderDto orderDto = new OrderDto();
        orderDto.setSkuId(1);
        orderDto.setUserId(1);
        orderDto.setAddTime(new Date());
        orderDto.setUpdateTime(new Date());
        orderService.createOrder(orderDto);
    }
}
