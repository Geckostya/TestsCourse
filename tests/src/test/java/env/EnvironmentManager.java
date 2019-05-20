package env;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static env.Constants.WEB_DRIVER_PATH;

public class EnvironmentManager {
  public static WebDriver driver;

  public static void initWebDriver() {
    System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
    if (driver != null) {
      shutdown();
    }
    driver = new ChromeDriver();
//    RunEnvironment.setWebDriver(driver);
  }

  public static void shutdown() {
    if (driver != null) {
      driver.quit();
    }
    driver = null;
  }
}
