package com.koyama.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TheInternetPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final String baseUrl = "https://the-internet.herokuapp.com/";

    // Home / links principais
    private final By abTestingLink = By.linkText("A/B Testing");
    private final By addRemoveElementsLink = By.linkText("Add/Remove Elements");
    private final By brokenImagesLink = By.linkText("Broken Images");
    private final By challengingDomLink = By.linkText("Challenging DOM");
    private final By checkboxesLink = By.linkText("Checkboxes");
    private final By contextMenuLink = By.linkText("Context Menu");
    private final By disappearingElementsLink = By.linkText("Disappearing Elements");
    private final By dragAndDropLink = By.linkText("Drag and Drop");

    // A/B Testing
    private final By abTestingParagraph = By.xpath("//div[@id='content']//div[@class='example']/p");

    // Add/Remove Elements
    private final By addElementButton = By.cssSelector("button[onclick='addElement()']");
    private final By deleteButtons = By.cssSelector("#elements button");

    // Broken Images
    private final By brokenImagesTitle = By.xpath("//div[@id='content']//div[@class='example']/h3");

    // Challenging DOM
    private final By quxButton = By.cssSelector("a.button.success");
    private final By challengingDomParagraph = By.cssSelector("p");

    // Checkboxes
    private final By checkboxesInput = By.cssSelector("#checkboxes input[type='checkbox']");

    // Context Menu
    private final By contextMenuParagraph = By.cssSelector("#content p:nth-of-type(1)");

    // Disappearing Elements
    private final By homeLink = By.cssSelector("a[href='/']");

    // Drag and Drop
    private final By columnA = By.id("column-a");
    private final By columnB = By.id("column-b");
    private final By headerA = By.cssSelector("#column-a header");
    private final By headerB = By.cssSelector("#column-b header");

    public TheInternetPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private List<WebElement> visibleAll(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    private void waitForUrl(String expectedUrlPart) {
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
    }

    public void openHomePage() {
        driver.get(baseUrl);
    }

    // A/B Testing
    public void goToABTesting() {
        click(abTestingLink);
        waitForUrl("/abtest");
    }

    public String getABTestingParagraphText() {
        return visible(abTestingParagraph).getText();
    }

    // Add/Remove Elements
    public void goToAddRemoveElements() {
        click(addRemoveElementsLink);
        waitForUrl("/add_remove_elements/");
    }

    public void addElements(int amount) {
        for (int i = 0; i < amount; i++) {
            click(addElementButton);
        }
    }

    public int getDeleteButtonsCount() {
        return driver.findElements(deleteButtons).size();
    }

    // Broken Images
    public void goToBrokenImages() {
        click(brokenImagesLink);
        waitForUrl("/broken_images");
    }

    public String getBrokenImagesTitleText() {
        return visible(brokenImagesTitle).getText();
    }

    // Challenging DOM
    public void goToChallengingDom() {
        click(challengingDomLink);
        waitForUrl("/challenging_dom");
    }

    public void clickQuxButtonMultipleTimes(int amount) {
        for (int i = 0; i < amount; i++) {
            click(quxButton);
        }
    }

    public String getChallengingDomParagraphText() {
        return visible(challengingDomParagraph).getText();
    }

    // Checkboxes
    public void goToCheckboxes() {
        click(checkboxesLink);
        waitForUrl("/checkboxes");
    }

    public void selectAllCheckboxes() {
        List<WebElement> allCheckboxes = visibleAll(checkboxesInput);

        for (WebElement checkbox : allCheckboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    public boolean areAllCheckboxesSelected() {
        List<WebElement> allCheckboxes = driver.findElements(checkboxesInput);

        for (WebElement checkbox : allCheckboxes) {
            if (!checkbox.isSelected()) {
                return false;
            }
        }

        return true;
    }

    // Context Menu
    public void goToContextMenu() {
        click(contextMenuLink);
        waitForUrl("/context_menu");
    }

    public String getContextMenuDescriptionText() {
        return visible(contextMenuParagraph).getText();
    }

    // Disappearing Elements
    public void goToDisappearingElements() {
        click(disappearingElementsLink);
        waitForUrl("/disappearing_elements");
    }

    public void clickHomeAndReturnToDisappearingElements() {
        click(homeLink);
        goToDisappearingElements();
    }

    public void openLinkAndReturn(String menuText, String expectedUrlPart) {
        By dynamicLink = By.linkText(menuText);

        click(dynamicLink);
        waitForUrl(expectedUrlPart);

        driver.navigate().back();
        waitForUrl("/disappearing_elements");
    }

    public boolean isOnDisappearingElementsPage() {
        return driver.getCurrentUrl().contains("/disappearing_elements");
    }

    // Drag and Drop
    public void goToDragAndDrop() {
        click(dragAndDropLink);
        waitForUrl("/drag_and_drop");
    }

    public void dragColumnAToColumnB() {
        WebElement source = visible(columnA);
        WebElement target = visible(columnB);

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();

        wait.until(ExpectedConditions.textToBe(headerA, "B"));
        wait.until(ExpectedConditions.textToBe(headerB, "A"));
    }

    public String getColumnAHeaderText() {
        return visible(headerA).getText();
    }

    public String getColumnBHeaderText() {
        return visible(headerB).getText();
    }
}