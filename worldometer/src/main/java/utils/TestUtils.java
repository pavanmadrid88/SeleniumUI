package utils;

import base.BaseClass;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestUtils extends BaseClass {


    public static String TESTDATA_PATH = "";
    public static Workbook workbook;
    public static Sheet sheet;
    public TestUtils() throws IOException {
    }

    public static Object[][] getInputTestData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\pavan.vasu\\pavan\\projects\\maven\\Selenium\\worldometer\\src\\main\\resources\\testData\\CountryData.xlsx");

        workbook = WorkbookFactory.create(fileInputStream);
        sheet = workbook.getSheet("Sheet1");

        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for(int i=0; i<sheet.getLastRowNum();i++){
            for(int j=0;j<sheet.getRow(0).getLastCellNum();j++){
                data[i][j] = sheet.getRow(i+1).getCell(j).toString();
            }
        }

        return data;


    }


    @DataProvider
    public Object[][] getCountryTestData() throws IOException {
        Object[][] data= TestUtils.getInputTestData();
        return data;

    }

}
