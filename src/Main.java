import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import Database.Neo4j.Neo4jRESTAPI;
import Web.APIs.Foursquare.FoursquareAPI;
import Web.APIs.Foursquare.FsToken;
import Web.APIs.Foursquare.FsURL;


public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		//System.out.println("you are using BPLibary!");
		
		FoursquareAPI fsAPI = new FoursquareAPI();
		FsToken fstoken = new FsToken();
		FsURL fsURL = new FsURL();
		
		ArrayList<String[]> fromList = new ArrayList<String[]>();
		
		//declare result 
		ArrayList<String[]> venues = new ArrayList<String[]>();
		
		//Read Token table
		//ArrayList<String[]> token = fstoken.tokenReady("tokenList.xls", "sheet1");
				
		
		String url = "https://api.foursquare.com/v2/venues/40a55d80f964a52020f31ee3/nextvenues?client_id=S3TCARJS00I452G1FSIPZZ0LDOKWX5MBCJ3V1SYPKS2V4Z2I&client_secret=JBJBBXH1RN4D105TFW0O4YEEUAJ2PCKOF5PZEYSBXARLGGZJ&v=20141006";
		//String url = fsURL.makeURL_venues_nextVenues("40a55d80f964a52020f31ee3", tokenSet);
		venues = fsAPI.venues_NextVenues(url);

		Neo4jRESTAPI neo4j = new Neo4jRESTAPI();
		int status = neo4j.getServerStatus();
		System.out.println("neo4j server status : " + status);


		

	}	
	
	//output: fromList, input: one Id among result
	private static void listNeo4j(ArrayList<String[]> venueList, String initNode){
		//create nodes & relations
		Neo4jRESTAPI neo4j = new Neo4jRESTAPI();
		for(int i=0;i<venueList.size();i++){
			String node = neo4j.createNode();
			neo4j.addProperty(node, "id", venueList.get(i)[0]);
			
			String relationAttributes = "{ \"next\" : \1\"}";
		    String relationShipURI = neo4j.addRelationship(initNode,
		                                                    node,
		                                                   "Next",
		                                                    relationAttributes);
			        
		    neo4j.addPropertyToRelation(relationShipURI, "Next", "1");
		}
		
	}
}
