package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialsPage {


    public CredentialsPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);

    }

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;


    @FindBy(id = "addNewCredential")
    private WebElement addNewCredential;

    @FindBy(id = "credentialURL")
    private WebElement credentialURL;

    @FindBy(id = "credentialUsername")
    private WebElement credentialUsername;

    @FindBy(id = "credentialPassword")
    private WebElement credentialPassword;

    @FindBy(id = "credential-url")
    private WebElement credentialURLValue;

    @FindBy(id = "credential-username")
    private WebElement credentialUsernameValue;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordValue;


    @FindBy(id = "credentialSubmitButton")
    private WebElement credentialSubmitButton;

    @FindBy(id = "homePageRedirect")
    private WebElement homePageRedirect;

    @FindBy(id = "editCredentialButton")
    private WebElement editCredentialButton;

    @FindBy(id = "deleteCredentialButton")
    private WebElement deleteCredentialButton;

    @FindBy(id = "deletehomePageRedirect")
    private WebElement deletehomePageRedirect;


    public void clickCredentialsTab() {
        navCredentialsTab.click();

    }

    public void addNewCredential() {
        addNewCredential.click();

    }

    public void fillCredentialDetails(String url, String username, String password) {
        this.credentialURLValue.sendKeys(url);
        this.credentialUsernameValue.sendKeys(username);
        this.credentialPasswordValue.sendKeys(password);
    }

    public void submitCredentials() {
        credentialSubmitButton.click();

    }

    public void redirectHome() {
        homePageRedirect.click();

    }

    public boolean credentialDisplayed() {
        try {
            if (credentialURL.isDisplayed() && credentialUsername.isDisplayed() && credentialPassword.isDisplayed())
                return true;
            else return false;
        } catch (Exception e) {
            return false;
        }

    }

    public void editCredential() {
        editCredentialButton.click();

    }


    public void editCredentialDetails(String url, String username, String password) {
        this.credentialURLValue.sendKeys(url);
        this.credentialUsernameValue.sendKeys(username);
        this.credentialPasswordValue.sendKeys(username);

    }

    public void deleteCredentials() {
        deleteCredentialButton.click();

    }

    public void deleteRedirectHome() {
        deletehomePageRedirect.click();

    }


}
