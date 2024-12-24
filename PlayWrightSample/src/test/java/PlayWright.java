import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlayWright {

	public static void main(String[] args) {
		
		Playwright playWright = Playwright.create();
		
		//Browser browser = playWright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		
		LaunchOptions lp = new LaunchOptions();
		lp.setChannel("chrome");
		lp.setHeadless(false);
		
		Browser browser = playWright.chromium().launch(lp);
		
		Page page = browser.newPage();
		//page.navigate("https://www.sakraworldhospital.com/request-appointment.php");
	
		page.navigate("https://www.youtube.com");

		
		String title = page.title();
		System.out.println("title is : "+ title);
		
		String url = page.url();
		System.out.println("url is : "+ url);
		
		browser.close();
		playWright.close();
	}

}
