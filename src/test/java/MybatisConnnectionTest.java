import com.swpuiot.config.RootConfig;
import com.swpuiot.config.WebConfig;
import com.swpuiot.initializer.WebAppInitializer;
import com.swpuiot.service.DataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(value = SpringJUnit4ClassRunner.class)
//@WebAppConfiguration(value = "RootConfig.class")
//@ContextConfiguration(classes = RootConfig.class)
public class MybatisConnnectionTest {
    @Autowired
    private DataService dataService;

    @Test

    public void test1() {
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
//        dataService = (DataService) context.getBean("dataService");
        dataService.getAllMeeting();
//        ((ClassPathXmlApplicationContext) context).close();
    }
}
