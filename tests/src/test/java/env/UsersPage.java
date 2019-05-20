package env;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static env.Constants.MAIN_PAGE;

public class UsersPage {
  private WebDriver driver;
  private WebElement createUserBtn;
  private List<UserTableElement> table;


  public UsersPage(WebDriver webDriver) {
    this.driver = webDriver;
    goToPage();
  }

  public void goToPage() {
    driver.get(MAIN_PAGE + "users");
    WebElement panel = driver.findElement(By.id("id_l.U.userPanel"));

    createUserBtn = panel.findElement(By.id("id_l.U.createNewUser"));
    WebElement usersTable = panel.findElement(By.id("id_l.U.usersList.usersList")).findElement(By.tagName("table"));
    WebElement tableBody = usersTable.findElement(By.tagName("tbody"));
    List<WebElement> tableRows = tableBody.findElements(By.tagName("tr"));
    table = tableRows.stream().map(a -> new UserTableElement(driver, a)).collect(Collectors.toList());
  }

  public UserTableElement findByLogin(String login) {
    for (UserTableElement user : table) {
      if (user.getLogin().equals(login)) return user;
    }
    return null;
  }

  public List<UserTableElement> getTable() {
    return table;
  }

  public CreateUserDialog startCreatingUser() {
    createUserBtn.click();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_l.U.cr.createUserDialog")));
    return new CreateUserDialog(driver.findElement(By.id("id_l.U.cr.createUserDialog")));
  }
}
