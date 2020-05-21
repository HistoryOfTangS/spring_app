package com.tangyiner.world.core;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class I18nMessageUtil {
    private MessageSource messageSource;

    public I18nMessageUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String messageKey, Object[] messageArgs) {
        return messageSource.getMessage(messageKey, messageArgs, LocaleContextHolder.getLocale());
    }
}
