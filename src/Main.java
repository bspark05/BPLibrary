import java.util.ArrayList;

import Web.APIs.Foursquare.FoursquareAPI;


public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		//System.out.println("you are using BPLibary!");
		
		FoursquareAPI fsAPI = new FoursquareAPI();
		//FsToken fstoken = new FsToken();
		//FsURL fsURL = new FsURL();
		Test test = new Test();
		
		ArrayList<String[]> fromList = new ArrayList<String[]>();
		
		//Read Token table
		//ArrayList<String[]> token = fstoken.tokenReady("tokenList.xls", "sheet1");
				
		
		String url = "https://api.foursquare.com/v2/venues/40a55d80f964a52020f31ee3/nextvenues?client_id=S3TCARJS00I452G1FSIPZZ0LDOKWX5MBCJ3V1SYPKS2V4Z2I&client_secret=JBJBBXH1RN4D105TFW0O4YEEUAJ2PCKOF5PZEYSBXARLGGZJ&v=20141006";
		//String url = fsURL.makeURL_venues_nextVenues("40a55d80f964a52020f31ee3", tokenSet);
		ArrayList<String[]> venues = fsAPI.venues_NextVenues(url);

		fromList = test.iteration(3, venues);
	}	
	
	private static ArrayList<String[]> checkList(String[] oneVenue, ArrayList<String[]> fromList){
		for(int i =0;i<fromList.size();i++){
			if(fromList.get(i)[0].equals(oneVenue[0])){
				System.out.println("in the list");
				break;
			}
		}
		fromList.add(oneVenue);
		return fromList;
	}
}
