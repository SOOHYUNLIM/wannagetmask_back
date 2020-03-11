package com.wannagetmask.selenium;

import com.wannagetmask.util.SeleniumUtil;
import org.junit.jupiter.api.Test;

public class SeleniumUtilTests {

    private SeleniumUtil seleniumUtil = SeleniumUtil.getChrome();

    @Test
    public void loginTest() {
        seleniumUtil.loginNaver("tngus1337", "rlxhdqks001!");
    }
}
