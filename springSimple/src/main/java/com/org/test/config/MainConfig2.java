package com.org.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * //@ComponentScan value:指定要扫描的包
 * //excludeFilters = Filter[] 指定扫描的时候按照什么规则排除那些组件
 * //includeFilters = Filter[] 指定扫描的时候只需要包含哪些组件
 * //useDefaultFilters = false 默认是true,扫描所有组件，要改成false
 * //－－－－扫描规则如下
 * //FilterType.ANNOTATION：按照注解
 * //FilterType.ASSIGNABLE_TYPE：按照给定的类型；比如按BookService类型
 * //FilterType.ASPECTJ：使用ASPECTJ表达式
 * //FilterType.REGEX：使用正则指定
 * //FilterType.CUSTOM：使用自定义规则，自已写类，实现TypeFilter接口 {@link com.org.test.config.filter.CustomerFilter}
 * @ComponentScan(value="com.enjoy.cap2",includeFilters={
 * 		@Filter(type=FilterType.CUSTOM,classes={JamesTypeFilters.class})
 * },useDefaultFilters=false)
 */
@Configuration
@ComponentScan(value = "com.org.test.beans")
public class MainConfig2 {

}
