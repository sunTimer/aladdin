package com.nucc;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@PropertySource("classpath:/bean.properties")
@ComponentScan("com.nucc")
@EnableAspectJAutoProxy
@EnableAsync
public class MainConfig {
}
