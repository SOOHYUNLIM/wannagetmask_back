package com.wannagetmask.util;

import org.junit.jupiter.api.Test;

public class SeleniumUtilTests {

    private SeleniumUtil seleniumUtil = SeleniumUtil.getChrome();

    @Test
    public void sendMsgTest() {
        seleniumUtil.loginKakaoAdmin("tjsvndrlskfk@naver.com", "tngus645312");
        seleniumUtil.sendMessage("쿠팡", "https://www.coupang.com/vp/products/71343011?itemId=238087169&vendorItemId=3584992631&q=%ED%83%90%EC%82%AC+%EB%A7%88%EC%8A%A4%ED%81%AC&itemsCount=30&searchId=6c3f8e43e15f4b9484491833a8b04d39&rank=0&isAddedCart=");
    }

    @Test
    public void loginTest() {
        seleniumUtil.loginNaver("tngus1337", "rlxhdqks001!");
        seleniumUtil.loginKakaoAdmin("tjsvndrlskfk@naver.com", "tngus645312");
    }
}
