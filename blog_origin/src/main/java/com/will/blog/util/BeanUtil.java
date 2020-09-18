package com.will.blog.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *  @name BeanUtil
 *  @Description Util for Bean
 *  @author will7 Mao
 *  @Date  2020/9/10
 */
public class BeanUtil {
    //copy not null prop
    public static String [] selectNullProp(Object source) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor [] descriptors = beanWrapper.getPropertyDescriptors();
        List<String> nullPropArray = new ArrayList<>();
        for (PropertyDescriptor d : descriptors){
            String descriptorName = d.getName();
            if (beanWrapper.getPropertyValue(descriptorName) == null){
                nullPropArray.add(descriptorName);
            }
        }
        return nullPropArray.toArray(new String [nullPropArray.size()]);
    }
}
