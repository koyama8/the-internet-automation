package com.koyama.pages;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

    /**
     * Seletores por link de texto.
     * Usados para navegar da home para as paginas principais do site.
     */
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
    private final By entryAdLink = By.linkText("Entry Ad");
    private final By fileDownloadLink = By.linkText("File Download");
    private final By floatingMenuLink = By.linkText("Floating Menu");
    private final By forgotPasswordLink = By.linkText("Forgot Password");
    private final By formAuthenticationLink = By.linkText("Form Authentication");
    private final By framesLink = By.linkText("Frames");
    private final By horizontalSliderLink = By.linkText("Horizontal Slider");
    private final By inputsLink = By.linkText("Inputs");
    private final By infiniteScrollLink = By.linkText("Infinite Scroll");
    private final By jqueryUiMenusLink = By.linkText("JQuery UI Menus");
    private final By javascriptAlertsLink = By.linkText("JavaScript Alerts");
    private final By keyPressesLink = By.linkText("Key Presses");
    private final By windowsMultiple = By.linkText("Multiple Windows");
    private final By notificationMessages = By.linkText("Notification Messages");
    
    /**
     * Seletores CSS.
     * Aqui ficam botoes, textos, links internos, listas e elementos visuais da tela.
     */
    private final By addElementButton = By.cssSelector("button[onclick='addElement()']");
    private final By deleteButtons = By.cssSelector("#elements button");
    private final By dynamicLoadingStartButton = By.cssSelector("#start button");
    private final By quxButton = By.cssSelector("a.button.success");
    private final By forgotPasswordSubmitButton = By.cssSelector("#forgot_password button[type='submit']");
    private final By loginSubmitButton = By.cssSelector("#login button[type='submit']");
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
    private final By floatingMenuHomeLink = By.cssSelector("#menu a[href='#home']");
    private final By floatingMenuNewsLink = By.cssSelector("#menu a[href='#news']");
    private final By floatingMenuContactLink = By.cssSelector("#menu a[href='#contact']");
    private final By floatingMenuAboutLink = By.cssSelector("#menu a[href='#about']");
    private final By framesLinkNested = By.cssSelector("#content a[href='/nested_frames']");
    private final By downloadLinks = By.cssSelector("#content .example a");
    private final By secureAreaTitle = By.cssSelector("#content h2");
    private final By horizontalSliderInput = By.cssSelector("input[type='range']");
    private final By numberInput = By.cssSelector("input[type='number']");
    private final By infiniteScrollItems = By.cssSelector(".jscroll-added");
    private final By jqueryUiPdfDownloadOption = By.cssSelector("#menu  a[href='/download/jqueryui/menu/menu.pdf']");
    private final By jqueryUiDescriptionText = By.cssSelector("#content p");
    private final By jsAlertButton = By.cssSelector("#content button[onclick='jsAlert()']");
    private final By jsConfirmButton = By.cssSelector("button[onclick='jsConfirm()']");
    private final By jsPromptButton = By.cssSelector("button[onclick='jsPrompt()']");
    private final By keyPressesDescription = By.cssSelector(".example p");
    private final By windowsText = By.cssSelector(".example h3");
    private final By messageNotification = By.cssSelector("#content a[href='/notification_message'] ");
    private final By messageJs = By.cssSelector("#flash");

    /**
     * Seletores por ID.
     * Aqui ficam elementos mais estaveis da pagina, como campos, mensagens e containers.
     */
    private final By columnA = By.id("column-a");
    private final By columnB = By.id("column-b");
    private final By contextMenuHotSpot = By.id("hot-spot");
    private final By dropdownSelect = By.id("dropdown");
    private final By dynamicControlsMessage = By.id("message");
    private final By dynamicLoadingFinishText = By.id("finish");
    private final By forgotPasswordEmailInput = By.id("email");
    private final By flashMessage = By.id("flash");
    private final By usernameForm = By.id("username");
    private final By passwordForm = By.id("password");
    private final By horizontalSliderValue = By.id("range");
    private final By keyInput = By.id("target");
    private final By pageResultText = By.id("result");

    /**
     * Seletores por name.
     * Usados principalmente no fluxo de frames.
     */
    private final By topFrame = By.name("frame-top");
    private final By leftFrame = By.name("frame-left");

    /**
     * Seletores por XPath.
     * Ficam aqui os casos em que o XPath ajuda mais pela estrutura ou pelo texto do elemento.
     */
    private final By dynamicControlsEnableInputButton =
        By.xpath("//form[@id='input-example']//button[@onclick='swapInput()']");
    private final By dynamicControlsToggleCheckboxButton =
        By.xpath("//form[@id='checkbox-example']//button[@onclick='swapCheckbox()']");
    private final By entryAdCloseButton =
        By.xpath("//div[@class='modal-footer']//p[normalize-space()='Close']");
    private final By entryAdInformationText =
        By.xpath("//div[@class='example']//p[normalize-space()='If closed, it will not appear on subsequent page loads.']");
    private final By leftFrameText =
        By.xpath("//body[normalize-space()='LEFT']");
    private final By enabledJqueryUiOption =
        By.xpath("//ul[@id='menu']//a[normalize-space()='Enabled']");
    private final By downloadsJqueryUiOption =
        By.xpath("//ul[@id='menu']//a[normalize-space()='Downloads']");
    private final By backToJqueryUiOption =
        By.xpath("//ul[@id='menu']//a[normalize-space()='Back to JQuery UI']");
    private final By linkWindows =
    	By.xpath("//div[@id='content']//a[normalize-space()='Click Here']");
    
    
    public TheInternetPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Metodos utilitarios internos.
     * Servem para centralizar clique, espera, visibilidade e tratamento simples de texto.
     */
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

    private String normalizeText(String rawText) {
        return rawText.replace("\u00D7", "").replaceAll("\\s+", " ").trim();
    }

    /**
     * Metodos de navegacao.
     * Este bloco concentra os metodos responsaveis por abrir paginas, acessar links
     * e controlar a movimentacao entre telas do site.
     */
    public void openHomePage() {
        driver.get(BASE_URL);
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
    
    public void goToWindows() {
    	 click(windowsMultiple);
    	 waitForUrl("/windows");
    	 visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }
    
    public void goToNotification() {
    	 click(notificationMessages);
    	 waitForUrl("/notification_message_rendered");
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
        click(entryAdLink);
        waitForUrl("/entry_ad");
        visualPause(SHORT_VISUAL_PAUSE_MS);
    }

    public void goToFileDownload() {
        click(fileDownloadLink);
        waitForUrl("/download");
        visualPause(SHORT_VISUAL_PAUSE_MS);
    }

    public void goToFloatingMenu() {
        click(floatingMenuLink);
        waitForUrl("/floating_menu");
        visualPause(LONG_VISUAL_PAUSE_MS);
    }

    public void goToForgotPassword() {
        click(forgotPasswordLink);
        waitForUrl("/forgot_password");
        visualPause(SHORT_VISUAL_PAUSE_MS);
    }

    public void goToFormAuthentication() {
        click(formAuthenticationLink);
        waitForUrl("/login");
        visualPause(LONG_VISUAL_PAUSE_MS);
    }

    public void goToFrames() {
        click(framesLink);
        waitForUrl("/frames");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void goToHorizontalSlider() {
        click(horizontalSliderLink);
        waitForUrl("/horizontal_slider");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void goToInputNumber() {
        click(inputsLink);
        waitForUrl("/inputs");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void goToJqueryUiMenu() {
        click(jqueryUiMenusLink);
        waitForUrl("/jqueryui/menu");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void goToInfiniteScroll() {
        click(infiniteScrollLink);
        waitForUrl("/infinite_scroll");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void goToJavaScriptAlerts() {
        click(javascriptAlertsLink);
        waitForUrl("/javascript_alerts");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void goToKeyPresses() {
        click(keyPressesLink);
        waitForUrl("/key_presses");
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
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

    public void openLinkFromDisappearingElementsAndReturn(String menuText, String expectedUrlPart) {
        By menuLink = By.linkText(menuText);

        click(menuLink);
        waitForUrl(expectedUrlPart);

        driver.navigate().back();
        waitForUrl("/disappearing_elements");
    }

    public void clickHomeAndReturnToDisappearingElements() {
        click(homeLink);
        goToDisappearingElements();
    }

    public void navigateBack(String expectedUrlPart) {
        if (expectedUrlPart == null || expectedUrlPart.trim().isEmpty()) {
            throw new IllegalArgumentException("expectedUrlPart cannot be null or blank.");
        }

        driver.navigate().back();
        waitForUrl(expectedUrlPart);
        visualPause(LONG_VISUAL_PAUSE_MS);
    }

    /**
     * Metodos de execucao e interacao.
     * Este bloco concentra as acoes feitas dentro das telas, como clicar, preencher,
     * arrastar, selecionar, submeter formularios e interagir com alertas.
     */
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

    public void clickQuxButtonMultipleTimes(int amount) {
        for (int index = 0; index < amount; index++) {
            click(quxButton);
        }
    }

    public String downloadFirstAvailableFile() {
        List<WebElement> fileLinks = visibleAll(downloadLinks);

        for (WebElement fileLink : fileLinks) {
            String fileName = fileLink.getText().trim();

            if (!fileName.isEmpty()) {
                wait.until(ExpectedConditions.elementToBeClickable(fileLink)).click();
                return fileName;
            }
        }

        throw new IllegalStateException("No downloadable file was available on the page.");
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

    public void selectCloseLink() {
        click(entryAdCloseButton);
        visualPause(SHORT_VISUAL_PAUSE_MS);
    }

    public void selectDropdownOptionByText(String optionText) {
        Select select = new Select(visible(dropdownSelect));
        select.selectByVisibleText(optionText);
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void selectLinkNested() {
        click(framesLinkNested);
        waitForUrl("/nested_frames");
        visualPause(LONG_VISUAL_PAUSE_MS);
    }

    public void downloadPdfFromJqueryUiMenu() {
        hover(enabledJqueryUiOption);
        visible(downloadsJqueryUiOption);
        visualPause(DEFAULT_VISUAL_PAUSE_MS);

        hover(downloadsJqueryUiOption);
        visible(jqueryUiPdfDownloadOption);
        visualPause(DEFAULT_VISUAL_PAUSE_MS);

        click(jqueryUiPdfDownloadOption);
        visualPause(DEFAULT_VISUAL_PAUSE_MS);

        hover(enabledJqueryUiOption);
        visualPause(DEFAULT_VISUAL_PAUSE_MS);

        click(backToJqueryUiOption);
        driver.navigate().back();
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void selectBackToJqueryUi() {
        hover(enabledJqueryUiOption);
        visible(backToJqueryUiOption);
        visualPause(DEFAULT_VISUAL_PAUSE_MS);

        click(backToJqueryUiOption);
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void clickJavaScriptAlertButton() {
        click(jsAlertButton);
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void clickJavaScriptConfirmButton() {
        click(jsConfirmButton);
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void clickJavaScriptPromptButton() {
        click(jsPromptButton);
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void dismissJavaScriptAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void typeSampleKeysOneByOne() {
        WebElement input = visible(keyInput);
        String[] letters = {"A", "B", "C", "D"};

        for (String letter : letters) {
            input.sendKeys(letter);
            visualPause(DEFAULT_VISUAL_PAUSE_MS);
            input.clear();
        }
    }
    
    public void selectWindows() {
    	 click(linkWindows);
    	 visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }
    
    public void selectNotification() {
       
    	for (int i = 0; i < 2; i++) {
	     click(messageNotification);
	     visualPause(DEFAULT_VISUAL_PAUSE_MS);
	 }
   }

    public void pressKeysSlowly(Keys[] keys) {
        WebElement input = visible(keyInput);

        input.click();

        for (Keys key : keys) {
            input.sendKeys(key);
            visualPause(DEFAULT_VISUAL_PAUSE_MS);
            input.clear();
        }
    }

    public void typeTextInJavaScriptPrompt(String text) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void acceptJavaScriptAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        visualPause(DEFAULT_VISUAL_PAUSE_MS);
    }

    public void navigateFloatingMenuAnchors() {
        click(floatingMenuHomeLink);
        waitForUrl("#home");

        click(floatingMenuNewsLink);
        waitForUrl("#news");

        click(floatingMenuContactLink);
        waitForUrl("#contact");

        click(floatingMenuAboutLink);
        waitForUrl("#about");
    }

    public void startDynamicLoadingExample() {
        click(dynamicLoadingStartButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicLoadingFinishText));
        visualPause(LONG_VISUAL_PAUSE_MS);
    }

    public void submitForgotPasswordRequest() {
        click(forgotPasswordSubmitButton);
        waitForUrl("/email_sent");
    }

    public void submitLoginForm() {
        click(loginSubmitButton);
        waitForUrl("/secure");
    }

    public void typeUsername(String username) {
        WebElement input = visible(usernameForm);
        input.clear();
        input.sendKeys(username);
        visualPause(LONG_VISUAL_PAUSE_MS);
    }

    public void typeForgotPasswordEmail(String email) {
        WebElement input = visible(forgotPasswordEmailInput);
        input.clear();
        input.sendKeys(email);
        visualPause(LONG_VISUAL_PAUSE_MS);
    }

    public void typeLoginPassword(String password) {
        WebElement passwordInput = visible(passwordForm);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        visualPause(LONG_VISUAL_PAUSE_MS);
    }

    public void typeNumberInput(String value) {
        WebElement input = visible(numberInput);
        input.clear();
        input.sendKeys(value);
        visualPause(LONG_VISUAL_PAUSE_MS);
    }

    public void increaseNumberInput(int value) {
        WebElement input = visible(numberInput);
        input.click();

        for (int index = 0; index < value; index++) {
            input.sendKeys(Keys.ARROW_UP);
            visualPause(SHORT_VISUAL_PAUSE_MS);
        }
    }

    /**
     * Faz scroll ate o final da pagina.
     */
    public void scrollToPageBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        visualPause(LONG_VISUAL_PAUSE_MS);
    }

    public void scrollUntilNewContentLoads() {
        int initialCount = getInfiniteScrollItemsCount();

        scrollToPageBottom();

        wait.until(driver -> {
            if (getInfiniteScrollItemsCount() > initialCount) {
                return true;
            }

            scrollToPageBottom();
            return getInfiniteScrollItemsCount() > initialCount;
        });
    }

    /**
     * Aliases de compatibilidade.
     * Este bloco foi mantido para nao quebrar chamadas antigas do projeto.
     */
    public void goToFileDowload() {
        goToFileDownload();
    }

    public void gotoMenuFloat() {
        goToFloatingMenu();
    }

    public void gotoPassword() {
        goToForgotPassword();
    }

    public void gotoForm() {
        goToFormAuthentication();
    }

    public void gotoFrames() {
        goToFrames();
    }

    public void goToQueryUI() {
        goToJqueryUiMenu();
    }

    public void goToLoad() {
        goToInfiniteScroll();
    }

    public void goToScriptsAlerts() {
        goToJavaScriptAlerts();
    }

    public void goToKeyInput() {
        goToKeyPresses();
    }

    public void selectEnabledDowloads() {
        downloadPdfFromJqueryUiMenu();
    }

    public void selectEnabledDownloads() {
        downloadPdfFromJqueryUiMenu();
    }

    public void selectBackToJQueryUi() {
        selectBackToJqueryUi();
    }

    public void selectMenuFloat() {
        navigateFloatingMenuAnchors();
    }

    public void writeForm(String username) {
        typeUsername(username);
    }

    public void writePassword(String email) {
        typeForgotPasswordEmail(email);
    }

    public void writePasswordForm(String password) {
        typeLoginPassword(password);
    }

    public void selectFilePDF() {
        downloadFirstAvailableFile();
    }

    public void selectButton() {
        submitForgotPasswordRequest();
    }

    public void selectButtonForm() {
        submitLoginForm();
    }

    public void selectButtonJS() {
        clickJavaScriptAlertButton();
    }

    public void selectButtonJSConfirm() {
        clickJavaScriptConfirmButton();
    }

    public void selectjsPrompt() {
        clickJavaScriptPromptButton();
    }

    public void cancelJavaScriptPrompt() {
        dismissJavaScriptAlert();
    }

    public void sendwriteKeys() {
        typeSampleKeysOneByOne();
    }

    public void typeTextAndAcceptJavaScriptPrompt(String text) {
        typeTextInJavaScriptPrompt(text);
    }

    /**
     * Metodos de leitura e validacao.
     * Este bloco fica no final porque ele reune os retornos usados nos asserts dos testes.
     */
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

    public List<String> getAvailableDownloadFileNames() {
        List<WebElement> fileLinks = visibleAll(downloadLinks);
        List<String> fileNames = new ArrayList<>();

        for (WebElement fileLink : fileLinks) {
            String fileName = fileLink.getText().trim();

            if (!fileName.isEmpty()) {
                fileNames.add(fileName);
            }
        }

        return fileNames;
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

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
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

    public String getEntryText() {
        return visible(closeText).getText();
    }

    public String getEntryTextAd() {
        return visible(entryAdInformationText).getText();
    }

    public String getEntryTextButton() {
        return visible(closeTextButton).getText();
    }

    public String getFlashMessageText() {
        return normalizeText(visible(flashMessage).getText());
    }

    public String getForgotPasswordEmailValue() {
        return visible(forgotPasswordEmailInput).getAttribute("value");
    }

    public String getLeftFrameText() {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(topFrame));
        driver.switchTo().frame(driver.findElement(leftFrame));

        return visible(leftFrameText).getText();
    }

    public String getSecureAreaTitleText() {
        return visible(secureAreaTitle).getText();
    }

    public String getSelectedDropdownOptionText() {
        Select select = new Select(visible(dropdownSelect));
        return select.getFirstSelectedOption().getText();
    }
    
    public String geTextWindows() {
    	return visible(windowsText).getText();
    }
    
    public String getNotificationMessage() {
    	return visible(messageJs)
    			.getText()
    			.split("\n")[0]
    			.trim();
    }

    public String getNumberInputValue() {
        return visible(numberInput).getAttribute("value");
    }

    public int getInfiniteScrollItemsCount() {
        return driver.findElements(infiniteScrollItems).size();
    }

    public String getJqueryUiDescriptionText() {
        return visible(jqueryUiDescriptionText).getText();
    }

    public void setHorizontalSliderValue(String targetValue) {
        WebElement slider = visible(horizontalSliderInput);
        slider.click();

        BigDecimal target = new BigDecimal(targetValue);
        BigDecimal current = new BigDecimal(slider.getAttribute("value"));
        BigDecimal step = new BigDecimal("0.5");

        int moves = target.subtract(current).divide(step).intValueExact();
        Keys direction = moves > 0 ? Keys.ARROW_RIGHT : Keys.ARROW_LEFT;

        for (int index = 0; index < Math.abs(moves); index++) {
            slider.sendKeys(direction);
        }

        wait.until(ExpectedConditions.textToBe(horizontalSliderValue, formatSliderValue(target)));
    }

    public String getHorizontalSliderValue() {
        return visible(horizontalSliderValue).getText();
    }

    /**
     * Formata o valor para ficar igual ao texto exibido na tela.
     */
    private String formatSliderValue(BigDecimal value) {
        return value.stripTrailingZeros().toPlainString();
    }

    public boolean isOnDisappearingElementsPage() {
        return driver.getCurrentUrl().contains("/disappearing_elements");
    }

    public String getTextoJQueryUI() {
        return getJqueryUiDescriptionText();
    }

    public String getJavaScriptAlertResultText() {
        return visible(pageResultText).getText();
    }

    public String getKeyPressesResultText() {
        return visible(pageResultText).getText();
    }

    public String getKeyPressesDescriptionText() {
        return visible(keyPressesDescription).getText();
    }

    public String getResultKeys() {
        return getKeyPressesDescriptionText();
    }

    /**
     * Pausa visual para estudo.
     * Por padrao ela fica ativa para facilitar o acompanhamento da execucao.
     * Se quiser desativar, rode com -Dstudy.mode=false.
     */
    private void visualPause(long milliseconds) {
        if (!Boolean.parseBoolean(System.getProperty("study.mode", "true"))) {
            return;
        }

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Execution was interrupted during the visual pause.", exception);
        }
    }

    /**
     * Move o mouse ate uma opcao do menu.
     * Esse helper e importante porque o JQuery UI Menu abre submenus por hover.
     */
    private void hover(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Actions(driver).moveToElement(element).perform();
    }
}
