import java.util.ArrayList;

import File.ExcelConvert;
import Web.APIs.Foursquare.FoursquareAPI;
import Web.APIs.Foursquare.FsToken;
import Web.APIs.Foursquare.FsURL;



public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		double lat;
		double lng;
		double radius = 62 * Math.sqrt(2);
		
		FoursquareAPI fsAPI = new FoursquareAPI();
		FsURL fsURL = new FsURL();
		FsToken fstoken = new FsToken();
		ExcelConvert exConverter= new ExcelConvert(); 
		
		//Read Token table
		ArrayList<String[]> token = fstoken.tokenReady("tokenList.xls", "sheet1");
		
		//Read Boundary table
		ArrayList<double[]> boundary = fsURL.boundaryReady("boundary.xls", "sheet1");
		
		//Set indices
		int binx=0;		//boundary index
		int saveUnit=1; //save unit index
		int index = 0;
		
		//declare result 
		ArrayList<String[]> venues = new ArrayList<String[]>();
		
		//Iterator
		for(int i = binx;i<boundary.size();i++){
			//URL setting1 - token change
			String[] tokenSet = token.get(i%token.size());
			//URL setting2 - url change
			double[] boundarySet = boundary.get(i);
			double objectId = boundarySet[0];
			double right = boundarySet[1];
			double top = boundarySet[2];
			double left = boundarySet[3];
			double bottom = boundarySet[4];
			
			lat = top;
			lng = left;
			
			double interval_lat = (top-bottom)/40;
			double intercal_lng = (right-left)/40;
			
			while(lng<right){
				while(lat>bottom){
					//URL setting3
					String url = fsURL.makeURL_venues_search(radius, lat, lng, tokenSet);

					//API operation
					venues = fsAPI.venues_search(url);
					lat-=interval_lat;	
				}
				lng+=intercal_lng;
				//System.out.println(index);
				lat = top;
			}
			index++;
			System.out.println(index);
			if(index==saveUnit || binx == boundary.size()-1){
				exConverter.writingExcelFile(venues, 0);
				exConverter.createExcelFile("venues "+(int)objectId);
				System.out.println(" "+(int)objectId);
				venues.clear();
				index=0;
			}
		}
	}
}
