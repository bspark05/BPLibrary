import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import Web.APIs.Foursquare.FoursquareAPI;
import Web.APIs.Foursquare.FsToken;
import Web.APIs.Foursquare.FsURL;



public class Main {

	public static void main(String[] args) throws IOException, JSONException {
		// TODO Auto-generated method stub
		double lat = 33.417574;
		double lng = -111.922608;
		double radius = 62 * Math.sqrt(2);
		int binx=0;
		
		FoursquareAPI fsAPI = new FoursquareAPI();
		FsURL fsURL = new FsURL();
		FsToken fstoken = new FsToken();
		
		//Read Token table
		ArrayList<String[]> token = fstoken.tokenReady("tokenList.xls", "sheet1");
		
		//Read Boundary table
		ArrayList<double[]> boundary = fsURL.boundaryReady("boundary.xls", "sheet1");
		System.out.println(boundary.get(3)[1]+" "+boundary.get(0)[2]);
		
		//Iterator
		for(int i = binx;i<boundary.size();i++){
			//URL setting1 - token change
			String[] tokenSet = token.get(i%token.size());
			//URL setting2 - url change
			
			//URL setting3
			String url = fsURL.makeURL_venues_search(radius, lat, lng, tokenSet);
			System.out.println(url);
			
			//API operation
			ArrayList<String[]> venues = fsAPI.venues_search(url);
			System.out.println(venues.get(0)[1]);
		}
	}

}
