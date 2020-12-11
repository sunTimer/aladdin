package com.nucc.schema;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class CarBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {

        String brand = element.getAttribute("brand");
        String engine = element.getAttribute("engine");
        String hp = element.getAttribute("hp");

        if (StringUtils.hasText(brand)) {
            builder.addPropertyValue("brand", brand);
        }
        if (StringUtils.hasText(engine)) {
            builder.addPropertyValue("engine", engine);
        }
        if (StringUtils.hasText(hp)) {
            builder.addPropertyValue("hp", hp);
        }
    }


    @Override
    protected Class<?> getBeanClass(Element element) {
        return Car.class;
    }
}
