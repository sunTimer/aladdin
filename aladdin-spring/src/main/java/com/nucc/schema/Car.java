package com.nucc.schema;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Car implements BeanNameAware , ApplicationContextAware {

    private String brand;
    private float engine;
    private int horsePower;


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getEngine() {
        return engine;
    }

    public void setEngine(float engine) {
        this.engine = engine;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", engine=" + engine +
                ", horsePower=" + horsePower +
                '}';
    }

    String name;

    public void setBeanName(String name) {
        this.name = name;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}