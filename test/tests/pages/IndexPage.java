package tests.pages;

import static org.fluentlenium.core.filter.FilterConstructor.withId;
import static org.fluentlenium.core.filter.FilterConstructor.withText;
import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

public class IndexPage extends FluentPage {
  private String url;

  public IndexPage(WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port;
  }
  
  @Override
  public String getUrl() {
    return this.url;
  }

  @Override
  public void isAt() {
    if (!title().equals("play-example-form")) {
      throw new RuntimeException("Not at IndexPage.");
    }
  }
  
  public void setName(String name) {
    fill("#name").with(name);
  }
  
  public void setPassword(String password) {
    fill("#password").with(password);
  }
  
  public void selectHobby(String hobby) {
    find("div", withId("hobbies")).find("input", withText(hobby)).click();
  }

  public void selectGradeLevel(String level) {
    find("div", withId("levels")).find("input", withText(level)).click();
  }
  
  public void selectGPA(String gpa) {
    find("select", withId("gpa")).find("option", withText(gpa)).click();
  }

  public void selectMajor(String major) {
    find("select", withId("majors")).find("option", withText(major)).click();
  }
  
  public void submit() {
    submit("#submit");
  }
  
  public void cancel() {
    find("#cancel").click();
  }


  
  
}
