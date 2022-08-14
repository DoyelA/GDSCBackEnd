package gdsc.task.backend.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MessageConfig {
    @Bean(value = "serviceMessage")
    public MessageSource serviceMessage() {

        ResourceBundleMessageSource serviceMessageSource = new ResourceBundleMessageSource();
        serviceMessageSource.setAlwaysUseMessageFormat(true);
        serviceMessageSource.setCacheMillis(3600);
        serviceMessageSource.setDefaultEncoding("UTF-8");
        serviceMessageSource.setDefaultLocale(Locale.ENGLISH);
        serviceMessageSource.setUseCodeAsDefaultMessage(true);
        serviceMessageSource.setBasename("locale/servicemessage");
        return serviceMessageSource;
    }

    @Bean(value="validationMessage")
    public MessageSource validationMessage(){
        ResourceBundleMessageSource validationMessageSource = new ResourceBundleMessageSource();
        validationMessageSource.setAlwaysUseMessageFormat(true);
        validationMessageSource.setCacheMillis(3600);
        validationMessageSource.setDefaultEncoding("UTF-8");
        validationMessageSource.setDefaultLocale(Locale.ENGLISH);
        validationMessageSource.setUseCodeAsDefaultMessage(true);
        validationMessageSource.setBasename("locale/validationmessage");
        return validationMessageSource;
    }

    @Bean(value="exceptionMessage")
    public MessageSource exceptionMessage(){
        ResourceBundleMessageSource exceptionMessageSource = new ResourceBundleMessageSource();
        exceptionMessageSource.setAlwaysUseMessageFormat(true);
        exceptionMessageSource.setCacheMillis(3600);
        exceptionMessageSource.setDefaultEncoding("UTF-8");
        exceptionMessageSource.setDefaultLocale(Locale.ENGLISH);
        exceptionMessageSource.setUseCodeAsDefaultMessage(true);
        exceptionMessageSource.setBasename("locale/exceptionmessage");
        return exceptionMessageSource;
    }

    @Bean(value="errorCodeMessage")
    public MessageSource errorMessage(){
        ResourceBundleMessageSource errorMessageSource= new ResourceBundleMessageSource();
        errorMessageSource.setAlwaysUseMessageFormat(true);
        errorMessageSource.setCacheMillis(3600);
        errorMessageSource.setDefaultEncoding("UTF-8");
        errorMessageSource.setDefaultLocale(Locale.ENGLISH);
        errorMessageSource.setUseCodeAsDefaultMessage(true);
        errorMessageSource.setBasename("locale/errorCode");
        return errorMessageSource;
    }
    @Bean
    public SessionLocaleResolver localeResolver(){
        SessionLocaleResolver sessionLocaleResolver=new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return sessionLocaleResolver;
    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor=new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
}