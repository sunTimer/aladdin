package com.nucc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ServcieA implements InitializingBean, DisposableBean, BeanPostProcessor, ApplicationContextAware {

    public ServcieA() {
        System.out.println("ServiceA 构造方法调用 ----"); //1
    }

    @PostConstruct
    public void init(){
        System.out.println("init method调用---");//3
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean post process before"); //2
        return bean;
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean 方法调用---"); //4
    }


    public void destroy() throws Exception {
        System.out.println("DisposableBean 销毁方法调用----");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("application context aware..---");
    }
}
