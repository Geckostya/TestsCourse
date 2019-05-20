package env;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;

public class UserTableElement {
  private final WebElement loginData;
  private final WebElement fullNameData;
  private final WebElement emailJabberData;
  private final WebElement groupsData;
  private final WebElement lastAccessData;
  private final WebElement buttonsData;
  private final WebDriver driver;

  UserTableElement(WebDriver driver, WebElement tableRow) {
    this.driver = driver;
    List<WebElement> data = tableRow.findElements(By.tagName("td"));
    loginData = data.get(0);
    fullNameData = data.get(1);
    emailJabberData = data.get(2);
    groupsData = data.get(3);
    lastAccessData = data.get(4);
    buttonsData = data.get(5);
  }

  public String getLogin() {
    return loginData.findElement(By.tagName("a")).getAttribute("title");
  }

  public String getFullName() {
    return fullNameData.findElement(By.tagName("div")).getAttribute("title");
  }

  public String getEmail() {
    return emailJabberData.findElements(By.tagName("div")).get(0).getAttribute("title");
  }

  public String getJabber() {
    List<WebElement> elements = emailJabberData.findElements(By.tagName("div"));
    return elements.size() >= 2 ? elements.get(1).getAttribute("title") : "";
  }

  private Optional<WebElement> findButton(String buttonText) {
    return buttonsData.findElements(By.tagName("a")).stream().filter(b -> b.getText().equals(buttonText)).findFirst();
  }

  public void delete() {
    Optional<WebElement> deleteBtn = findButton("Delete");
    if (deleteBtn.isPresent()) {
      deleteBtn.get().click();
      new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent()).accept();
    }
  }
}
