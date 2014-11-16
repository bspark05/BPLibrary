import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import Web.APIs.Foursquare.FoursquareAPI;
import Web.APIs.Foursquare.FsToken;
import Web.APIs.Foursquare.FsURL;


public class Test {
	public ArrayList<String[]> iteration(int depth, ArrayList<String[]> fromList) throws IOException, JSONException {
		FsToken fstoken = new FsToken();
		FsURL fsURL = new FsURL();
		FoursquareAPI fsAPI = new FoursquareAPI();
		ArrayList<String[]> token = fstoken.tokenReady("tokenList.xls", "sheet1");
		
		int indexFromList = fromList.size();
		System.out.println("initial no. of fromList = "+indexFromList);
		
		int startindex=0;
		
		while(depth>0){
			ArrayList<String[]> tempFromList = new ArrayList<String[]>();
			System.out.println("depth = "+ depth);
			for(int k=0;k<fromList.size();k++){
				tempFromList.add(fromList.get(k));
			}
			System.out.println("size of tempFromList = " +tempFromList.size());
			
			int index = 0;
			
			for(int i=startindex; i<tempFromList.size();i++){
				String[] tokenSet = token.get(i%token.size());
				String url = fsURL.makeURL_venues_nextVenues(tempFromList.get(i)[0], tokenSet);
				ArrayList<String[]> venues1 = fsAPI.venues_NextVenues(url);
				
				for(int j = 0;j<venues1.size();j++){
					ArrayList<String[]> resultfromList = checkList(venues1.get(j), fromList);
					fromList = resultfromList;
				}
				index++;
				System.out.println("# in fromList = "+index);
			}
			startindex = startindex+index;
			
			depth--;
			
			System.out.println("size of fromList in the depth = "+fromList.size());
		}
		System.out.println("final fromList.size() = "+fromList.size());
		return fromList;
	}
	
	//output: renewed fromList, input: 
	public ArrayList<String[]> checkList(String[] oneVenue, ArrayList<String[]> fromList){
		for(int i =0;i<fromList.size();i++){
			if(fromList.get(i)[0].equals(oneVenue[0])){
				System.out.println("in the list");
				return fromList;
			}
		}
		fromList.add(oneVenue);
		System.out.println("added value = "+oneVenue[0]);
		return fromList;
	}
	
}
