
package ohtuhatut.selenium;

import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
/**
 * 
 * @author tuomokar
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ohtuhatut.Main.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ReferenceListTest extends FluentTest {

    @Value("${local.server.port}")
    private int serverPort;
    private WebDriver webDriver = new HtmlUnitDriver();

    private String getUrl() {
        return "http://localhost:" + serverPort;
    }

    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }

    @Test
    public void mainPageIsShownWithNoSpecifiedAddress() {
        goTo(getUrl());
        assertTrue(pageSource().contains("Welcome to the reference app"));
        assertEquals("References", title());
    }

    @Test
    public void referenceListCreationWorksWithNonEmptyName() {
        getToReferenceListCreationPage();

        fill("#name").with("testList1");
        submit(find("form").first());

        assertTrue(pageSource().contains("testList1"));
        assertTrue(pageSource().contains("No references in the database at the moment for you to add"));

    }

    @Test
    public void referenceListCreationGivesErrorMessageWhenEnteringAnEmptyName() {
        getToReferenceListCreationPage();

        fill("#name").with("");
        submit(find("form").first());

        assertTrue(pageSource().contains("Create a new reference list"));
        assertTrue(pageSource().contains("may not be empty"));
        
    }
    
    @Test
    public void addingReferencesToAReferenceListIsSuccessful() {
        createAReference();
        createAReferenceList();        
        
        fillSelect("#references").withIndex(0);
        
        submit(find("form").first());
        
        
        assertTrue(pageSource().contains("testTitle"));
        assertTrue(pageSource().contains("No references in the database at the moment for you to add"));
    }
    
    private void createAReferenceList() {
        getToReferenceListCreationPage();

        fill("#name").with("testList1");
        submit(find("form").first());
    }
    
    private void createAReference() {
        getToManualReferenceCreationPage();

        fill("#title").with("testTitle");
        submit(find("form").first());
    }

    private void getToReferenceListCreationPage() {
        goTo(getUrl());
        click(find("a", 1));
    }
    
    private void getToManualReferenceCreationPage() {
        getToReferenceCreationsChoosingPage();
        click(find("a", 5));
    }
    
    private void getToReferenceCreationsChoosingPage() {
        goTo(getUrl());
        click(find("a", 0));
    }
}