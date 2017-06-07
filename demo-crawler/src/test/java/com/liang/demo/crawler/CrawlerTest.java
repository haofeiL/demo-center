package com.liang.demo.crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;


public class CrawlerTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.setProperty("webdriver.chrome.driver", "d:\\development\\chromedriver.exe");
        DesiredCapabilities caps = new DesiredCapabilities();
        ((DesiredCapabilities) caps).setJavascriptEnabled(true);
        /*((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "D:\\development\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");*/
        ((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX + "Cookie",
                "aliyungf_tc=AQAAADMtBFQkWwEA+q44OlEg8+ljFV5k; acw_tc=AQAAACg9yVXV7QIA+q44OtksJoKGo2eW; q_c1=774e986b9bc14122bfc6ddbd5df1da25|1493878005000|1493878005000; _xsrf=3ee00a302fe6d89052d6776dadccf20f; r_cap_id=\"ZWFkYmI2ZGNlNzc4NDY5OWFlMTMyYTgzNjg4ZWVlZDE=|1493878005|43d0acc21e69d3cca5287b7ed6c135dfa9166626\"; cap_id=\"OTEwMGUwOGVmNzhlNDA1YTkzMGRjZmQ5MjAwZjRiMmE=|1493878005|934db29d398373a31764bee0fba9843cb652e1e0\"; d_c0=\"AFBCjgOYtAuPTolPL0drB8XLqMoTmNiuOlk=|1493878006\"; _zap=3792ab9c-4023-4c5b-910a-52485911818d; l_n_c=1; z_c0=Mi4wQUJDTXYxSWtCZ2tBVUVLT0E1aTBDeGNBQUFCaEFsVk5BMUl5V1FBYmF5ZnBCRGNQUmRBYjRndGZDdDBmQzZ4SGdR|1493878022|4d0d8e31e836bf19fc9f1b52a62c6c2855ef9ee4; __utma=51854390.668001633.1493878029.1493878029.1493878029.1; __utmb=51854390.0.10.1493878029; __utmc=51854390; __utmz=51854390.1493878029.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmv=51854390.100--|2=registration_date=20151118=1^3=entry_date=20151118=1");
//		caps.setCapability("takesScreenshot", false);

        // ((DesiredCapabilities)
        // caps).setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
        // "D:\\development\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
//		((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
//				"/usr/soft/phantomjs-2.1.1-linux-x86_64/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "D:\\development\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "userAgent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "resourceTimeout",
                10000);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "loadImages",
                true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[]{
                "--web-security=false",
                "--ignore-ssl-errors=true",
                "--disk-cache=true"
        });

		/*WebDriver driver = new PhantomJSDriver(caps);
        driver.get("https://www.zhihu.com/#signin");
		System.out.println(driver.getPageSource());*/

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.zhihu.com/");
        driver.manage().deleteAllCookies();
        String cookieStr = "aliyungf_tc=AQAAADMtBFQkWwEA+q44OlEg8+ljFV5k; acw_tc=AQAAACg9yVXV7QIA+q44OtksJoKGo2eW; q_c1=774e986b9bc14122bfc6ddbd5df1da25|1493878005000|1493878005000; _xsrf=3ee00a302fe6d89052d6776dadccf20f; r_cap_id=\"ZWFkYmI2ZGNlNzc4NDY5OWFlMTMyYTgzNjg4ZWVlZDE=|1493878005|43d0acc21e69d3cca5287b7ed6c135dfa9166626\"; cap_id=\"OTEwMGUwOGVmNzhlNDA1YTkzMGRjZmQ5MjAwZjRiMmE=|1493878005|934db29d398373a31764bee0fba9843cb652e1e0\"; d_c0=\"AFBCjgOYtAuPTolPL0drB8XLqMoTmNiuOlk=|1493878006\"; _zap=3792ab9c-4023-4c5b-910a-52485911818d; l_n_c=1; z_c0=Mi4wQUJDTXYxSWtCZ2tBVUVLT0E1aTBDeGNBQUFCaEFsVk5BMUl5V1FBYmF5ZnBCRGNQUmRBYjRndGZDdDBmQzZ4SGdR|1493878022|4d0d8e31e836bf19fc9f1b52a62c6c2855ef9ee4; __utma=51854390.668001633.1493878029.1493878029.1493878029.1; __utmb=51854390.0.10.1493878029; __utmc=51854390; __utmz=51854390.1493878029.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmv=51854390.100--|2=registration_date=20151118=1^3=entry_date=20151118=1";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String[] arr1 = cookieStr.split(";");
        String key = null;
        String val = null;
        for (String str : arr1) {
            key = "";
            val = "";
            System.out.println(str);
            String[] arr2 = str.trim().split("=");
            if (arr2.length == 2) {
                key = arr2[0].trim();
                val = arr2[1].trim();
            } else if (arr2.length > 2) {
                key = arr2[0].trim();
                for (int i = 1, size = arr2.length; i < size; i++) {
                    if (StringUtils.isBlank(val)) {
                        val += arr2[i].trim();
                    } else {
                        val += "=" + arr2[i].trim();
                    }
                }
            }
            System.out.println(key + "=" + val);
            js.executeScript("document.cookie='" + key + "=" + val + "'");
        }
        System.out.println(driver.manage().getCookies());
        driver.get("https://www.zhihu.com/#signin");

