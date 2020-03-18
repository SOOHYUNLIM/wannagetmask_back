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

    public SeleniumUtil(String webDriverID, String webDriverPath) {
        System.setProperty(webDriverID, webDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        this.webDriver = new ChromeDriver();
        this.javascriptExecutor = (JavascriptExecutor) webDriver;
// if(webDriverID.split("\\.")[1].equals("chrome")) {
// this.webDriver = new ChromeDriver();
// this.javascriptExecutor = (JavascriptExecutor) webDriver;
// } else{
// this.webDriver = new ChromeDriver();
// this.javascriptExecutor = (JavascriptExecutor) webDriver;
// }
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
//*[@id="wrap"]/div/div[2]/div[2]/form/fieldset/div[4]/div[2]/div[2]/span[1]/a
//*[@id="content"]/div/div[2]/div[2]/form/fieldset/div[3]/div[3]/div[2]/span[1]/a
                webDriver.findElement(By.xpath("//*[@id=\"wrap\"]/div/div[2]/div[2]/form/fieldset/div[4]/div[2]/div[3]/span[1]/a"));
                isStock = true;
                break;
            } catch (NoSuchElementException e) {
                try {
                    webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div[2]/form/fieldset/div[3]/div[3]/div[2]/span[1]/a"));
                } catch (NoSuchElementException e2) {
                    navigation.refresh();
                }
            }
        }
        return isStock;
    }

    public void buy(String option) throws InterruptedException {
// webDriver.getWindowHandles();
//요기는 다시 수정하자 입력받는걸루
        if(option != null) {
            Select options = new Select(webDriver.findElement(By.xpath("//*[@id=\"wrap\"]/div/div[2]/div[2]/form/fieldset/div[4]/div[1]/ul/li/ul/li/div/select")));
            options.selectByValue(option);
        }
        try{
            webDriver.findElement(By.xpath("//*[@id=\"wrap\"]/div/div[2]/div[2]/form/fieldset/div[4]/div[2]/div[3]/span[1]/a")).click();
        } catch (Exception e1){
            System.out.println("1번 실패");
            try {
                javascriptExecutor.executeScript("document.getElementsByClassName(\"_buy_button _click(nmp.front.sellershop.product.show.sale_info.checkoutPurchase()) _stopDefault _productPreLaunch N=a:pcs.buy\")[0].click()");
            } catch (Exception e2){
                System.out.println("2번 실패");
            }
        }
        Thread.sleep(1000);
        javascriptExecutor.executeScript("document.getElementById('generalPayments').click()");
        javascriptExecutor.executeScript("document.getElementById('pay18').click()");
        javascriptExecutor.executeScript("document.getElementById('all_agree').click()");
        javascriptExecutor.executeScript("document.getElementsByClassName('txt_order')[0].click()");
    }

    public void buy(int option) throws InterruptedException {
// webDriver.getWindowHandles();
//요기는 다시 수정하자 입력받는걸루
        if(option != 0) {
            javascriptExecutor.executeScript("document.getElementById('cuid_0').value="+option);
        }
        try{
//*[@id="wrap"]/div/div[2]/div[2]/form/fieldset/div[4]/div[2]/div[2]/span[1]/a
            webDriver.findElement(By.xpath("//*[@id=\"wrap\"]/div/div[2]/div[2]/form/fieldset/div[4]/div[2]/div[3]/span[1]/a")).click();
        } catch (Exception e1){
            System.out.println("1번 실패");
            try {
                javascriptExecutor.executeScript("document.getElementsByClassName(\"_buy_button _click(nmp.front.sellershop.product.show.sale_info.checkoutPurchase()) _stopDefault _productPreLaunch N=a:pcs.buy\")[0].click()");
            } catch (Exception e2){
                System.out.println("2번 실패");
                try {
                    webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div[2]/form/fieldset/div[3]/div[3]/div[2]/span[1]/a")).click();
                } catch (Exception e3) {
                    System.out.println("3번 실패");
                }
            }
        }
        Thread.sleep(1000);
        javascriptExecutor.executeScript("document.getElementById('generalPayments').click()");
        javascriptExecutor.executeScript("document.getElementById('pay18').click()");
        javascriptExecutor.executeScript("document.getElementById('all_agree').click()");
        javascriptExecutor.executeScript("document.getElementsByClassName('txt_order')[0].click()");
    }

    public void loginMusinsa(String id, String pw) throws Exception{
        webDriver.get("https://my.musinsa.com/login/v1/login?referer=https%3A%2F%2Fstore.musinsa.com%2Fapp%2F");
        javascriptExecutor.executeScript("document.getElementsByName('id')[0].value='" + id + "'");
        javascriptExecutor.executeScript("document.getElementsByName('pw')[0].value='" + pw + "'");
        webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/form/span[3]/input")).click();
        Thread.sleep(1000);
    }
}