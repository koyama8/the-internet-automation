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

    // Link selectors
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
    private final By entryAd = By.linkText("Entry Ad");
    private final By fileDownload = By.linkText("File Download");
    private final By floatMenu = By.linkText("Floating Menu");
    private final By passwordLink = By.linkText("Forgot Password");
    private final By formAuthentication = By.linkText("Form Authentication");    
    private final By framesLink = By.linkText("Frames");
    

    // Button selectors
    private final By addElementButton = By.cssSelector("button[onclick='addElement()']");
    private final By deleteButtons = By.cssSelector("#elements button");
    private final By dynamicLoadingStartButton = By.cssSelector("#start button");
    private final By quxButton = By.cssSelector("a.button.success");

    // CSS selectors
    private final By abTestingParagraph = By.cssSelector("#content .example p");
    private final By brokenImages = By.cssSelector(".example img");
    private final By brokenImagesTitle = By.cssSelector("#content .example h3");
    private final By challengingDomParagraph = By.cssSelector(".example p");
    private final By checkboxesInput = By.cssSelector("#checkboxes input[type='checkbox']");
    private final By contextMenuDescription = By.cssSelector("#content p:nth-of-type(1)");
    private final By dynamicContentDescription = By.cssSelector("#content p:nth-of-type(1)");
    private final By dynamicLoadingDescription = By.cssSelector("#content p");
    private final By dynamicLoadingExample1Link = By.cssSelector("#content a[href='/dynamic_loading/1']");
    private final By dynamicLoadingExample2Link = By.cssSelector("#content a[href='/dynamic_loading/2']");
    private final By dynamicLoadingTitle = By.cssSelector("#content h3");
    private final By headerA = By.cssSelector("#column-a header");
    private final By headerB = By.cssSelector("#column-b header");
    private final By homeLink = By.cssSelector("a[href='/']");
    private final By closeTextButton = By.cssSelector(".modal-footer p");
    private final By closeText = By.cssSelector(".modal-body p");
    private final By floatMenualLink = By.cssSelector("#content a[href='#home'] ");
    private final By floatMenuLink2 = By.xpath("//div[@class='example']//a[normalize-space()='News']");
    private final By floatMenulink3 = By.cssSelector("#content a[href='#contact'");
    private final By framesLinkNested = By.cssSelector("#content a[href='/nested_frames']");
    

    // ID selectors
    private final By columnA = By.id("column-a");
    private final By columnB = By.id("column-b");
    private final By contextMenuHotSpot = By.id("hot-spot");
    private final By dropdownSelect = By.id("dropdown");
    private final By dynamicControlsMessage = By.id("message");
    private final By dynamicLoadingFinishText = By.id("finish");
    private final By forgotPasswordEmailInput= By.id("email");

    // XPath selectors
    private final By dynamicControlsEnableInputButton =
        By.xpath("//form[@id='input-example']//button[@onclick='swapInput()']");
    private final By dynamicControlsToggleCheckboxButton =
        By.xpath("//form[@id='checkbox-example']//button[@onclick='swapCheckbox()']");
    private final By entryadlButton = 
    		By.xpath("//div[@class='modal-footer']//p[normalize-space()='Close']");
    private final By entryText = 
    		By.xpath("//div[@class='example']//p[normalize-space()='If closed, it will not appear on subsequent page loads.']");
    private final By fileDowloadPDF =
    		By.xpath("//a[contains(@href,'p_compress.pdf')]");
    private final By gestionDePruebasDocxLink  =
    		By.xpath("//div[@class='example']//a[normalize-space()='Gestion de pruebas.docx']");
    private final By tmpTXT =
    		By.xpath("//div[@class='example']//a[normalize-space()='tmp0060drcz.txt']");
    private final By buttonPassword = 
    		By.xpath("//form[@id='forgot_password']//button[@type='submit']");
    private final By floatMenulink4 = 
    		By.xpath("//div[@class='example']//a[normalize-space()='About']");
    private final By usernameForm =
    		By.xpath("//form[@id='login']//input[@type='text']");
    private final By passwordFor = 
    		By.xpath("//form[@id='login']//input[@type='password']");
    private final By buttonForm =
    		By.xpath("//form[@id='login']//button[@type='submit']");
    private final By leftframeText = 
    		By.xpath("//body[normalize-space()='LEFT']");
    
    
    // Name
    private final By topframe = By.name("frame-top");
    private final By leftframe = By.name("frame-left");
    
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

    // Navigation and action methods
    public void acceptContextMenuAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public void addElements(int amount) {
        for (int index = 0; index < amount; index++) {
            click(addElementButton);
            visualPause(SHORT_VISUAL_PAUSE_MS);
        }
    }

    public void clickHomeAndReturnToDisappearingElements() {
        click(homeLink);
        goToDisappearingElements();
    }

    public void clickQuxButtonMultipleTimes(int amount) {
        for (int index = 0; index < amount; index++) {
            click(quxButton);
        }
    }

    public void dragColumnAToColumnB() {
        WebElement source = visible(columnA);
        WebElement target = visible(columnB);

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();

        wait.until(ExpectedConditions.textToBe(headerA, "B"));
        wait.until(ExpectedConditions.textToBe(headerB, "A"));
    }

    public void enableInputField() {
        click(dynamicControlsEnableInputButton);
        wait.until(ExpectedConditions.textToBe(dynamicControlsMessage, "It's enabled!"));
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void goToAbTesting() {
        click(abTestingLink);
        waitForUrl("/abtest");
    }

    public void goToAddRemoveElements() {
        click(addRemoveElementsLink);
        waitForUrl("/add_remove_elements/");
    }

    public void goToBrokenImages() {
        click(brokenImagesLink);
        waitForUrl("/broken_images");
    }

    public void goToChallengingDom() {
        click(challengingDomLink);
        waitForUrl("/challenging_dom");
    }

    public void goToCheckboxes() {
        click(checkboxesLink);
        waitForUrl("/checkboxes");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void goToContextMenu() {
        click(contextMenuLink);
        waitForUrl("/context_menu");
    }

    public void goToDisappearingElements() {
        click(disappearingElementsLink);
        waitForUrl("/disappearing_elements");
    }

    public void goToDragAndDrop() {
        click(dragAndDropLink);
        waitForUrl("/drag_and_drop");
    }

    public void goToDropdown() {
        click(dropdownLink);
        waitForUrl("/dropdown");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void goToDynamicContent() {
        click(dynamicContentLink);
        waitForUrl("/dynamic_content");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void goToDynamicControls() {
        click(dynamicControlsLink);
        waitForUrl("/dynamic_controls");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void goToDynamicLoading() {
        click(dynamicLoadingLink);
        waitForUrl("/dynamic_loading");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }
    
    public void goToEntryAd() {
    	   click(entryAd);
    	   waitForUrl("/entry_ad");
    	   visualPause(SHORT_VISUAL_PAUSE_MS);
    }
    
    public void goToFileDowload() {
    	   click(fileDownload);
    	   waitForUrl("/download");
    	   visualPause(SHORT_VISUAL_PAUSE_MS);
    }
    
    public void gotoMenuFloat() {
    	  click(floatMenu);
    	  waitForUrl("/floating_menu");
    	  visualPause(LONG_VISUAL_PAUSE_MS);
    }
    
    public void gotoPassword() {
    	  click(passwordLink);
    	  waitForUrl("/forgot_password");
    	  visualPause(SHORT_VISUAL_PAUSE_MS);
    }
    
    public void gotoForm() {
    	 click(formAuthentication);
    	 waitForUrl("/login");
    	 visualPause(LONG_VISUAL_PAUSE_MS);
    }
    
    public void gotoFrames() {
    	  click(framesLink);
    	  waitForUrl("/frames");
    	  visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void navigateBack(String expectedUrlPart) {
        if (expectedUrlPart == null || expectedUrlPart.trim().isEmpty()) {
            throw new IllegalArgumentException("expectedUrlPart cannot be null or blank.");
        }

        driver.navigate().back();
        waitForUrl(expectedUrlPart);
        visualPause(LONG_VISUAL_PAUSE_MS);
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

    public void openHomePage() {
        driver.get(BASE_URL);
    }

    public void openLinkFromDisappearingElementsAndReturn(String menuText, String expectedUrlPart) {
        By menuLink = By.linkText(menuText);

        click(menuLink);
        waitForUrl(expectedUrlPart);

        driver.navigate().back();
        waitForUrl("/disappearing_elements");
    }

    public void removeCheckbox() {
        click(dynamicControlsToggleCheckboxButton);
        wait.until(ExpectedConditions.textToBe(dynamicControlsMessage, "It's gone!"));
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void rightClickContextMenuBox() {
        WebElement hotSpot = visible(contextMenuHotSpot);
        Actions actions = new Actions(driver);

        actions.contextClick(hotSpot).perform();
        visualPause(SHORT_VISUAL_PAUSE_MS);
    }

    public void selectAllCheckboxes() {
        List<WebElement> allCheckboxes = visibleAll(checkboxesInput);

        for (WebElement checkbox : allCheckboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    public void selectDropdownOptionByText(String optionText) {
        Select select = new Select(visible(dropdownSelect));
        select.selectByVisibleText(optionText);
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void startDynamicLoadingExample() {
        click(dynamicLoadingStartButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicLoadingFinishText));
        visualPause(LONG_VISUAL_PAUSE_MS);
    }
    
    public void selectCloseLink() {
    	  click(entryadlButton);
    	  visualPause(SHORT_VISUAL_PAUSE_MS);
    }
    
    public void selectFilePDF() {
    	  click(fileDowloadPDF);
    	  click(gestionDePruebasDocxLink );
    	  click(tmpTXT);
    	  visualPause(LONG_VISUAL_PAUSE_MS);
    }
    
    public void selectButton() {
    	  click(buttonPassword);
    	  visualPause(LONG_VISUAL_PAUSE_MS);
    }
    
    public void selectButtonForm() {
    	  click(buttonForm);
    	  visualPause(LONG_VISUAL_PAUSE_MS);
    }
    
    public void selectLinkNested() {
    	  click(framesLinkNested);
    	  visualPause(LONG_VISUAL_PAUSE_MS);
    }
    
    public  String getLeftFrameText() {
    	  driver.switchTo().defaultContent();
    	  driver.switchTo().frame(driver.findElement(topframe));
    	  driver.switchTo().frame(driver.findElement(leftframe));
    	  
    	  return visible(leftframeText).getText();
    }
    
    public void selectMenuFloat() {
    	  click(floatMenualLink);
    	  visualPause(SHORT_VISUAL_PAUSE_MS);
    	  click(floatMenuLink2);
    	  visualPause(SHORT_VISUAL_PAUSE_MS);
    	  click(floatMenulink3);
    	  visualPause(SHORT_VISUAL_PAUSE_MS);
    	  click(floatMenulink4);
    	  visualPause(SHORT_VISUAL_PAUSE_MS);

    }
    
    public void writePassword(String email) {
    	  WebElement input = visible(forgotPasswordEmailInput);
    	  input.clear();
    	  input.sendKeys(email);
    	  visualPause(LONG_VISUAL_PAUSE_MS);
    }
    
    public void writeForm(String username) {
    	  WebElement input = visible(usernameForm);
    	  input.clear();
    	  input.sendKeys(username);
    	  visualPause(LONG_VISUAL_PAUSE_MS);
    }
    
    public void writePasswordForm(String password) {
    	  WebElement passworInput = visible(passwordFor);
    	  passworInput.clear();
    	  passworInput.sendKeys(password);
    	  visualPause(LONG_VISUAL_PAUSE_MS);
    }

    // Read and validation methods
    public boolean areAllCheckboxesSelected() {
        List<WebElement> allCheckboxes = driver.findElements(checkboxesInput);

        for (WebElement checkbox : allCheckboxes) {
            if (!checkbox.isSelected()) {
                return false;
            }
        }

        return true;
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

    public String getABTestingParagraphText() {
        return visible(abTestingParagraph).getText();
    }

    public String getBrokenImagesTitleText() {
        return visible(brokenImagesTitle).getText();
    }

    public String getChallengingDomParagraphText() {
        return visible(challengingDomParagraph).getText();
    }

    public String getColumnAHeaderText() {
        return visible(headerA).getText();
    }

    public String getColumnBHeaderText() {
        return visible(headerB).getText();
    }

    public String getContextMenuAlertText() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert.getText();
    }

    public String getContextMenuDescriptionText() {
        return visible(contextMenuDescription).getText();
    }

    public int getDeleteButtonsCount() {
        return driver.findElements(deleteButtons).size();
    }

    public String getDynamicContentDescriptionText() {
        return visible(dynamicContentDescription).getText();
    }

    public String getDynamicControlsMessageText() {
        return visible(dynamicControlsMessage).getText();
    }

    public String getDynamicLoadingDescriptionText() {
        return visible(dynamicLoadingDescription).getText();
    }

    public String getDynamicLoadingFinishText() {
        return visible(dynamicLoadingFinishText).getText();
    }

    public String getDynamicLoadingTitleText() {
        return visible(dynamicLoadingTitle).getText();
    }

    public String getSelectedDropdownOptionText() {
        Select select = new Select(visible(dropdownSelect));
        return select.getFirstSelectedOption().getText();
    }
    
    public String getEntryTextButton() {
    	   return visible(closeTextButton).getText();
    }
    
    public String  getEntryText() {
    	 return visible(closeText).getText();
    }
    
    public String getEntryTextAd() {
    	  return visible(entryText).getText();
    }


    public boolean isOnDisappearingElementsPage() {
        return driver.getCurrentUrl().contains("/disappearing_elements");
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