//		WebDriver driver = new PhantomJSDriver(caps);
        /*((DesiredCapabilities) caps).setCapability("chrome.switches",
                Arrays.asList("--start-maximized"));*/
//		ChromeOptions options = new ChromeOptions();
//		PhantomJSDriver driver = new PhantomJSDriver();
//		options.addArguments("--test-type", "--start-maximized");
//		WebDriver driver = new ChromeDriver(options);
//		WebDriver driver = new HtmlUnitDriver();
//		System.out.println(crawlerZh(driver));
		
		/*PhantomJSDriver driver = new PhantomJSDriver(caps);
		driver.manage().timeouts().setScriptTimeout(2, TimeUnit.SECONDS);
		driver.executePhantomJS( "alertmsg='';var page = this;page.onAlert = function(msg) {alertmsg=msg;};");
		driver.get("http://www.dreamdu.com/javascript/exe_window.alert/");*/
		/*driver.get("https://passport.jd.com/new/login.aspx");
		CrawlerUtils.sleep(2000);
		WebElement user = driver.findElement(By.id("loginname"));
		WebElement password = driver.findElement(By.id("nloginpwd"));
		WebElement submit = driver.findElement(By.id("loginsubmit"));
		user.sendKeys("15764236507");
		password.sendKeys("yanan0318");
		submit.click();
		CrawlerUtils.sleep(2000);
		
		WebElement code = driver.findElement(By.id("code"));
		code.sendKeys("111111");
		submit = driver.findElement(By.id("submitBtn"));
		submit.click();
		CrawlerUtils.sleep(500);*/
		/*String alertmessage = (String)((PhantomJSDriver)driver).executePhantomJS("var page = this; return alertmsg;");*/
		/*System.out.println("验证码状态=============================="+alertmessage);
		WebElement info = CrawlerUtils.findElementByDriver(driver, "code-msg", CrawlerUtils.ID);
		String infoText = info == null ? ""  : info.getText();
		System.out.println("infoText=========="+infoText);*/
		
		/*long startTime = System.currentTimeMillis();
		int i = 0;
		while(true)
		{
			//打印
			WebElement sendMsg = driver.findElement(By.id("sendMobileCode"));
			System.out.println("sendmsg========"+sendMsg.isDisplayed()+"=============="+sendMsg.isEnabled());
			CrawlerUtils.sleep(1000);
			i++;
			if(i > 3 * 60)
			{
				break;
			}
		}
		System.out.println("循环完成i====="+i+"，time===="+(System.currentTimeMillis() - startTime));*/
		/*driver.quit();*/
    }

    public static Map<String, String> crawlerZh(WebDriver driver) {
        Map<String, String> map = new HashMap();
//		登陆
        driver.get("https://www.zhihu.com/#signin");
        System.out.println(driver.getCurrentUrl());
        List<String> urls = new ArrayList();
        urls.add("https://www.zhihu.com/people/mu-yi-43-58/followees");
        List<String> totalUrls = new ArrayList();
//		crawlerZh(totalUrls, urls, driver);
        System.out.println("***********end" + totalUrls.size());
        System.out.println("***********end" + totalUrls);
		/*WebElement loginname = CrawlerUtils.findElementByDriver(driver, "input[name='account']", "css");
		loginname.sendKeys("15865265075");
		WebElement loginpwd = CrawlerUtils.findElementByDriver(driver, "input[name='password']", "css");
		CrawlerUtils.sleep(2000);
		loginpwd.sendKeys("XJw239386839");
		WebElement searchButton = CrawlerUtils.findElementByDriver(driver,"button[class='sign-button submit']", "css");
		searchButton.click();
		CrawlerUtils.sleep(2000);
		System.out.println(driver.getCurrentUrl());*/
		/*driver.get("https://www.zhihu.com/people/mu-yi-43-58/followees");
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		List<WebElement> webElements = CrawlerUtils.findElementsByDriver(driver, 
				"div[class='zm-profile-card zm-profile-section-item zg-clear no-hovercard']", 
				"css");
		int urlSize = webElements.size();
		while(true)
		{
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			CrawlerUtils.sleep(1000);
			webElements = CrawlerUtils.findElementsByDriver(driver, 
					"div[class='zm-profile-card zm-profile-section-item zg-clear no-hovercard']", 
					"css");
			if(urlSize == webElements.size())
			{
				break;
			}
			urlSize = webElements.size();
		}
		System.out.println(webElements.size());
		List<String> urls = new ArrayList<>();
		for(WebElement we : webElements)
		{
			WebElement tempWe = CrawlerUtils.findElementByElement(we, "a[class='zg-link author-link']", "css");
			urls.add(tempWe.getAttribute("href"));
		}
		System.out.println(urls);*/
//		获取关注人
//		关注人的关注人（递归）
		/*driver.get("https://www.zhihu.com/people/mu-yi-43-58/followees");
		System.out.println(driver.getCurrentUrl());
		List<WebElement> webElements = CrawlerUtils.findElementsByDriver(driver, 
				"div[class='zm-profile-card zm-profile-section-item zg-clear no-hovercard']", 
				"css");
		System.out.println(webElements.size());*/
        return map;
    }
	
	/*public static void crawlerZh(List<String> totalAccounts, List<String> accounts, WebDriver driver)
	{
		List<String> newAccount = new ArrayList<>();
		for(String account : accounts)
		{
			if(!account.contains("/followees"))
			{
				account += "followees";
			}
			driver.get(account);
			newAccount.addAll(crawlerAccount(driver));
		}
		System.out.println("************迭代中"+newAccount.size());
		System.out.println("************迭代中"+newAccount);
		totalAccounts.addAll(newAccount);
		crawlerZh(totalAccounts, newAccount, driver);
		if(totalAccounts.size() > 1000)
		{
			return;
		}*/
		/*if(accounts.size() > 1000)
		{
//			写文件
			accounts.clear();
		}*/
