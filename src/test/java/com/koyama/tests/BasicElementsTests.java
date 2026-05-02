package com.koyama.tests;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.koyama.base.UiTestSupport;
import com.koyama.listeners.ScreenshotListener;
import com.koyama.pages.TheInternetPage;

@Listeners(ScreenshotListener.class)
public class BasicElementsTests extends UiTestSupport {

    private TheInternetPage page;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        page = new TheInternetPage(driver, wait);
    }

    @Test
    public void shouldOpenABTestingPageAndDisplayParagraph() {
        page.openHomePage();
        page.goToAbTesting();

        String paragraphText = page.getABTestingParagraphText();

        Assert.assertTrue(
            paragraphText.contains("split testing"),
            "The A/B Testing page should describe split testing."
        );
    }

    @Test
    public void shouldAddFiveElementsSuccessfully() {
        page.openHomePage();
        page.goToAddRemoveElements();

        page.addElements(5);

        Assert.assertEquals(
            page.getDeleteButtonsCount(),
            5,
            "The Delete button count should be 5."
        );
    }

    @Test
    public void shouldOpenBrokenImagesPageAndDetectBrokenImages() {
        page.openHomePage();
        page.goToBrokenImages();

        Assert.assertEquals(
            page.getBrokenImagesTitleText(),
            "Broken Images",
            "The Broken Images page title is incorrect."
        );

        Assert.assertTrue(
            page.countBrokenImages() > 0,
            "The scenario should contain at least one broken image."
        );
    }

    @Test
    public void shouldOpenChallengingDomPageAndValidateParagraph() {
        page.openHomePage();
        page.goToChallengingDom();
        page.clickQuxButtonMultipleTimes(5);

        String paragraphText = page.getChallengingDomParagraphText();

        Assert.assertTrue(
            paragraphText.contains("The hardest part in automated web testing"),
            "The Challenging DOM main paragraph was not displayed as expected."
        );
    }

    @Test
    public void shouldSelectAllCheckboxesSuccessfully() {
        page.openHomePage();
        page.goToCheckboxes();

        page.selectAllCheckboxes();

        Assert.assertTrue(
            page.areAllCheckboxesSelected(),
            "All checkboxes should remain selected."
        );
    }

    @Test
    public void shouldOpenContextMenuPageAndDisplayAlert() {
        page.openHomePage();
        page.goToContextMenu();

        Assert.assertEquals(
            page.getContextMenuDescriptionText(),
            "Context menu items are custom additions that appear in the right-click menu.",
            "The Context Menu description text is incorrect."
        );

        page.rightClickContextMenuBox();

        Assert.assertEquals(
            page.getContextMenuAlertText(),
            "You selected a context menu",
            "The expected context menu alert was not displayed."
        );

        page.acceptContextMenuAlert();
    }

    @Test
    public void shouldDisplayDynamicContentDescription() {
        page.openHomePage();
        page.goToDynamicContent();

        Assert.assertTrue(
            page.getDynamicContentDescriptionText().contains("ever-evolving nature of content"),
            "The Dynamic Content page description should explain the page behaviour."
        );
    }

    @Test
    public void shouldRemoveCheckboxAndEnableInputSuccessfully() {
        page.openHomePage();
        page.goToDynamicControls();

        page.removeCheckbox();
        Assert.assertEquals(
            page.getDynamicControlsMessageText(),
            "It's gone!",
            "The checkbox removal message is incorrect."
        );

        page.enableInputField();
        Assert.assertEquals(
            page.getDynamicControlsMessageText(),
            "It's enabled!",
            "The input enable message is incorrect."
        );
    }

    @Test
    public void shouldLoadDynamicContentExamplesSuccessfully() {
        page.openHomePage();
        page.goToDynamicLoading();

        Assert.assertEquals(
            page.getDynamicLoadingTitleText(),
            "Dynamically Loaded Page Elements",
            "The Dynamic Loading page title is incorrect."
        );

        Assert.assertTrue(
            page.getDynamicLoadingDescriptionText().contains("returns a result dynamically"),
            "The Dynamic Loading page description should explain the dynamic behaviour."
        );

        page.openDynamicLoadingExample1();
        page.startDynamicLoadingExample();
        Assert.assertEquals(
            page.getDynamicLoadingFinishText(),
            "Hello World!",
            "Example 1 should display the final loaded text."
        );

        page.navigateBack("/dynamic_loading");

        page.openDynamicLoadingExample2();
        page.startDynamicLoadingExample();
        Assert.assertEquals(
            page.getDynamicLoadingFinishText(),
            "Hello World!",
            "Example 2 should display the final loaded text."
        );
    }

    @Test
    public void shouldDisplayAndCloseEntryAdSuccessfully() {
        page.openHomePage();
        page.goToEntryAd();

        Assert.assertEquals(
            page.getEntryTextButton(),
            "Close",
            "The Entry Ad modal close text is incorrect."
        );

        Assert.assertEquals(
            page.getEntryText(),
            "It's commonly used to encourage a user to take an action "
                + "(e.g., give their e-mail address to sign up for something or disable their ad blocker).",
            "The Entry Ad modal body text is incorrect."
        );

        page.selectCloseLink();

        Assert.assertEquals(
            page.getEntryTextAd(),
            "If closed, it will not appear on subsequent page loads.",
            "The Entry Ad page should explain the post-close behaviour."
        );
    }

    @Test
    public void shouldDownloadFirstAvailableFileSuccessfully() {
        page.openHomePage();
        page.goToFileDownload();

        List<String> availableFileNames = page.getAvailableDownloadFileNames();
        Assert.assertFalse(
            availableFileNames.isEmpty(),
            "The File Download page should expose at least one file."
        );

        String downloadedFileName = page.downloadFirstAvailableFile();
        Assert.assertTrue(
            availableFileNames.contains(downloadedFileName),
            "The downloaded file should come from the list displayed on the page."
        );

        Path downloadedFile = waitForDownloadedFile(downloadedFileName);
        Assert.assertTrue(
            Files.exists(downloadedFile),
            "The selected file should be downloaded to the configured directory."
        );
    }

    @Test
    public void shouldNavigateFloatingMenuAnchorsSuccessfully() {
        page.openHomePage();
        page.goToFloatingMenu();

        page.navigateFloatingMenuAnchors();

        Assert.assertTrue(
            page.getCurrentUrl().endsWith("#about"),
            "The floating menu flow should finish on the About anchor."
        );
    }

    @Test
    public void shouldFillForgotPasswordFormSuccessfully() {
        page.openHomePage();
        page.goToForgotPassword();

        String email = "selenium@teste.com.br";
        page.typeForgotPasswordEmail(email);

        Assert.assertEquals(
            page.getForgotPasswordEmailValue(),
            email,
            "The forgot password e-mail field should keep the typed value."
        );
    }

    @Test
    public void shouldLogIntoSecureAreaSuccessfully() {
        page.openHomePage();
        page.goToFormAuthentication();

        page.typeUsername("tomsmith");
        page.typeLoginPassword("SuperSecretPassword!");
        page.submitLoginForm();

        Assert.assertEquals(
            page.getSecureAreaTitleText(),
            "Secure Area",
            "The secure area title is incorrect after login."
        );

        Assert.assertTrue(
            page.getFlashMessageText().contains("You logged into a secure area!"),
            "The success message should confirm the login."
        );
    }

    @Test
    public void shouldValidateNestedFramesSuccessfully() {
        page.openHomePage();
        page.goToFrames();

        page.selectLinkNested();
        Assert.assertEquals(page.getLeftFrameText(), "LEFT");
        page.navigateBack("/frames");
    }

    @Test
    public void shouldMoveHorizontalSliderToThreePointFive() {
        page.openHomePage();
        page.goToHorizontalSlider();

        page.setHorizontalSliderValue("3.5");

        Assert.assertEquals(
            page.getHorizontalSliderValue(),
            "3.5",
            "O valor do slider deveria ser 3.5."
        );
    }

    @Test
    public void shouldIncreaseNumberInputSuccessfully() {
        page.openHomePage();
        page.goToInputNumber();

        page.typeNumberInput("100");
        page.increaseNumberInput(10);

        Assert.assertEquals(
            page.getNumberInputValue(),
            "110",
            "O campo numerico deveria conter o valor 110."
        );
    }

    @Test
    public void shouldDownloadPdfFromJqueryUiMenu() {
        page.openHomePage();
        page.goToJqueryUiMenu();

        page.downloadPdfFromJqueryUiMenu();
    }

    @Test
    public void shouldNavigateBackFromJqueryUiMenu() {
        page.openHomePage();
        page.goToJqueryUiMenu();

        page.selectBackToJqueryUi();

        Assert.assertEquals(
            page.getJqueryUiDescriptionText(),
            "JQuery UI is many things, but one thing specifically that causes automation challenges is their set of Widgets"
        );
    }

    @Test
    public void shouldLoadNewContentWhenScrollingDown() {
        page.openHomePage();
        page.goToInfiniteScroll();

        int initialCount = page.getInfiniteScrollItemsCount();

        page.scrollUntilNewContentLoads();

        int finalCount = page.getInfiniteScrollItemsCount();

        Assert.assertTrue(
            finalCount > initialCount,
            "A pagina deveria carregar novos blocos apos o scroll."
        );
    }
    
    @Test
    public void shoudAlerts() {
    	   page.openHomePage();
    	   page.goToScriptsAlerts();
    	   
    	   page.selectButtonJS();
    	   
  
    	   page.acceptJavaScriptAlert();
    	   Assert.assertEquals(page.getJavaScriptAlertResultText(), 
    			   "You successfully clicked an alert");
    	   
    	   page.selectButtonJSConfirm();
    	   
    	   
    	   page.cancelJavaScriptPrompt();
    	   Assert.assertEquals(page.getJavaScriptAlertResultText(),
    			   "You clicked: Cancelado",
    			   "A mensagem final deveria mostrar Cancelado quando o prompt for cancelado.");
    	   
    	   page.selectjsPrompt();
       page.typeTextAndAcceptJavaScriptPrompt("Koyama estudando Selenium");
    	   
    	   page.acceptJavaScriptAlert();
    }

    @Test
    public void sendKeysPresses() {
 	   page.openHomePage();
 	   page.goToKeyInput();
 	   
 	   page.sendwriteKeys();
 	   Assert.assertEquals(page.getResultKeys(),  
 			   "Key presses are often used to interact with a website (e.g., tab order, enter, escape, etc.). "
 			   + "Press a key and see what you inputted.");
 	   
 	   Keys[] keys = {Keys.SPACE,Keys.BACK_SPACE,Keys.TAB};
	   
	   page.pressKeysSlowly(keys);
	   Assert.assertEquals(page.getJavaScriptAlertResultText(), 
			   "You entered: TAB");
   }


    @Test
    public void shouldSelectDropdownOptionsSuccessfully() {
        page.openHomePage();
        page.goToDropdown();

        String[] options = {"Option 1", "Option 2"};

        for (String option : options) {
            page.selectDropdownOptionByText(option);
            Assert.assertEquals(
                page.getSelectedDropdownOptionText(),
                option,
                "The selected dropdown option does not match the expected value."
            );
        }
    }

    @Test
    public void shouldNavigateThroughDisappearingElementsAndReturn() {
        page.openHomePage();
        page.goToDisappearingElements();
        page.clickHomeAndReturnToDisappearingElements();

        page.openLinkFromDisappearingElementsAndReturn("About", "/about/");
        page.openLinkFromDisappearingElementsAndReturn("Contact Us", "/contact-us/");
        page.openLinkFromDisappearingElementsAndReturn("Portfolio", "/portfolio/");

        Assert.assertTrue(
            page.isOnDisappearingElementsPage(),
            "The flow should end on the Disappearing Elements page."
        );
    }

    @Test
    public void shouldDragAndDropColumnsSuccessfully() {
        page.openHomePage();
        page.goToDragAndDrop();
        page.dragColumnAToColumnB();

        Assert.assertEquals(
            page.getColumnAHeaderText(),
            "B",
            "Column A should display header B after drag and drop."
        );

        Assert.assertEquals(
            page.getColumnBHeaderText(),
            "A",
            "Column B should display header A after drag and drop."
        );
    }
}
