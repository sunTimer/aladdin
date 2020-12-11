package com.nucc.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class CarNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        super.registerBeanDefinitionParser("car", new CarBeanDefinitionParser());
    }
}
