//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tangyiner.world.core.handler;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.tangyiner.world.core.*;
import com.tangyiner.world.core.utils.ErrorUtil;
import com.tangyiner.world.core.utils.LoggerManager;
import com.tangyiner.world.core.utils.TangyinerCloudUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
@Slf4j
public class ResponseBody implements ResponseBodyAdvice<Object> {
    private static final Logger LOGGER = LoggerManager.getRunLog();

    @Autowired
    private CoreConfiguration coreConfiguration;

    @Autowired
    private I18nMessageUtil messageUtil;


    public ResponseBody() {
    }

    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType == null ? false : returnType.getContainingClass().getPackage().getName().startsWith("com.tangyiner");
    }

    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        Boolean isFeignClient = false;
        HttpServletRequest httpServletRequest = TangyinerCloudUtil.getHttpServletRequest();
        if (null != httpServletRequest) {
            Map<String, String> headers = TangyinerCloudUtil.getHeaders(httpServletRequest);
            if (headers.containsKey("feign-client")) {
                isFeignClient = true;
            }
        }

        if (body == null) {
            return isFeignClient ? body : new ActionResult(MDC.get("requestId"), ActionResultCode.SUCCESS.getValue(), (String)null, (Object)null);
        } else if (body instanceof Exception) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            Exception ex = (Exception)body;
            LOGGER.error("ResponseBodyHandler body = {}, ex = {}", body, ex);
            if (body instanceof BusinessException) {
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                BusinessException bex = (BusinessException)body;
                return isFeignClient ? body : new ActionResult(MDC.get("requestId"), bex.getErrorCode(), bex.getMessage(), (Object)null);
            } else {
                if (body instanceof IllegalArgumentException || body instanceof NullPointerException || body instanceof IndexOutOfBoundsException || body instanceof IllegalStateException) {
                    response.setStatusCode(HttpStatus.BAD_REQUEST);
                    String message = ex.getMessage();
                    if (StringUtils.isNotEmpty(message)) {
                        if (isFeignClient) {
                            return body;
                        }

                        if (CoreConfiguration.getProduct()) {
                            return new ActionResult(MDC.get("requestId"), ActionResultCode.BUSINESS_ERROR.getValue(), this.messageUtil.getMessage("core.exception.message", (Object[])null), (Object)null);
                        }

                        return new ActionResult(MDC.get("requestId"), ActionResultCode.BUSINESS_ERROR.getValue(), message, (Object)null);
                    }
                }

                BindingResult bindingResult = null;
                if (body instanceof BindException) {
                    bindingResult = ((BindException)body).getBindingResult();
                }

                if (body instanceof MethodArgumentNotValidException) {
                    bindingResult = ((MethodArgumentNotValidException)body).getBindingResult();
                }

                if (bindingResult != null && bindingResult.hasErrors()) {
                    response.setStatusCode(HttpStatus.BAD_REQUEST);
                    return isFeignClient ? body : new ActionResult(MDC.get("requestId"), ActionResultCode.BUSINESS_ERROR.getValue(), ErrorUtil.getErrorsMessage(bindingResult), (Object)null);
                } else if (isFeignClient) {
                    return body;
                } else {
                    return CoreConfiguration.getProduct() ? new ActionResult(MDC.get("requestId"), ActionResultCode.OTHER_ERROR.getValue(), this.messageUtil.getMessage("core.exception.message", (Object[])null), (Object)null) : new ActionResult(MDC.get("requestId"), ActionResultCode.OTHER_ERROR.getValue(), ex.getMessage(), (Object)null);
                }
            }
        } else {
            return isFeignClient ? body : new ActionResult(MDC.get("requestId"), ActionResultCode.SUCCESS.getValue(), (String)null, body);
        }
    }
}
