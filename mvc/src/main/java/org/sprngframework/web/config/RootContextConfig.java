package org.sprngframework.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@ComponentScan(value = "org.mvc.web",includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})})
public class RootContextConfig {
}
