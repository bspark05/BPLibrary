import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import Web.APIs.Foursquare.FoursquareAPI;
import Web.APIs.Foursquare.FsToken;
import Web.APIs.Foursquare.FsURL;


public class Test {
	public ArrayList<String[]> iteration(int depth, ArrayList<String[]> venues, ArrayList<String[]> fromList) throws IOException, JSONException {
		FsToken fstoken = new FsToken();
		FsURL fsURL = new FsURL();
		FoursquareAPI fsAPI = new FoursquareAPI();
		ArrayList<String[]> token = fstoken.tokenReady("tokenList.xls", "sheet1");
		
		if(depth==0){
			return fromList;
		}else{
			for(int i=0; i<venues.size();i++){
				String[] tokenSet = token.get(i%token.size());
				String url = fsURL.makeURL_venues_nextVenues(venues.get(i)[0], tokenSet);
				ArrayList<String[]> venues1 = fsAPI.venues_NextVenues(url);
				ArrayList<String[]> fromList1 = checkList(venues.get(i),fromList);
				
				iteration(depth-1, venues1, fromList1);
			}
			return fromList;
		}
	}
	
	//output: renewed fromList, input: 
	public ArrayList<String[]> checkList(String[] oneVenue, ArrayList<String[]> fromList){
		for(int i = 0; i<fromList.size();i++){
			if(!fromList.get(i)[0].equals(oneVenue[0])){
				System.out.println("no");
				fromList.add(oneVenue);
			}else{
				System.out.println("yes");
			}
		}
		for(int j=0;j<fromList.size();j++){
			System.out.println(fromList.get(j)[0]);
			}
		return fromList;
	}
}
