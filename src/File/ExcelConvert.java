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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelConvert {
	private Workbook wb = new XSSFWorkbook();
	private Sheet sheet = wb.createSheet("sheet 1");
	
	public void writingExcelFile(ArrayList<String[]> arrList, int rownum) throws Exception{
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
	
	public void createExcelFile(String filename){
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
	
	
	public ArrayList<double[]> readExcelFileNum(String filepath, String sheetname, int colNum){
		ArrayList<double[]> arrList = new ArrayList<double[]>();
		
		try {
			FileInputStream fileInputStream = new FileInputStream(filepath);
			@SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet worksheet = workbook.getSheet(sheetname);
			for(int i=0;i<=worksheet.getLastRowNum();i++){
				HSSFRow row = worksheet.getRow(i);
				HSSFCell cellColNum=row.getCell(colNum);
				double output = cellColNum.getNumericCellValue();
				double[] list = {output};
				arrList.add(list);
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
	
	public ArrayList<String[]> readExcelFileStr(String filepath, String sheetname, int colNum){
		ArrayList<String[]> arrList = new ArrayList<String[]>();
		try {
			FileInputStream fileInputStream = new FileInputStream(filepath);
			@SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet worksheet = workbook.getSheet(sheetname);
			for(int i=0;i<=worksheet.getLastRowNum();i++){
				HSSFRow row = worksheet.getRow(i);
				HSSFCell cellColNum=row.getCell(colNum);
				String output = cellColNum.getStringCellValue();
				String[] list = {output};
		
				arrList.add(list);
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
