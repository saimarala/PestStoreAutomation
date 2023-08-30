package api.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class XLUtility1 {
    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook wb;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path=null;

   public XLUtility1(String path){
        this.path=path;
    }
    public int getRowCount(String sheetName) throws Exception {
        fi=new FileInputStream(path);
        wb=new XSSFWorkbook(fi);
        sheet= wb.getSheet(sheetName);
        int rowCount=sheet.getLastRowNum();
        wb.close();
        fi.close();
        return rowCount;
    }
    public int getCellCount(String sheetName,int rowNum)throws Exception{
        fi=new FileInputStream(path);
        wb=new XSSFWorkbook(fi);
        sheet=wb.getSheet(sheetName);
        int cellCount=sheet.getRow(rowNum).getLastCellNum();
        wb.close();
        fi.close();
        return cellCount;
    }
    public Object   getCellData(String sheetName,int rowNum,int colNum)throws Exception{
        fi=new FileInputStream(path);
        wb=new XSSFWorkbook(fi);
        sheet=wb.getSheet(sheetName);
//        row=sheet.getRow(rowNum);
//        cell=row.getCell(colNum);
 //       DataFormatter formatter=new DataFormatter();
        Object data;
        try{
            data=new DataFormatter().formatCellValue(sheet.getRow(rowNum).getCell(colNum));
            //Returned the formatted value of a cell in to string regardless of celltype
        }catch (Exception e){
            data="";
        }
        wb.close();
        fi.close();
        return data;
    }
    public void setCellData(String sheetName,int rowNum,int colNum,String data)throws Exception{
        fi=new FileInputStream(path);
        wb=new XSSFWorkbook(fi);
        sheet=wb.getSheet(sheetName);
        row=sheet.getRow(rowNum);
        cell=row.createCell(colNum);
        cell.setCellValue(data);
        fo=new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();

    }

}
