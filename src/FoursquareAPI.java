import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;



public class FoursquareAPI {
	ArrayList<String[]> arrayList = new ArrayList<String[]>();
	private double radius;
	private String client_id;
	private String client_secret;
	private String tokenDate;
	
	
	public void setURL(double radius, ArrayList<String[]> tokenList, int index) {
		String[] tokenPair = tokenIterator(tokenList, index);
		
		this.radius = radius;
		this.client_id = tokenPair[0];
		this.client_secret = tokenPair[1];
		this.tokenDate = tokenPair[2];
	}
	
	public String[] tokenIterator(ArrayList<String[]> tokenList, int index){
		String[] tokenPair = {"", "", ""};
		switch(index%tokenList.size()){
		case 0:
			tokenPair = tokenList.get(0);
			break;
		case 1:
			tokenPair = tokenList.get(1);
			break;
		case 2:
			tokenPair = tokenList.get(2);
			break;
		case 3:
			tokenPair = tokenList.get(3);
			break;
		case 4:
			tokenPair = tokenList.get(4);
			break;
		case 5:
			tokenPair = tokenList.get(5);
			break;
		case 6:
			tokenPair = tokenList.get(6);
			break;
		case 7:
			tokenPair = tokenList.get(7);
			break;
		case 8:
			tokenPair = tokenList.get(8);
			break;
		case 9:
			tokenPair = tokenList.get(9);
			break;
		}
		return tokenPair;
	}

	public String makeURL(double latsql, double lngsql){
		String url = "https://api.foursquare.com/v2/venues/search?ll="+latsql+","+lngsql+"&radius="+radius+"&limit=50&&client_id="+client_id+"&client_secret="+client_secret+"&v="+tokenDate;
		return url;
	}
	
	public ArrayList<String[]> parsingFS(String url) throws IOException, JSONException{
		JSONObject json = JsonReader.readJsonFromUrl(url);
		//JSONObject json = readJsonFromUrl("https://api.foursquare.com/v2/venues/search?ll=40.7,-74&&client_id=CLIENTID&client_secret=CLIENTSECRET&v=YYYYMMDD");
		//System.out.println(json.toString());
		JSONObject json1 = (JSONObject) new JSONTokener(json.toString()).nextValue();
        JSONObject json2 = json1.getJSONObject("response");
        
        JSONArray arr1 = json2.getJSONArray("venues");
        for(int i=0;i<arr1.length();i++){
        	String id = arr1.getJSONObject(i).getString("id");
        	String name = arr1.getJSONObject(i).getString("name");
        	//String phone = arr1.getJSONObject(i).getJSONObject("contact").getString("phone");
        	double lat = arr1.getJSONObject(i).getJSONObject("location").getDouble("lat");
        	double lng = arr1.getJSONObject(i).getJSONObject("location").getDouble("lng");
        	
        	int checkinsCount = arr1.getJSONObject(i).getJSONObject("stats").getInt("checkinsCount");
        	int usersCount = arr1.getJSONObject(i).getJSONObject("stats").getInt("usersCount");
        	
        	JSONArray arr2 = arr1.getJSONObject(i).getJSONArray("categories");
        	String cateName = null;
        	if(arr2.isNull(0)){
        		cateName = "null";
        	}else{
        		cateName = arr2.getJSONObject(0).getString("name");
        	}
        		
        	String[] array = {id, name, cateName, Double.toString(lat), Double.toString(lng), Integer.toString(checkinsCount), Integer.toString(usersCount)};
        	//String[] array = {name, Double.toString(lat), Double.toString(lng), Integer.toString(checkinsCount), Integer.toString(usersCount)};
        	arrayList.add(array);
        }
		return arrayList;
	}
}