//		crawlerZh(newAccount, driver);
/*//		登陆
		driver.get("https://www.zhihu.com/#signin");
		System.out.println(driver.getCurrentUrl());
		WebElement loginname = CrawlerUtils.findElementByDriver(driver, "input[name='account']", "css");
		loginname.sendKeys("15865265075");
		WebElement loginpwd = CrawlerUtils.findElementByDriver(driver, "input[name='password']", "css");
		CrawlerUtils.sleep(2000);
		loginpwd.sendKeys("XJw239386839");
		WebElement searchButton = CrawlerUtils.findElementByDriver(driver,"button[class='sign-button submit']", "css");
		searchButton.click();
		CrawlerUtils.sleep(2000);
		System.out.println(driver.getCurrentUrl());
		driver.get("https://www.zhihu.com/people/mu-yi-43-58/followees");
		
		accounts.addAll(urls);*/

}
	
	/*public static List<String> crawlerAccount(WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		List<WebElement> webElements = CrawlerUtils.findElementsByDriver(driver, 
				"div[class='zm-profile-card zm-profile-section-item zg-clear no-hovercard']", 
				"css");
		int urlSize = webElements.size();
		while(true)
		{
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			CrawlerUtils.sleep(1000);
			webElements = CrawlerUtils.findElementsByDriver(driver, 
					"div[class='zm-profile-card zm-profile-section-item zg-clear no-hovercard']", 
					"css");
			if(urlSize == webElements.size())
			{
				break;
			}
			urlSize = webElements.size();
		}
		List<String> urls = new ArrayList<>();
		for(WebElement we : webElements)
		{
			WebElement tempWe = CrawlerUtils.findElementByElement(we, "a[class='zg-link author-link']", "css");
			urls.add(tempWe.getAttribute("href"));
		}
		return urls;
	}*/
	
	/*public static Map<String, String> crawlerJd(WebDriver driver)
	{
		driver.get("http://channel.jd.com/fashion.html");
		List<WebElement> webelements = CrawlerUtils.findElementsByDriver(driver,
				"a[target='_blank']",
				"css");
		Map<String, String> map = new HashMap<>();
		String itemName = null;
		String itemPrice = null;
		List<String> urlList = new ArrayList<>();
		try
		{
			for(WebElement we : webelements)
			{
				System.out.println(we.getAttribute("href"));
				if(we.getAttribute("href").contains("item.jd.com"))
				{
					System.out.println("*********************"+we.getAttribute("href"));
					String detailUrl = we.getAttribute("href");
					if(urlList.contains(detailUrl))
					{
						continue;
					}
					urlList.add(detailUrl);
					*//*driver.get(detailUrl);
					System.out.println(driver.getCurrentUrl());*//*
					*//*WebElement web = CrawlerUtils.findElementByDriver(driver,
							"#name > h1",
							"css");
					if(web == null)
					{
						continue;
					}
					itemName = web.getText();
					web = CrawlerUtils.findElementByDriver(driver,
							"#jd-price",
							"css");
					if(web == null)
					{
						continue;
					}
					itemPrice = web.getText();
					if(StringUtils.isBlank(itemName) || StringUtils.isBlank(itemPrice))
					{
						continue;
					}
					map.put(itemName, itemPrice);*//*
				}
			}
		}
		finally
		{
			System.out.println(urlList);
		}

		int i = 0;
		for(String detailUrl : urlList)
		{
			if(i > 10000)
			{
				break;
			}
			driver.get(detailUrl);
			WebElement web = CrawlerUtils.findElementByDriver(driver,
					"#name > h1",
					"css");
			if(web == null)
			{
				continue;
			}
			itemName = web.getText();
			web = CrawlerUtils.findElementByDriver(driver,
					"#jd-price",
					"css");
			if(web == null)
			{
				continue;
			}
			itemPrice = web.getText();
			if(StringUtils.isBlank(itemName) || StringUtils.isBlank(itemPrice))
			{
				continue;
			}
			map.put(itemName, itemPrice);
			i++;
		}
		System.out.println(i);
		System.out.println(map);
		return map;*/
		
		/*WebDriver driver = new PhantomJSDriver(caps);
		String url = "http://item.jd.com/";
		WebElement webElement = null;
		long i = 0L;
		String itemName = "";
		String itemPrice = "";
		Map<String, String> map = new HashMap<>();
		int count = 1;
		while(i < 1000000000)
		{
			i++;
			driver.get(url+i+".html");
			System.out.println(driver.getCurrentUrl());
			if(driver.getCurrentUrl().contains("http://www.jd.com/"))
			{
				continue;
			}
			WebElement webelement = CrawlerUtils.findElementByDriver(driver, 
					"#name > h1",
					"css");
			itemName = webelement.getText();
			webelement = CrawlerUtils.findElementByDriver(driver, 
					"#jd-price",
					"css");
			itemPrice = webelement.getText();
			if(StringUtils.isBlank(itemName) || StringUtils.isBlank(itemPrice))
			{
				continue;
			}
			System.out.println("*****************************************************************************"+count);
			map.put(itemName, itemPrice);
			count++;
			if(count % 10 == 0)
			{
				File resourceFilePath = new File("D:\\jd");
		        if (!resourceFilePath.exists())
		        {
		            resourceFilePath.mkdirs();
		        }
		        File file = new File("D\\jd\\jd"+(count/100000)+".xls");
		        FileOutputStream fileOutput = new FileOutputStream(file);
		        HSSFWorkbook wb = new HSSFWorkbook();
		        HSSFSheet sheet = wb.createSheet("1");
		        //标题
		        HSSFRow row = sheet.createRow(0);
		        HSSFCell cell0 = row.createCell(0);
		        cell0.setCellValue("商品名称");
		        HSSFCell cell1 = row.createCell(1);
		        cell1.setCellValue("商品价格");
		        
		        Iterator iter = map.entrySet().iterator();
		        int rownum = 1;
		        while(iter.hasNext())
		        {
		        	Entry entry = (Entry)iter.next();
		        	row = sheet.createRow(rownum);
		        	cell0 = row.createCell(0);
			        cell0.setCellValue(String.valueOf(entry.getKey()));
			        cell1 = row.createCell(1);
			        cell0.setCellValue(String.valueOf(entry.getValue()));
		        	rownum++;
		        }
		        wb.write(fileOutput);
		        fileOutput.close();
				
			}
		}*/
