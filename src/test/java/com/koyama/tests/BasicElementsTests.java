package com.koyama.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.koyama.base.BaseTest;
import com.koyama.pages.TheInternetPage;

public class BasicElementsTests extends BaseTest {

    private TheInternetPage page;

    @BeforeMethod
    public void initPage() {
        page = new TheInternetPage(driver, wait);
    }

    @Test
    public void shouldOpenABTestingPageAndDisplayParagraph() {
        page.openHomePage();
        page.goToABTesting();

        String paragraphText = page.getABTestingParagraphText();

        Assert.assertTrue(
            paragraphText.contains("Also known as split testing"),
            "O parágrafo da página A/B Testing não contém o texto esperado."
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
            "A quantidade de botões Delete deveria ser 5."
        );
    }

    @Test
    public void shouldOpenBrokenImagesPageSuccessfully() {
        page.openHomePage();
        page.goToBrokenImages();

        Assert.assertEquals(
            page.getBrokenImagesTitleText(),
            "Broken Images",
            "O título da página Broken Images está incorreto."
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
            "O texto principal da página Challenging DOM não foi encontrado."
        );
    }

    @Test
    public void shouldSelectAllCheckboxesSuccessfully() {
        page.openHomePage();
        page.goToCheckboxes();

        page.selectAllCheckboxes();

        Assert.assertTrue(
            page.areAllCheckboxesSelected(),
            "Nem todos os checkboxes ficaram marcados."
        );
    }

    @Test
    public void shouldOpenContextMenuPageSuccessfully() {
        page.openHomePage();
        page.goToContextMenu();

        Assert.assertEquals(
            page.getContextMenuDescriptionText(),
            "Context menu items are custom additions that appear in the right-click menu.",
            "O texto descritivo da página Context Menu está incorreto."
        );
    }
    
    @Test
    public void contentDynamicParagraph() {
    	page.openHomePage();
    	page.goToContextDynamic();
    	
    	Assert.assertEquals(page.getContextDynamicDescriptionText(),
         "This example demonstrates the ever-evolving nature of content by loading new text and images on each page refresh.");
    }
    
    
    @Test
    public void shouldSelectDropdown() {
    	page.openHomePage();
    	page.gotoDropdown();
    	
    	String[] options = {"Option 1","Option 2"};
    	
    	for (String option : options) {
			
    		page.selectDropdownOptionByText(option);
		}    	
    }

    @Test
    public void shouldNavigateThroughDisappearingElementsAndReturn() {
        page.openHomePage();
        page.goToDisappearingElements();
        page.clickHomeAndReturnToDisappearingElements();

        page.openLinkAndReturn("About", "/about/");
        page.openLinkAndReturn("Contact Us", "/contact-us/");
        page.openLinkAndReturn("Portfolio", "/portfolio/");

        Assert.assertTrue(
            page.isOnDisappearingElementsPage(),
            "O teste deveria terminar na página Disappearing Elements."
        );
    }

    @Test
    public void shouldDragAndDropColumnsSuccessfully() {
        page.openHomePage();
        page.goToDragAndDrop();
        page.dragColumnAToColumnB();

        Assert.assertEquals(page.getColumnAHeaderText(),"B","A coluna A deveria conter o header B após o drag and drop.");

        Assert.assertEquals(page.getColumnBHeaderText(),"A","A coluna B deveria conter o header A após o drag and drop.");
    }
}