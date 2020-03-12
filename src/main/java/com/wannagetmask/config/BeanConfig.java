package com.wannagetmask.config;

import com.wannagetmask.domain.Target;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class BeanConfig {

    @Qualifier("targetList")
    @Bean
    public ArrayList<Target> targetList(){
        return new ArrayList<Target>();
    }

}
