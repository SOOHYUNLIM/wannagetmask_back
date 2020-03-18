package com.wannagetmask.util;

import com.wannagetmask.domain.Target;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JsoupUtil {

    public boolean checkStock(Target target) {
        boolean result = false;
        try{
            Document rawData = Jsoup.connect(target.getUrl()).userAgent("Opera").get();
            int allProductCnt = rawData.getElementsByClass(target.getListTag()).size();
            int soldoutProductCnt = rawData.getElementsByClass(target.getSoldoutTag()).size();
            result = allProductCnt == soldoutProductCnt ? false : true;
        } catch (Exception e) {
            log.error(target.getDomain() + "페이지 로딩 불가!!");
        }
        return result;
    }

}
