package File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class ExcelConvert {
	
	public void writingExcelFile(ArrayList<String[]> arrList, Sheet sheet, int rownum) throws Exception{
		try {
	        for (int j = 0; j < arrList.size(); j++) {
	            Row row = sheet.createRow(rownum);
	            String[] arrListRow = arrList.get(j);
	            for (int k = 0; k < arrListRow.length; k++) {
	                Cell cell = row.createCell(k);
	                cell.setCellValue(arrListRow[k]);
	            }
	            rownum++;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    }
	}
	
	public void createExcelFile(Workbook wb, String filename){
		FileOutputStream fileOut;
        try {
             
            fileOut = new FileOutputStream(filename+".xls");
            wb.write(fileOut);
            fileOut.close();
             
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        System.out.print( "File created!" );
	}
	
	
	public ArrayList<double[]> readExcelFileNum(String filepath, String sheetname){
		ArrayList<double[]> arrList = new ArrayList<double[]>();
		try {
			FileInputStream fileInputStream = new FileInputStream(filepath);
			@SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet worksheet = workbook.getSheet(sheetname);
			for(int i=0;i<=worksheet.getLastRowNum();i++){
				HSSFRow row = worksheet.getRow(i);
				HSSFCell cell0=row.getCell(0);
				double objectID = cell0.getNumericCellValue();
				HSSFCell cell1=row.getCell(1);
				double right = cell1.getNumericCellValue();
				HSSFCell cell2=row.getCell(2);
				double top = cell2.getNumericCellValue();
				HSSFCell cell3=row.getCell(3);
				double left = cell3.getNumericCellValue();
				HSSFCell cell4=row.getCell(4);
				double bottom = cell4.getNumericCellValue();
				
				double[] arr = {objectID, top, bottom, left, right};
				arrList.add(arr);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrList;
		
	}
	
	public ArrayList<String[]> readExcelFileStr(String filepath, String sheetname){
		ArrayList<String[]> arrList = new ArrayList<String[]>();
		try {
			FileInputStream fileInputStream = new FileInputStream(filepath);
			@SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet worksheet = workbook.getSheet(sheetname);
			for(int i=0;i<=worksheet.getLastRowNum();i++){
				HSSFRow row = worksheet.getRow(i);
				HSSFCell cell0=row.getCell(0);
				String client_id = cell0.getStringCellValue();
				HSSFCell cell1=row.getCell(1);
				String client_secret = cell1.getStringCellValue();
				HSSFCell cell2=row.getCell(2);
				String tokenDate = cell2.getStringCellValue();
				
				String[] arr = {client_id, client_secret, tokenDate};
				arrList.add(arr);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrList;
		
		
	}
}
