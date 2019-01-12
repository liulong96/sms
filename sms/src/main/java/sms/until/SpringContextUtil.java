package sms.until;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Locale;

public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContextUtil.context = context;
    }

    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> clazz){
        return context.getBean(beanName, clazz);
    }

    public static <T> T getBean (Class<T> clazz){
        return context.getBean(clazz);
    }

    public static String getMessage(String key){

        return context.getMessage(key, null, Locale.getDefault());
    }


}
