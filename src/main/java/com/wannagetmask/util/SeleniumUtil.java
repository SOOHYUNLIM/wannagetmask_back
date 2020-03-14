package com.wannagetmask.util;

import com.wannagetmask.domain.Option;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.openqa.selenium.WebDriver.*;

public class SeleniumUtil {

    private final WebDriver webDriver;
    private final JavascriptExecutor javascriptExecutor;

    private SeleniumUtil(String webDriverID, String webDriverPath) {
        System.setProperty(webDriverID, webDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        this.webDriver = new ChromeDriver();
        this.javascriptExecutor = (JavascriptExecutor) webDriver;
//        if(webDriverID.split("\\.")[1].equals("chrome")) {
//            this.webDriver = new ChromeDriver();
//            this.javascriptExecutor = (JavascriptExecutor) webDriver;
//        } else{
//            this.webDriver = new ChromeDriver();
//            this.javascriptExecutor = (JavascriptExecutor) webDriver;
//        }
    }

    public static SeleniumUtil getChrome() {
        String webDriverID = "webdriver.chrome.driver";
        String webDriverPath = "C:\\chromedriver.exe";
        return new SeleniumUtil(webDriverID, webDriverPath);
    }

    public void closeWebDriver() {
        webDriver.close();
    }

    public void crawl(String targetURL) {
        webDriver.get(targetURL);
    }

    public boolean loginNaver(String id, String pw) {
        webDriver.get("https://nid.naver.com/nidlogin.login");
        javascriptExecutor.executeScript("document.getElementsByName('id')[0].value='" + id + "'");
        javascriptExecutor.executeScript("document.getElementsByName('pw')[0].value='" + pw + "'");
        webDriver.findElement(By.id("log.login")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return webDriver.getCurrentUrl().equals("https://www.naver.com/") ? true : false;
    }

    public boolean checkStock(String url) {
        boolean isStock = false;
        Navigation navigation = webDriver.navigate();
        navigation.to(url);
        while (true) {
            try {
                webDriver.findElement(By.xpath("//*[@id=\"wrap\"]/div/div[2]/div[2]/form/fieldset/div[4]/div[2]/div[3]/span[1]/a"));
                isStock = true;
                break;
            } catch (NoSuchElementException e) {
                navigation.refresh();
            }
        }
        return isStock;
    }

    public void buy() throws InterruptedException {
        //요기는 다시 수정하자 입력받는걸루
        Select option = new Select(webDriver.findElement(By.xpath("//*[@id=\"wrap\"]/div/div[2]/div[2]/form/fieldset/div[4]/div[1]/ul/li/ul/li/div/select")));
        option.selectByValue("14020629136");


        webDriver.findElement(By.xpath("//*[@id=\"wrap\"]/div/div[2]/div[2]/form/fieldset/div[4]/div[2]/div[3]/span[1]/a")).click();
        Thread.sleep(1000);
        javascriptExecutor.executeScript("document.getElementById('generalPayments').click()");
        javascriptExecutor.executeScript("document.getElementById('pay18').click()");
        javascriptExecutor.executeScript("document.getElementById('all_agree').click()");
        javascriptExecutor.executeScript("document.getElementsByClassName('txt_order')[0].click()");
    }

    public Map<String, List<Option>> intoNaverShopping(String url) {
        crawl(url);
        String title = webDriver.findElement(By.xpath("/html/head/meta[13]")).getAttribute("content");
        List<Option> options = new Select(webDriver.findElement(By.xpath("//*[@id=\"wrap\"]/div/div[2]/div[2]/form/fieldset/div[4]/div[1]/ul/li/ul/li/div/select")))
                .getOptions().stream().map(option->Option.builder().id(option.getAttribute("value")).text(option.getText()).build()).collect(Collectors.toList());
        Map<String, List<Option>> result = new HashMap<>();
        result.put(title, options);
        return result;
    }


    public void loginKakaoAdmin(String id, String pw) {
        webDriver.get("https://accounts.kakao.com/login/kakaoforbusiness?continue=https://center-pf.kakao.com/_HFxonxb/messages/new");
        javascriptExecutor.executeScript("document.getElementsByName('email')[0].value='" + id + "'");
        javascriptExecutor.executeScript("document.getElementsByName('password')[0].value='" + pw + "'");
        webDriver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/div[8]/button")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String domain, String url) {
        Navigation navigation = webDriver.navigate();
        navigation.to("https://center-pf.kakao.com/_HFxonxb/messages/new/feed");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        javascriptExecutor.executeScript("document.getElementById('fieldRadio3Foritems[0].link.type').click()");
        javascriptExecutor.executeScript("document.getElementById('fieldRadio0Forshareable').click()");
        webDriver.findElement(By.id("messageWrite")).sendKeys(domain + " 입고!");
        webDriver.findElement(By.id("btnName")).sendKeys("구매하기");
        webDriver.findElement(By.id("linkUpload")).sendKeys(url);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        javascriptExecutor.executeScript("document.getElementsByClassName('btn_g2')[0].click()");
        webDriver.findElement(By.xpath("//*[@id=\"mArticle\"]/div/form/div[2]/button[3]")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriver.findElement(By.id("phoneNumber")).sendKeys("01027680222");
        webDriver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/div[1]/button")).click();
    }
}
