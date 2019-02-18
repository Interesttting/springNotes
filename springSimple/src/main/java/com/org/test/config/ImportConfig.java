package com.org.test.config;

import com.org.test.bean.MyImportBeanDefinitionRegistrar;
import com.org.test.bean.MyImportSelector;
import com.org.test.bean.defind.A;
import com.org.test.bean.defind.B;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Import 批量导入第三方类的方式并生成bean
 * MyImportSelector 批量生成多个bean
 * MyImportBeanDefinitionRegistrar 通过修改bean 定义来注册bean
 *
 */
@Import(value = {A.class,B.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
@Configuration
public class ImportConfig {
}
