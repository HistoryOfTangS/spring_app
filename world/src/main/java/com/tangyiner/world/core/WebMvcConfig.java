package com.tangyiner.world.core;

import com.tangyiner.world.core.handler.ControllerExceptionHandler;
import com.tangyiner.world.core.CoreConstants;
import com.tangyiner.world.core.handler.ResponseBody;
import com.tangyiner.world.core.inteceptor.CorsInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.tangyiner.**.controller")
@Import({ControllerExceptionHandler.class, ResponseBody.class})
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 定制消息转换器, 控制对象序列化格式
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(
                MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8, MediaType.APPLICATION_XML,
                MediaType.APPLICATION_XHTML_XML, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN, MediaType.TEXT_XML));
        converter.setObjectMapper(CoreConstants.OBJECT_MAPPER);
        converters.add(converter);
    }

    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }*/

    /**
     * 跨域处理拦截器
     *
     * @return
     */
    @Bean
    public CorsInterceptor corsInterceptor() {
        return new CorsInterceptor();
    }

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        //注册拦截器, 处理Cross Origin Resource Sharing允许跨域访问
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");
    }
}
