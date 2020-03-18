package com.wannagetmask.selenium;

import com.wannagetmask.domain.Target;
import com.wannagetmask.util.JsoupUtil;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class JsoupUtilTests {

    JsoupUtil jUtil = new JsoupUtil();

    @Test
    public void checkStockTest() throws IOException {
        System.out.println("===========================");
        System.out.println(jUtil.checkStock(Target.builder().url("http://www.welkeepsmall.com/shop/shopbrand.html?type=X&xcode=023").domain("웰킵스").listTag("tb-center").soldoutTag("soldout").build()));
    }
}
