package com.udacity.jwdnd.course1.cloudstorage;

import org.h2.mvstore.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotesPage {

    public NotesPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "addNewNote")
    private WebElement addNewNote;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteTitleValue")
    private WebElement noteTitleValue;

    @FindBy(id = "noteTitleDescription")
    private WebElement noteDescriptionValue;

    @FindBy(id = "noteSubmitButton")
    private WebElement noteSubmitButton;

    @FindBy(id = "homePageRedirect")
    private WebElement homePageRedirect;

    @FindBy(id = "editNoteButton")
    private WebElement editNoteButton;

    @FindBy(id = "deleteNoteButton")
    private WebElement deleteNoteButton;

    @FindBy(id = "deletehomePageRedirect")
    private WebElement deletehomePageRedirect;

    public void clickNotesTab() {
        navNotesTab.click();

    }

    public void addNewNote() {
        addNewNote.click();
    }

    public void fillNotesDetails(String noteTitle, String noteDescription) {
        this.noteTitle.sendKeys(noteTitle);
        this.noteDescription.sendKeys(noteDescription);

    }

    public void submitNote() {
        noteSubmitButton.click();
    }

    public void redirectHome() {
        homePageRedirect.click();

    }

    public void deleteRedirectHome() {
        deletehomePageRedirect.click();

    }

    /**
     * error thrown if the element is not present at all. Using try catch to handle this
     */
    public boolean noteDisplayed() {
        try {
            if (noteTitleValue.isDisplayed() && noteDescriptionValue.isDisplayed()) return true;
            else return false;
        } catch (Exception e) {
            return false;
        }


    }

    public void editNote() {
        editNoteButton.click();
    }

    public void deleteNote() {
        deleteNoteButton.click();
    }

    public void editNoteDetails(String updatedTitle, String updatedDescription) {
        this.noteTitle.sendKeys(updatedTitle);
        this.noteDescription.sendKeys(updatedDescription);
    }
}
