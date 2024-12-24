package PlayWrightPractise;

import java.nio.file.Paths;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;


public class PlayWrightInspector {

	  public static void main(String[] args) {
	    try (Playwright playwright = Playwright.create()) {
	      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
	        .setHeadless(false));
	      BrowserContext context = browser.newContext();
	      context.tracing().start(new Tracing.StartOptions()
	    		  .setScreenshots(true)
	    		  .setSnapshots(true)
	    		  .setSources(true));
	      
	      Page page = context.newPage();
	      page.navigate("https://www.sakraworldhospital.com/request-appointment");
	      
	      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Book a Health Check-up")).click();
	      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Age Specific health check-up")).click();
	      page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Paediatric").setExact(true)).click();
	      page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Paediatric").setExact(true)).click();
	      page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Paediatric").setExact(true)).dblclick();
	     
	      page.locator("li").filter(new Locator.FilterOptions().setHasText("Paediatric ₹7000 Details")).getByRole(AriaRole.LINK).click();
	      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Find a Doctor")).click();
	      page.locator("p").filter(new Locator.FilterOptions().setHasText("Director - Renal Transplant and Uro Oncology, Urology & Andrology")).click();
	      page.locator(".doctors-images-view > a").first().click();
	      Page page1 = page.waitForPopup(() -> {
	        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book an Appointment")).click();
	      });
	      page1.getByText("27").click();
	      page1.getByText("10:10 AM").click();
	     
	   // Stop tracing and export it into a zip archive.
	      context.tracing().stop(new Tracing.StopOptions()
	        .setPath(Paths.get("trace.zip")));
	      browser.close();
	      playwright.close();
	      }
	  }
	}
	

