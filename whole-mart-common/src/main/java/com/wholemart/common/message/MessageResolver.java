package com.wholemart.common.message;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageResolver {
    private final MessageSource messageSource;

    public MessageResolver(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String get(String key, Object... arguments) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, arguments, key, locale);
    }
}
