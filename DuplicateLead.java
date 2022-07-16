package week2.day1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DuplicateLead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strUsername="demosalesmanager";
		String strPassword="crmsfa";
		//we have to call WDM for the browser driver
		WebDriverManager.chromedriver().setup(); //verify the version, download,setup		
		ChromeDriver driver=new ChromeDriver();		//launch the browser -chrome
		//Open browser and login
		driver.get("http://leaftaps.com/opentaps");		 //load the url
		driver.manage().window().maximize(); //maximize the browser		
		driver.findElement(By.id("username")).sendKeys(strUsername);	//enter the username in the username field	
		driver.findElement(By.id("password")).sendKeys(strPassword);		//enter the password in the password field
		driver.findElement(By.className("decorativeSubmit")).click();//Click the login button


		//verify if login is successful
		WebElement logout= driver.findElement(By.className("decorativeSubmit")); //Check if we are in the right page
		String attribute=logout.getAttribute("value"); //Get the attribute and print
		System.out.println(attribute);		
		if(attribute.equals("Logout")){
			System.out.println("Successfully logged in to leaftaps as: " +strUsername);
		}	

		driver.findElement(By.linkText("CRM/SFA")).click(); //click CRM/SFA button

		//^^^^^^^Create Lead^^^^^^
		driver.findElement(By.linkText("Leads")).click(); //CLICK THE Leads tab link		
		driver.findElement(By.linkText("Create Lead")).click(); //click the create link id            
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys("SeleniumTest");//Enter company name
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys("Uma");//Enter the first name
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys("G");//Enter the last name
		driver.findElement(By.id("createLeadForm_firstNameLocal")).sendKeys("uma");//Enter the first name(local)
		driver.findElement(By.id("createLeadForm_departmentName")).sendKeys("Testing");
		driver.findElement(By.id("createLeadForm_description")).sendKeys("This is a testcase to understand the usage of locators in Selenium in week2Day1.");
		driver.findElement(By.name("primaryEmail")).sendKeys("uma123@gmail.com");
		WebElement dropStateOrProvince = driver.findElement(By.id("createLeadForm_generalStateProvinceGeoId"));//Webelement dropdown object
		Select objProvince=new Select(dropStateOrProvince); //creating a select class to select value from dropdown
		objProvince.selectByVisibleText("New York"); //Select the state or province from dropdown
		driver.findElement(By.className("smallSubmit")).click();  //click the create lead button

		//verify if the lead is created-page verification
		String strTitle=driver.getTitle();
		//System.out.println(strTitle);
		if(strTitle.equals("View Lead | opentaps CRM")) { //Checking title of the page
			System.out.println("Successfully created lead");
		}
		else{
			System.out.println("Couldnot create lead");
		}
		
		//Duplicate lead
		driver.findElement(By.linkText("Duplicate Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).clear();//Clear the field
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys("NewCompany");
		driver.findElement(By.id("createLeadForm_firstName")).clear();//clear the name field
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys("uma g"); //enter new value
		driver.findElement(By.className("smallSubmit")).click(); 
		if(strTitle.equals("View Lead | opentaps CRM")) { //Checking title of the page
			System.out.println("Successfully updated lead");
		}
		else{
			System.out.println("Couldnot update lead");
		}
		
		

		//Close Browser"
		driver.close();



	}

}
