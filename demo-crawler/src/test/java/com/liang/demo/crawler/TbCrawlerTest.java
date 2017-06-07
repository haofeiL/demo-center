package com.liang.demo.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by haofeiL on 2017/6/7.
 */
public class TbCrawlerTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.taobao.com/");

//        driver.get("https://huaile.tmall.com/shop/view_shop.htm?spm=a230r.7195193.1997079397.2.YcKaul");
        WebElement womenSwear = driver.findElement(By.cssSelector("body > div.screen-outer.clearfix > div.main > div.tbh-service.J_Module > div > ul > li:nth-child(1) > span > a:nth-child(1)"));
        womenSwear.click();

        //点击店铺
        driver.findElement(By.className("search-wrap")).findElements(By.tagName("span")).get(0).click();
        driver.findElement(By.className("search-wrap")).findElements(By.tagName("span")).get(1).click();

    }
}
