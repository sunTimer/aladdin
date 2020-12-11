package com.nucc;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ServiceB {

    public ServiceB() {
        System.out.println("ServiceB 构造器---");
    }

    @Value("${name}")
    private String name;
}
