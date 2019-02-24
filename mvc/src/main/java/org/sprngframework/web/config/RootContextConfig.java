package org.sprngframework.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * 跟容器主要存放services bean、repository bean
 */
@ComponentScan(value = "org.mvc.web",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})})
public class RootContextConfig {
}
