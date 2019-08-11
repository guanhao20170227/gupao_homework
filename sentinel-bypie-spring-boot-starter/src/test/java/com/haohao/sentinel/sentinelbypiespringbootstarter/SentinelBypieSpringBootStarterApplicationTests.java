package com.haohao.sentinel.sentinelbypiespringbootstarter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SentinelBypieSpringBootStarterApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetAllBeanName() {
        //get all bean name
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(SentinelBypieSpringBootStarterApplication.class);

        String[] names = ac.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }

}
