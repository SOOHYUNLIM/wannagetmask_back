package com.wannagetmask.util;

import com.wannagetmask.domain.TargetCrawled;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TargetUtil {

    // 상품리스트페이지 URL
    private String productsPage;

    // 유효한 상품 URL LIST
    private List<TargetCrawled> lastTargetList = new ArrayList<>();

    public TargetUtil(String productsPage) {
        this.productsPage = productsPage;
    }

    public List<TargetCrawled> getVaildTargetUrls() throws IOException {

        Document rawData = Jsoup.connect(this.productsPage).userAgent("Opera").get();
        Elements elements = rawData.select(".search-product a");
        List<String> allUrls = new LinkedList<>();

        for (Element ele : elements) {
            allUrls.add("https://www.coupang.com" + ele.attr("href"));
        }

        // 유효한 상품인지 확인
        for (Iterator<String> iter = allUrls.iterator(); iter.hasNext(); ) {
            String targetUrl = iter.next();
            Document tmpDoc = Jsoup.connect(targetUrl).userAgent("Opera").get();
            if (tmpDoc.select(".prod-price-container").first() == null) {
                // 완전히 판매 중지된 상품은 리스트에서 제외
                iter.remove();
            } else {
                String prodCode = tmpDoc.getElementById("contents").attr("data-product-id");
                lastTargetList.add(TargetCrawled.builder().prodCode(prodCode).url(targetUrl).build());
            }
        }

        return this.lastTargetList;
    }

}
