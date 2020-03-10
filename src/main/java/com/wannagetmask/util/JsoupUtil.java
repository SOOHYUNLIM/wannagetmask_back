package com.wannagetmask.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import javax.swing.text.Document;
import java.io.IOException;

public class JsoupUtil {

    private final Connection jsoupConnection;

    public JsoupUtil(String url) {
        this.jsoupConnection = Jsoup.connect(url);
    }

    public void test() {
        try {
            System.out.println(jsoupConnection.get().getElementsByTag("option"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
