package tests;
import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;
import org.junit.Test;
import play.libs.F.Callback;
import play.test.TestBrowser;
import tests.pages.IndexPage;


public class ViewTest {
  private final int testPort = 3333;
  
  /** Test simple retrieval of the index page. */
  @Test
  public void testIndexPage() {
    running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      @Override
      public void invoke(TestBrowser browser) {
        IndexPage indexPage = new IndexPage(browser.getDriver(), testPort);
        browser.goTo(indexPage);
        indexPage.isAt();
      }
    });
  }

}
