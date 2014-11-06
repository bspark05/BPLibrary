package Web.APIs.Foursquare;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class FsToken {	
	public ArrayList<String[]> tokenReady(String filepath, String sheetname){
		ArrayList<String[]> arrList = new ArrayList<String[]>();
		try {
			FileInputStream fileInputStream = new FileInputStream(filepath);
			@SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet worksheet = workbook.getSheet(sheetname);
			for(int i=0;i<=worksheet.getLastRowNum();i++){
				HSSFRow row = worksheet.getRow(i);
				HSSFCell cell0=row.getCell(0);
				String clientId = cell0.getStringCellValue();
				HSSFCell cell1=row.getCell(1);
				String clientPw = cell1.getStringCellValue();
				HSSFCell cell2=row.getCell(2);
				String tokenDT = cell2.getStringCellValue();
				
				String[] arr = {clientId, clientPw, tokenDT};
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
	
	public String[] tokenIterator(ArrayList<String[]> tokenList, int index){
		String[] tokenPair = {"", "", ""};
		int i = index%tokenList.size();
		tokenPair = tokenList.get(i);
		index++;
		if(index>tokenList.size()){
			index=0;
		}
		return tokenPair;
	}
}
