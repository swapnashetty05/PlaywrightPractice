package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Page;

public class PlayWrightFactory {

	Playwright playWright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	Properties prop;

	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();

	public static Playwright getPlaywright() {
		return tlPlaywright.get();
	}

	public static Browser getBrowser() {
		return tlBrowser.get();
	}

	public static BrowserContext getBrowserContext() {
		return tlBrowserContext.get();
	}

	public static Page getPage() {
		return tlPage.get();
	}

	public Page initBrowser(Properties prop) {

		String browserName = prop.getProperty("browser").trim();
		System.out.println("browser name : " + browserName);

		// playWright = Playwright.create();

		tlPlaywright.set(Playwright.create());

		switch (browserName.toLowerCase()) {

		case "chromium":
			// browser = playWright.chromium().launch(new
			// BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		case "firefox":
			// browser = playWright.firefox().launch(new
			// BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		case "safari":
			// browser = playWright.webkit().launch(new
			// BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		case "chrome":
			// browser = playWright.chromium().launch(new
			// BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
			tlBrowser.set(getPlaywright().chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;

		default:
			System.out.println("Please enter proper browser name....");
			break;
		}
		/*
		 * browserContext = browser.newContext(); page = browserContext.newPage();
		 * page.navigate(prop.getProperty("url").trim()); return page;
		 */

		tlBrowserContext.set(getBrowser().newContext());
		tlPage.set(getBrowserContext().newPage());
		getPage().navigate(prop.getProperty("url").trim());
		return getPage();
	}

	/**
	 * This method used to initialize properties from config file
	 */
	public Properties init_prop() {

		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			prop.load(ip);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;

	}

	/**
	 * take screenshot
	 * 
	 */

	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		/*
		 getPage().screenshot(new
		 Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

		 return path;
		 */
		 
		byte[] buffer;

		buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		String base64Path = Base64.getEncoder().encodeToString(buffer);

		return base64Path;
		
	}

}
