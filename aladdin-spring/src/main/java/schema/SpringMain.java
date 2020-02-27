package schema;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class SpringMain {

    public static void main(String[] args) {

        xmlBeanFactory();
        //classPathxmlApplicationContext();
    }

    private static void classPathxmlApplicationContext() {
        BeanFactory bf = new ClassPathXmlApplicationContext("spring-context.xml");

        Car benz = (Car) bf.getBean("car");
        System.out.println(benz);
    }

    public static void xmlBeanFactory(){
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-context.xml"));
        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
    }
}
