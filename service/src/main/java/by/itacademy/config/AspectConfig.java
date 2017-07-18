package by.itacademy.config;

import by.itacademy.aspect.LogExecutedMethodsAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Yury V. on 18.07.17.
 */

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

    @Bean
    public LogExecutedMethodsAspect logExecutedMethodsAspect() {
        return new LogExecutedMethodsAspect();
    }

}
