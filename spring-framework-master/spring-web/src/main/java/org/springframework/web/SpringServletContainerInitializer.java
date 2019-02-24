/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web;

import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

/**
 *
 * Tomcat的Host容器在添加子容器时，会通过解析.xml并通过classloader加载 @HandlesTypes注解的类
 * 读取@HandlesTypes注解value值。并放入ServletContainerInitializers 对应的Set集合中
 * 在ApplicationContext 内部启动时会通知 ServletContainerInitializers 的onStart方法()。这个onStart方法的第一个参数就是@HandlesTypes注解的value 值指定的Class集合
 * 在Spring 应用中，对ServletContainerInitializers的实现就是SpringServletContainerInitializer,注解指定的类就是WebApplicationInitializer.
 *
 *
 * SpringServletContainerInitializer的主要内容：
 * 1、让tomcat加载WebApplicationInitializer的子类放入到Set<Class<?>> webAppInitializerClasses集合中 即@HandlesTypes(WebApplicationInitializer.class)
 * 2、实例化这些实现类 即
 * 3、调用这些实现类的onStartup方法
 *
 */

@HandlesTypes(WebApplicationInitializer.class)
public class SpringServletContainerInitializer implements ServletContainerInitializer {

	@Override
	public void onStartup(@Nullable Set<Class<?>> webAppInitializerClasses, ServletContext servletContext)
			throws ServletException {

		List<WebApplicationInitializer> initializers = new LinkedList<>();
		//通过反射方式 实例化 所有所有的WebApplicationInitializer实现类
		if (webAppInitializerClasses != null) {
			for (Class<?> waiClass : webAppInitializerClasses) {
				//判断是否不是接口、不是抽象类
				if (!waiClass.isInterface() && !Modifier.isAbstract(waiClass.getModifiers()) &&
						WebApplicationInitializer.class.isAssignableFrom(waiClass)) {
					try {
						initializers.add((WebApplicationInitializer)
								ReflectionUtils.accessibleConstructor(waiClass).newInstance());
					}
					catch (Throwable ex) {
						throw new ServletException("Failed to instantiate WebApplicationInitializer class", ex);
					}
				}
			}
		}

		if (initializers.isEmpty()) {
			servletContext.log("No Spring WebApplicationInitializer types detected on classpath");
			return;
		}

		servletContext.log(initializers.size() + " Spring WebApplicationInitializers detected on classpath");
		AnnotationAwareOrderComparator.sort(initializers);

		//处理有所的WebApplicationInitializer子类实例调用onStartup
		for (WebApplicationInitializer initializer : initializers) {
			initializer.onStartup(servletContext);
		}
	}

}
