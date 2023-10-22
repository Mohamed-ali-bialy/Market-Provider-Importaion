import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class MaketImportation_Excel {

    public String Modify_Excel(int numb_row,String inputAbsolutePath)
    {
        String searchby="";//element to search by after uplading
        try {

            Random random = new Random();
            int randomNumber ;
            String randomstr;

            //Set the number of rows to be Added
            int row_number=numb_row;//Set the number of rows to be Added

            //initialization
            String filePath = inputAbsolutePath.replace("\\", "\\\\");//file path
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


    return  searchby;}
}
