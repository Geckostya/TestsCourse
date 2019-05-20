package env;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CreateUserDialog {
  private final WebElement okButton;
  private final WebElement cancelButton;
  private final WebElement loginInput;
  private final WebElement passwordInput;
  private final WebElement confirmPasswordInput;

  CreateUserDialog(WebElement dialog) {
    WebElement btnPanel = dialog.findElement(By.className("jt-bl-south"))
        .findElement(By.className("jt-panel"))
        .findElement(By.className("jt-panel-content"));
    okButton = btnPanel.findElement(By.id("id_l.U.cr.createUserOk"));
    cancelButton = btnPanel.findElement(By.id("id_l.U.cr.createUserCancel"));

    WebElement fieldsPanel = dialog.findElement(By.className("jt-bl-wrapper"))
        .findElement(By.className("jt-bl-center"))
        .findElement(By.className("jt-panel"))
        .findElement(By.className("jt-panel-content"));
    List<WebElement> fieldPanels = fieldsPanel.findElements(By.tagName("div"));
//    WebElement other = fieldPanels.get(1).findElement(By.className("jt-fieldset"));
    List<WebElement> credentials = fieldPanels.get(0)
        .findElement(By.className("jt-fieldset"))
        .findElements(By.tagName("div"));

    loginInput = credentials.get(0).findElement(By.tagName("input"));
    passwordInput = credentials.get(1).findElement(By.tagName("input"));
    confirmPasswordInput = credentials.get(2).findElement(By.tagName("input"));
  }

  public void setFields(String login, String password, String confirmPassword) {
    loginInput.sendKeys(login);
    passwordInput.sendKeys(password);
    confirmPasswordInput.sendKeys(confirmPassword);
  }

  public void setFields(String login, String password) {
    setFields(login, password, password);
  }

  public void clickOk() {
    okButton.click();
  }

  public void clickCancel() {
    cancelButton.click();
  }
}
