import com.nucc.ServiceD;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:spring-context.xml"}) //加载配置文件
public class MyTest {

    @Resource
    private ServiceD serviceD;

    @Test
    public void test(){
        serviceD.hello();
    }
}
