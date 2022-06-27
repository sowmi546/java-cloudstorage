package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.util.Assert;

import java.io.File;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	public int port;

	public static WebDriver driver;

	public String baseURL;
	private final String firstName = "Sowmya";
	private final String lastName = "Duggimpudi";
	private final String userName = "sowmi546";
	private final String password = "test";
	private final String noteTitle = "test title";
	private final String noteDescription = "test description";
	private final String url = "www.google.com";
	private final String credUsername =  "test credential";
	private final String credPassword = "test password";

	@BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

	}

	@AfterAll
	public static void afterAll() {
		driver.quit();
		driver = null;
	}

	@BeforeEach
	public void beforeEach() {
		baseURL = "http://localhost:" + port;
	}


	/**
	 * Test to restrict access to homepage for non logged in user
	 */
	@Test
	public void accessRestrictionForHomePage(){
		driver.get(baseURL +"/home");
		Assertions.assertEquals("Login", driver.getTitle());

	}


	@Test
	@Order(1)
	public void userLoginFlow(){


		driver.get(baseURL+"/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		SignUpPage signupPage =  new SignUpPage(driver);
		signupPage.signup(firstName, lastName, userName, password);

		driver.get(baseURL+"/login");
		Assertions.assertEquals("Login", driver.getTitle());
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(userName, password);



		Assertions.assertEquals("Home",driver.getTitle());

		//in homepage the user has to click logout button and we need to check he's redirected to login page

		HomePage homePage = new HomePage(driver);
		homePage.userLogout();
		Assertions.assertEquals("Login", driver.getTitle());





	}

	/**
	 * login as user and note functionality
	 */
	@Test
	@Order(2)
	public void notesTests() throws InterruptedException{

		driver.get(baseURL+"/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		SignUpPage signupPage =  new SignUpPage(driver);
		signupPage.signup(firstName, lastName, userName, password);

		driver.get(baseURL + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(userName, password);
		new WebDriverWait(driver, 5).until(ExpectedConditions.titleIs("Home"));


		Assertions.assertEquals("Home",driver.getTitle());

		NotesPage notesPage = new NotesPage(driver);
		notesPage.clickNotesTab();
		Thread.sleep(2000);
		notesPage.addNewNote();
		Thread.sleep(2000);

		notesPage.fillNotesDetails(noteTitle, noteDescription);
		Thread.sleep(2000);
		notesPage.submitNote();
		Thread.sleep(2000);

		notesPage.redirectHome();
		Assertions.assertEquals("Home",driver.getTitle());

		notesPage.clickNotesTab();
		Thread.sleep(2000);
		Assertions.assertEquals(true, notesPage.noteDisplayed());

		//editing an existing note
		notesPage.editNote();
		Thread.sleep(2000);

		notesPage.editNoteDetails("updated title", "updated description");
		notesPage.submitNote();
		Thread.sleep(2000);
		notesPage.redirectHome();
		Assertions.assertEquals("Home",driver.getTitle());

		notesPage.clickNotesTab();
		Thread.sleep(2000);
		Assertions.assertEquals(true, notesPage.noteDisplayed());

		//deleting a note
		notesPage.deleteNote();
		Thread.sleep(2000);
		notesPage.deleteRedirectHome();
		Assertions.assertEquals("Home", driver.getTitle());

		notesPage.clickNotesTab();
		Thread.sleep(2000);
		Assertions.assertEquals(false,notesPage.noteDisplayed() );
	}

	@Test
	public void credentialTests() throws InterruptedException {

		driver.get(baseURL+"/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		SignUpPage signupPage =  new SignUpPage(driver);
		signupPage.signup(firstName, lastName, userName, password);

		driver.get(baseURL + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(userName, password);
		new WebDriverWait(driver, 5).until(ExpectedConditions.titleIs("Home"));


		Assertions.assertEquals("Home",driver.getTitle());
		CredentialsPage credentialsPage = new CredentialsPage(driver);
		credentialsPage.clickCredentialsTab();
		Thread.sleep(2000);
		credentialsPage.addNewCredential();
		Thread.sleep(2000);

		credentialsPage.fillCredentialDetails(url,credUsername,credPassword);
		Thread.sleep(2000);
		credentialsPage.submitCredentials();
		Thread.sleep(2000);

		credentialsPage.redirectHome();
		Assertions.assertEquals("Home",driver.getTitle());

		credentialsPage.clickCredentialsTab();
		Thread.sleep(2000);
		Assertions.assertEquals(true, credentialsPage.credentialDisplayed());

		/** editing an existing credential
		 *
		 */

		credentialsPage.editCredential();
		Thread.sleep(2000);

		credentialsPage.editCredentialDetails("updated url", "updated username", "updated password");
		credentialsPage.submitCredentials();
		Thread.sleep(2000);
		credentialsPage.redirectHome();
		Assertions.assertEquals("Home",driver.getTitle());

		credentialsPage.clickCredentialsTab();
		Thread.sleep(2000);
		Assertions.assertEquals(true, credentialsPage.credentialDisplayed());

		//deleting a note
		credentialsPage.deleteCredentials();
		Thread.sleep(2000);
		credentialsPage.deleteRedirectHome();
		Assertions.assertEquals("Home", driver.getTitle());

		credentialsPage.clickCredentialsTab();
		Thread.sleep(2000);
		Assertions.assertEquals(false,credentialsPage.credentialDisplayed() );




	}









	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
		
		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}

	
	
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling redirecting users 
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric: 
	 * https://review.udacity.com/#!/rubrics/2724/view 
	 */
	@Test
	public void testRedirection() {
		// Create a test account
		doMockSignUp("Redirection","Test","RT","123");
		
		// Check if we have been redirected to the log in page.
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs 
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at: 
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {
		// Create a test account
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");
		
		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code. 
	 * 
	 * Read more about file size limits here: 
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File","Test","LFT","123");
		doLogIn("LFT", "123");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

	}







}
