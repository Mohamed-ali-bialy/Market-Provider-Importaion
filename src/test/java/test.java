import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.junit.*;
import org.openqa.selenium.By;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import java.util.Random;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
public class test {
    public static boolean added=false;
    @BeforeClass public static void Intialization()
    {
        String searchby="";//element to search by after uplading

        //creating random values in Excel sheet
        try {

            Random random = new Random();
            int randomNumber ;
            String randomstr;

            //Set the number of rows to be Added
            int row_number=10;//Set the number of rows to be Added

            //initialization
            String filePath = "C:\\Users\\Mohamed Elbialy\\IdeaProjects\\Importaion Market Providor Products\\src\\main\\resources\\19-10.xlsx";//file path
            FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = (XSSFSheet) workbook.getSheet("products"); // Change to the appropriate sheet name or index
            //initialization



            for(int i=1;i<=row_number;i++)//looping on each row  limted by row_number
            {
                int rowIndex = i;
                randomNumber = random.nextInt(90000) + 10000;
                randomstr=String.valueOf(randomNumber);//random string

                // Specify the data to add
                String dataToAdd = "Imporation_Test_"+randomstr;

                if(i==1){searchby=dataToAdd;}//set data to search by

                // Get the row at the specified index
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    row = sheet.createRow(rowIndex);
                }

                Cell cellmoney=row.createCell(3);//money column
                cellmoney.setCellValue("100");//set money

                Cell cellsku=row.createCell(0);//Sku coulumn
                cellsku.setCellValue(randomstr+randomstr);//set SKU

                for(int j =1;j<=2;j++)//looping in each column to set data in the row
                {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(dataToAdd);

                }
            }
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        //creating random values in Excel sheet


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

