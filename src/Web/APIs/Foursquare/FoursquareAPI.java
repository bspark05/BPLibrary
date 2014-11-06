package Web.APIs.Foursquare;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import Web.Parser.JsonReader;

public class FoursquareAPI {
	FsURL fsurl = new FsURL();

	public ArrayList<String[]> venues_search(double radius, double latsql, double lngsql) throws IOException, JSONException{
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		
		JSONObject json = JsonReader.readJsonFromUrl(fsurl.getURL());
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
