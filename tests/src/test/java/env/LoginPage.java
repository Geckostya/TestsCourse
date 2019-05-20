package env;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
  private WebDriver driver;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
    goToPage(driver);
  }

  private void goToPage(WebDriver driver) {
    driver.get(Constants.MAIN_PAGE + "login");
  }

  public void login() {
    driver.findElement(By.id("id_l.L.login")).sendKeys(Constants.ROOT_LOGIN);
    driver.findElement(By.id("id_l.L.password")).sendKeys(Constants.ROOT_PASSWORD);
    driver.findElement(By.id("id_l.L.loginButton")).click();
  }
}
