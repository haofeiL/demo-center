package com.liang.demo.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;
import java.util.Set;

/**
 * Created by haofeiL on 2017/6/7.
 */
public class TbCrawlerTest {
    public static void main(String[] args) throws Exception {
        WebDriver driver = null;
        for (int i = 0; i < 10; i++) {
            System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("https://www.taobao.com/");

            WebElement womenSwear = driver.findElement(By.cssSelector("body > div.screen-outer.clearfix > div.main > div.tbh-service.J_Module > div > ul > li:nth-child(1) > span > a:nth-child(1)"));
            String windowHandle = driver.getWindowHandle();
            womenSwear.click();
            Thread.sleep(2000);
            Set<String> windowHandles = driver.getWindowHandles();
            for (String str : windowHandles) {
                if (!str.equals(windowHandle)) {
                    driver.switchTo().window(str);
                }
            }

//        driver.get("https://www.taobao.com/markets/nvzhuang/taobaonvzhuang?spm=a21bo.50862.201867-main.2.gjU6zB&qq-pf-to=pcqq.c2c");

            WebElement ele = driver.findElement(By.cssSelector(".opt"));
            System.out.println(ele);
            Actions actions = new Actions(driver);
            actions.moveToElement(ele).perform();

//        ele.click();
            ele = driver.findElement(By.cssSelector(".opt-drop"));
            ele.click();


            driver.findElement(By.id("q")).sendKeys("球球球的神秘商店");
            driver.findElement(By.cssSelector(".local")).click();

            Thread.sleep(2000);

            List<WebElement> elementList = driver.findElements(By.cssSelector(".shop-name"));
            for (WebElement webElement : elementList) {
                System.out.println(webElement.getText());
                if (webElement.getText().contains("球球球的神秘商店")) {
                    webElement.click();
                }
            }
            driver.close();
            Thread.sleep(1000);
        }

    }
}
