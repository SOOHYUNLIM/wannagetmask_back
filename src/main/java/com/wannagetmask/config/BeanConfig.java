package com.wannagetmask.config;

import com.wannagetmask.domain.Target;
import com.wannagetmask.repository.TargetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final TargetRepository targetRepository;

    @Qualifier("targetList")
    @Bean
    public List<Target> targetList(){
        return targetRepository.findAll();
    }

}
