package com.nucc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        ServcieA bean = applicationContext.getBean(ServcieA.class);
        applicationContext.setAllowCircularReferences(true);
        System.out.println(bean);
    }
}
