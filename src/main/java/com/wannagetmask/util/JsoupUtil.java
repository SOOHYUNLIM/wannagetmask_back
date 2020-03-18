package com.wannagetmask.util;

import com.wannagetmask.domain.Target;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupUtil {

    public boolean checkStock(Target target) throws IOException{

        Document rawData = Jsoup.connect(target.getUrl()).userAgent("Opera").get();
        int allProductCnt = rawData.getElementsByClass(target.getListTag()).size();
        int soldoutProductCnt = rawData.getElementsByClass(target.getSoldoutTag()).size();
        return allProductCnt == soldoutProductCnt ? false : true;

    }



}
