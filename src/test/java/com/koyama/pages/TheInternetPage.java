package com.koyama.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TheInternetPage {

    private static final String BASE_URL = "https://the-internet.herokuapp.com/";
    private static final long SHORT_VISUAL_PAUSE_MS = 1_500;
    private static final long DEFAULT_VISUAL_PAUSE_MS = 2_000;
    private static final long LONG_VISUAL_PAUSE_MS = 3_000;

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Home
    private final By abTestingLink = By.linkText("A/B Testing");
    private final By addRemoveElementsLink = By.linkText("Add/Remove Elements");
    private final By brokenImagesLink = By.linkText("Broken Images");
    private final By challengingDomLink = By.linkText("Challenging DOM");
    private final By checkboxesLink = By.linkText("Checkboxes");
    private final By contextMenuLink = By.linkText("Context Menu");
    private final By disappearingElementsLink = By.linkText("Disappearing Elements");
    private final By dragAndDropLink = By.linkText("Drag and Drop");
    private final By dropdownLink = By.linkText("Dropdown");
    private final By dynamicContentLink = By.linkText("Dynamic Content");
    private final By dynamicControlsLink = By.linkText("Dynamic Controls");
    private final By dynamicLoadingLink = By.linkText("Dynamic Loading");

    // A/B Testing
    private final By abTestingParagraph = By.cssSelector("#content .example p");

    // Add/Remove Elements
    private final By addElementButton = By.cssSelector("button[onclick='addElement()']");
    private final By deleteButtons = By.cssSelector("#elements button");

    // Broken Images
    private final By brokenImagesTitle = By.cssSelector("#content .example h3");
    private final By brokenImages = By.cssSelector(".example img");

    // Challenging DOM
    private final By quxButton = By.cssSelector("a.button.success");
    private final By challengingDomParagraph = By.cssSelector(".example p");

    // Checkboxes
    private final By checkboxesInput = By.cssSelector("#checkboxes input[type='checkbox']");

    // Context Menu
    private final By contextMenuDescription = By.cssSelector("#content p:nth-of-type(1)");
    private final By contextMenuHotSpot = By.id("hot-spot");

    // Dropdown
    private final By dropdownSelect = By.id("dropdown");

    // Dynamic Content
    private final By dynamicContentDescription = By.cssSelector("#content p:nth-of-type(1)");

    // Dynamic Controls
    private final By dynamicControlsToggleCheckboxButton =
        By.xpath("//form[@id='checkbox-example']//button[@onclick='swapCheckbox()']");
    private final By dynamicControlsEnableInputButton =
        By.xpath("//form[@id='input-example']//button[@onclick='swapInput()']");
    private final By dynamicControlsMessage = By.id("message");

    // Dynamic Loading
    private final By dynamicLoadingDescription = By.cssSelector("#content p");
    private final By dynamicLoadingTitle = By.cssSelector("#content h3");
    private final By dynamicLoadingExample1Link = By.cssSelector("#content a[href='/dynamic_loading/1']");
    private final By dynamicLoadingExample2Link = By.cssSelector("#content a[href='/dynamic_loading/2']");
    private final By dynamicLoadingStartButton = By.cssSelector("#start button");
    private final By dynamicLoadingFinishText = By.id("finish");

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
        driver.get(BASE_URL);
    }

    // A/B Testing
    public void goToAbTesting() {
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
        for (int index = 0; index < amount; index++) {
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

    public int countBrokenImages() {
        List<WebElement> images = visibleAll(brokenImages);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        int brokenImagesCount = 0;

        for (WebElement image : images) {
            Number naturalWidth = (Number) javascriptExecutor.executeScript(
                "return arguments[0].naturalWidth;",
                image
            );

            if (naturalWidth == null || naturalWidth.intValue() == 0) {
                brokenImagesCount++;
            }
        }

        return brokenImagesCount;
    }

    // Challenging DOM
    public void goToChallengingDom() {
        click(challengingDomLink);
        waitForUrl("/challenging_dom");
    }

    public void clickQuxButtonMultipleTimes(int amount) {
        for (int index = 0; index < amount; index++) {
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
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
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
        return visible(contextMenuDescription).getText();
    }

    public void rightClickContextMenuBox() {
        WebElement hotSpot = visible(contextMenuHotSpot);
        Actions actions = new Actions(driver);

        actions.contextClick(hotSpot).perform();
        visualPause(SHORT_VISUAL_PAUSE_MS);
    }

    public String getContextMenuAlertText() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert.getText();
    }

    public void acceptContextMenuAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    // Dropdown
    public void goToDropdown() {
        click(dropdownLink);
        waitForUrl("/dropdown");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void selectDropdownOptionByText(String optionText) {
        Select select = new Select(visible(dropdownSelect));
        select.selectByVisibleText(optionText);
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public String getSelectedDropdownOptionText() {
        Select select = new Select(visible(dropdownSelect));
        return select.getFirstSelectedOption().getText();
    }

    // Dynamic Content
    public void goToDynamicContent() {
        click(dynamicContentLink);
        waitForUrl("/dynamic_content");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public String getDynamicContentDescriptionText() {
        return visible(dynamicContentDescription).getText();
    }

    // Dynamic Controls
    public void goToDynamicControls() {
        click(dynamicControlsLink);
        waitForUrl("/dynamic_controls");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void removeCheckbox() {
        click(dynamicControlsToggleCheckboxButton);
        wait.until(ExpectedConditions.textToBe(dynamicControlsMessage, "It's gone!"));
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void enableInputField() {
        click(dynamicControlsEnableInputButton);
        wait.until(ExpectedConditions.textToBe(dynamicControlsMessage, "It's enabled!"));
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public String getDynamicControlsMessageText() {
        return visible(dynamicControlsMessage).getText();
    }

    // Dynamic Loading
    public void goToDynamicLoading() {
        click(dynamicLoadingLink);
        waitForUrl("/dynamic_loading");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public String getDynamicLoadingDescriptionText() {
        return visible(dynamicLoadingDescription).getText();
    }

    public String getDynamicLoadingTitleText() {
        return visible(dynamicLoadingTitle).getText();
    }

    public void openDynamicLoadingExample1() {
        click(dynamicLoadingExample1Link);
        waitForUrl("/dynamic_loading/1");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void openDynamicLoadingExample2() {
        click(dynamicLoadingExample2Link);
        waitForUrl("/dynamic_loading/2");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void startDynamicLoadingExample() {
        click(dynamicLoadingStartButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicLoadingFinishText));
        visualPause(LONG_VISUAL_PAUSE_MS);
    }

    public String getDynamicLoadingFinishText() {
        return visible(dynamicLoadingFinishText).getText();
    }

    public void navigateBack(String expectedUrlPart) {
        if (expectedUrlPart == null || expectedUrlPart.trim().isEmpty()) {
            throw new IllegalArgumentException("expectedUrlPart cannot be null or blank.");
        }

        driver.navigate().back();
        waitForUrl(expectedUrlPart);
        visualPause(SHORT_VISUAL_PAUSE_MS);
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

    public void openLinkFromDisappearingElementsAndReturn(String menuText, String expectedUrlPart) {
        By menuLink = By.linkText(menuText);

        click(menuLink);
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

    // Intentional pause for study mode so the user can visually follow the execution.
    private void visualPause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Execution was interrupted during the visual pause.", exception);
        }
    }
}
