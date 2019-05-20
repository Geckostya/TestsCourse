package tests;

import env.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UsersTableTest {
  WebDriver driver;
  private UsersPage usersPage;

  @Before
  public void startBrowser() {
    EnvironmentManager.initWebDriver();
    driver = EnvironmentManager.driver;
    new LoginPage(driver).login();
    usersPage = new UsersPage(driver);
  }

  @Test
  public void smokeTest() {
    UserTableElement root = usersPage.findByLogin("root");
    assertEquals("root", root.getFullName());
  }

  @Test
  public void createUser() {
    String userLogin = "new_user0";

    UserTableElement user = usersPage.findByLogin(userLogin);
    assertNull(user);

    CreateUserDialog dialog = usersPage.startCreatingUser();
    dialog.setFields(userLogin, "pass");
    dialog.clickOk();

    usersPage.goToPage();

    user = usersPage.findByLogin(userLogin);
    assertNotNull(user);
    assertEquals(userLogin, user.getLogin());
    user.delete();
  }

  @Test
  public void cancelUserCreation() {
    String userLogin = "new_user1";

    UserTableElement user = usersPage.findByLogin(userLogin);
    assertNull(user);

    CreateUserDialog dialog = usersPage.startCreatingUser();
    dialog.setFields(userLogin, "pass");
    dialog.clickCancel();

    usersPage.goToPage();

    user = usersPage.findByLogin(userLogin);
    assertNull(user);
  }

  @Test
  public void createUserWithSpaces() {
    String userLoginFirst = "new_user2";
    String userLogin = userLoginFirst + " with spaces";

    UserTableElement user = usersPage.findByLogin(userLogin);
    assertNull(user);

    CreateUserDialog dialog = usersPage.startCreatingUser();
    dialog.setFields(userLogin, "pass");
    dialog.clickOk();

    usersPage.goToPage();

    assertNull(usersPage.findByLogin(userLogin));
    assertNull(usersPage.findByLogin(userLoginFirst));
  }

  @Test
  public void createUserWithoutPassword() {
    String userLogin = "new_user3";

    UserTableElement user = usersPage.findByLogin(userLogin);
    assertNull(user);

    CreateUserDialog dialog = usersPage.startCreatingUser();
    dialog.setFields(userLogin, "");
    dialog.clickOk();

    usersPage.goToPage();

    assertNull(usersPage.findByLogin(userLogin));
  }

  @Test
  public void createUserWithWrongPasswordConfirm() {
    String userLogin = "new_user4";

    UserTableElement user = usersPage.findByLogin(userLogin);
    assertNull(user);

    CreateUserDialog dialog = usersPage.startCreatingUser();
    dialog.setFields(userLogin, "pass", "confirm");
    dialog.clickOk();

    usersPage.goToPage();

    assertNull(usersPage.findByLogin(userLogin));
  }

  @After
  public void tearDown() {
    EnvironmentManager.shutdown();
  }
}