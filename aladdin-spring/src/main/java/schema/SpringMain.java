package schema;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

    public static void main(String[] args) {

        BeanFactory bf = new ClassPathXmlApplicationContext("spring-context.xml");

        Car benz = (Car) bf.getBean("car");
        System.out.println(benz);
    }
}
