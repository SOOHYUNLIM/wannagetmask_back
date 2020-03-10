package com.wannagetmask.util;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

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

    public void loginNaver(String id, String pw) throws InterruptedException {
        webDriver.get("https://nid.naver.com/nidlogin.login");
        javascriptExecutor.executeScript("document.getElementsByName('id')[0].value='" + id + "'");
        javascriptExecutor.executeScript("document.getElementsByName('pw')[0].value='" + pw + "'");
        webDriver.findElement(By.id("log.login")).click();
        Thread.sleep(1000);
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

    public Select intoNaverShopping(String url) {
        crawl(url);
        return new Select(webDriver.findElement(By.xpath("//*[@id=\"wrap\"]/div/div[2]/div[2]/form/fieldset/div[4]/div[1]/ul/li/ul/li/div/select")));
    }
}
