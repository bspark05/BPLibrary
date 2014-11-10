package Web.APIs.Foursquare;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class FsURL {
	private String URL;
	
	public String getURL() {
		return URL;
	}

	public ArrayList<double[]> boundaryReady(String filepath, String sheetname){
		ArrayList<double[]> arrList = new ArrayList<double[]>();
		try {
			FileInputStream fileInputStream = new FileInputStream(filepath);
			@SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet worksheet = workbook.getSheet(sheetname);
			for(int i=0;i<=worksheet.getLastRowNum();i++){
				HSSFRow row = worksheet.getRow(i);
				HSSFCell cell0=row.getCell(0);
				double objectId = cell0.getNumericCellValue();
				HSSFCell cell1=row.getCell(1);
				double top = cell1.getNumericCellValue();
				HSSFCell cell2=row.getCell(2);
				double bottom = cell2.getNumericCellValue();
				HSSFCell cell3=row.getCell(3);
				double left = cell3.getNumericCellValue();
				HSSFCell cell4=row.getCell(4);
				double right = cell4.getNumericCellValue();
				
				double[] arr = {objectId, top, bottom, left, right};
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
	public String makeURL_venues_search(double radius, double latsql, double lngsql, String[] tokenSet){
		String clientId = tokenSet[0];
		String clientSc = tokenSet[1];
		String tokenDate = tokenSet[2];
		
		String url = "https://api.foursquare.com/v2/venues/search?ll="+latsql+","+lngsql+"&radius="+radius+"&limit=50&&client_id="+clientId+"&client_secret="+clientSc+"&v="+tokenDate;
		return url;
	}
	
	public String makeURL_venues_nextVenues(String placeID, String[] tokenSet){
		String clientId = tokenSet[0];
		String clientSc = tokenSet[1];
		String tokenDate = tokenSet[2];
		
		String url = "https://api.foursquare.com/v2/venues/"+placeID+"/nextvenues?client_id="+clientId+"&client_secret="+clientSc+"&v="+tokenDate;
		return url;
	}
	
}
