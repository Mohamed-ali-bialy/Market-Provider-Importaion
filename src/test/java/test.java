import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.junit.*;
import org.openqa.selenium.By;
public class test {
    public static boolean added=false;
    @BeforeClass public static void Intialization()
    {
        //adding elements to excel sheet
        MaketImportation_Excel m1=new MaketImportation_Excel();
        String searchby= m1.Modify_Excel(10,"C:\\Users\\Mohamed Elbialy\\IdeaProjects\\Importaion Market Providor Products\\src\\main\\resources\\19-10.xlsx");//element to search by after uplading
        //adding elements to excel sheet
        System.out.println(searchby);


        // Importing EXCEL SHEEET
        String filepath="C:\\Users\\Mohamed Elbialy\\IdeaProjects\\Importaion Market Providor Products\\src\\main\\resources\\19-10.xlsx";
        //initialization
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.get("https://cp-stg.isupply.tech/buyers");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //initialization

        //login
        (driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[2]/input"))).sendKeys("siteadmin@isupply.tech");
        (driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[3]/input"))).sendKeys("Isupply@1234");
        (driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[5]/button"))).click();//login
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        (wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div[6]/button[1]")))).click();//ok
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //login

        //import create page
        (driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div/div/div[9]/a/span[2]"))).click();//import
        (driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div/div[2]/div/a"))).click();//create
        //import create page

        //sending data
        (driver.findElement(By.cssSelector("#importation_type > option:nth-child(1)"))).click();//market
        (driver.findElement(By.cssSelector("#category_id > div > select > option:nth-child(1)"))).click();//pharmacy
        (driver.findElement(By.cssSelector("#market_provider_status_id > div > select > option:nth-child(1)"))).click();//active
        (driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/form/div[1]/div[6]/div/input"))).sendKeys(filepath);//send file
        (driver.findElement(By.cssSelector("#kt_content_container > div > form > div.card-footer > button"))).click();//upload
        //sending data

        //OPEN Products
        (driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div/div/div[5]/span/span[2]"))).click();//Product managment
        (driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div/div/div[5]/div/div[3]/a/span[2]"))).click();//products
        //open products

        //search
        (driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div/div[2]/div/div[1]/div[2]/div/label/input"))).sendKeys(searchby,Keys.ENTER);//searching for element
        String title=(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/div/div/div[2]/div/div[2]/table/tbody/tr[1]/td[3]"))).getText();
        if(title.equals(searchby)){added=true;}//search


        // Importing EXCEL SHEEET
    }

    @Test public void test1(){Assert.assertEquals(true ,added);}
}

