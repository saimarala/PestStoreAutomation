package api.utilities;

import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {
    static XSSFWorkbook wb;
    static XSSFSheet sheet;
    XSSFRow row;
    XSSFCell cell;
//    static String path=null;
//    ExcelReader(String path){
//        this.path=path;
//    }
    public  Object [][] excelTestData() throws IOException {
        System.out.println("************ Loading Data From Excel *******************");
        Object [][] arr=null;
        wb=new XSSFWorkbook(new FileInputStream(System.getProperty("user.dir")+"//testData//userData.xlsx"));
        sheet=wb.getSheet("TestData");
        int rows=sheet.getPhysicalNumberOfRows();
        int columns=sheet.getRow(0).getPhysicalNumberOfCells();
        arr=new Object[rows-1][columns];
        for(int i=1;i<rows;i++){
            for (int j=0;j<columns;j++){
                arr[i][j]=new DataFormatter().formatCellValue(sheet.getRow(i+1).getCell(j));
            }
        }
        wb.close();
        return arr;
    }
}
