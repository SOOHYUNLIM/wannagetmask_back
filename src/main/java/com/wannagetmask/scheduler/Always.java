package com.wannagetmask.scheduler;

import com.wannagetmask.domain.Target;
import com.wannagetmask.repository.TargetRepository;
import com.wannagetmask.util.JsoupUtil;
import com.wannagetmask.util.MyThread;
import com.wannagetmask.util.SeleniumUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Always implements ApplicationRunner {

    @Qualifier("targetList")
    private final List<Target> targetList;
    private final JsoupUtil jsoupUtil;
    private final MyThread myThread;
    private final SeleniumUtil seleniumUtil = SeleniumUtil.getChrome();

    @Override
    public void run(ApplicationArguments args) {
        targetList.forEach(target -> log.info(target.toString()));
//        while (true) {
//            targetList.forEach(target -> myThread.run(()->{
//                log.info(Thread.currentThread().getName()+"현재 쓰레드");
//                log.info(target.getDomain()+" 검사 대상");
//                jsoupUtil.checkStock(target);
//                log.info("============검사종료===========");
//            }));
//        }
        while (true) {
            targetList.forEach(target -> {
                log.info(target.getDomain() + " 검사 대상");
                boolean isStock = jsoupUtil.checkStock(target);
                if(isStock) {
                    log.info("빨리 문자 날려주세요!!!");
                    seleniumUtil.loginKakaoAdmin("tjsvndrlskfk@naver.com", "tngus645312");
                    seleniumUtil.sendMessage(target.getDomain(), target.getUrl());
                }
                log.info("============검사종료=========== "+isStock);
            });
        }
    }
}
