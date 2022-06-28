package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);

    }

    @FindBy(id = "fileUpload")
    private WebElement inputFileUpload;

    @FindBy(className = "files")
    private WebElement filesList;

    @FindBy(className = "viewFile")
    private WebElement viewFile;

    @FindBy(className = "deleteFile")
    private WebElement deleteFile;

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    public void userLogout() {
        logoutButton.click();

    }


}
