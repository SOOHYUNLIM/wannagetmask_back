package com.wannagetmask.scheduler;

import com.wannagetmask.domain.TargetCrawled;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckTargetsScheduler {

    // db에서 타겟list를 가져와야함===========!!!!!!!!!!!!!!
    private List<TargetCrawled> regularCheckList = new ArrayList<>();

    public void regularCheck() {
        try {
            for(TargetCrawled target : regularCheckList) {
                Document rawData = Jsoup.connect(target.getUrl()).userAgent("Opera").get();
                Element ele = rawData.select(".oos-label").first();
                if(ele.toString()==null) {
                    System.out.println("일시품절이 아닌 상품 URL");
                    // 이 url이 알람 떠야함!!!!!!!!!!
                    System.out.println(target.getUrl());
                } else {
                    System.out.println("일시품절");
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

}