//*[@id="track11212182076"]/td[4]/div/a[2]
		/*WebDriver driver = new PhantomJSDriver(caps);
		driver.get("http://item.jd.com/190006.html");
		WebElement webelement = CrawlerUtils.findElementByDriver(driver, 
				"#name > h1",
				"css");
		System.out.println(webelement.getText());
		webelement = CrawlerUtils.findElementByDriver(driver, 
				"#jd-price",
				"css");
		System.out.println(webelement.getText());*/
		
		/*WebDriver driver = new PhantomJSDriver(caps);
		driver.get("https://passport.jd.com/new/login.aspx");
		WebElement loginname = driver.findElement(By.id("loginname"));
		loginname.sendKeys("jingwen_1985");
		WebElement loginpwd = driver.findElement(By.id("nloginpwd"));
		loginpwd.sendKeys("XJw239386839");
		WebElement searchButton = driver.findElement(By.id("loginsubmit"));
		searchButton.click();
		CrawlerUtils.sleep(2000);
		
		
		driver.get("http://details.jd.com/normal/item.action?orderid=220501711&PassKey=2A0DD0AC2A238021BBC86D8C707E41C7");
		System.out.println(driver.getCurrentUrl());
		WebElement webele = CrawlerUtils.findElementByDriver(driver, 
				"//*[@id='container']/div[2]/div/div[4]/div/div/div[1]/div/div[2]/div[2]",
				"xpath");
		List<WebElement> elements = webele.findElements(By.cssSelector(".item"));
		System.out.println(elements.size());
		Map<String, String> map = new HashMap<>();
		for(WebElement we : elements)
		{
			map.put(CrawlerUtils.findElementByElement(we, "span", "css").getText(), 
					CrawlerUtils.findElementByElement(we, "div", "css").getText());
		}
		System.out.println(map);*/

