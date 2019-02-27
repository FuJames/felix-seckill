import com.fqz.sku.SkuServiceApplication;
import com.fqz.sku.api.SkuService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author fuqianzhong
 * @date 2019/2/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkuServiceApplication.class)
public class ServiceTester {
    @Autowired
    private SkuService skuService;

    @Test
    public void testSkuUpdateOptimistic(){
        int row = skuService.updateStockByIdOptimistic(5,0,1);
        Assert.assertTrue(row == 1);
    }
}
