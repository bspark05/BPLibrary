import java.util.ArrayList;

import org.neo4j.graphdb.RelationshipType;

import Database.Neo4j.Neo4jRESTAPI;
import Web.APIs.Foursquare.FoursquareAPI;
import Web.APIs.Foursquare.FsToken;
import Web.APIs.Foursquare.FsURL;


public class Main {
	private static enum RelTypes implements RelationshipType {
		nextVenue, Know
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		//System.out.println("you are using BPLibary!");
		
		FoursquareAPI fsAPI = new FoursquareAPI();
		FsURL fsURL = new FsURL();
		FsToken fstoken = new FsToken();
		Test test = new Test();
		Neo4jRESTAPI neo4j = new Neo4jRESTAPI();
		
		ArrayList<String[]> fromList = new ArrayList<String[]>();				
		
		String url = "https://api.foursquare.com/v2/venues/40a55d80f964a52020f31ee3/nextvenues?client_id=S3TCARJS00I452G1FSIPZZ0LDOKWX5MBCJ3V1SYPKS2V4Z2I&client_secret=JBJBBXH1RN4D105TFW0O4YEEUAJ2PCKOF5PZEYSBXARLGGZJ&v=20141006";
		//String url = fsURL.makeURL_venues_nextVenues("40a55d80f964a52020f31ee3", tokenSet);
		ArrayList<String[]> venues = fsAPI.venues_NextVenues(url);

		fromList = test.iteration(4, venues);
		
		//Read Token table5
		ArrayList<String[]> token = fstoken.tokenReady("tokenList.xls", "sheet1");
		
		for(int i = 0;i<fromList.size();i++){
			String[] tokenSet = token.get(i%token.size());
			String url1 = fsURL.makeURL_venues_nextVenues(fromList.get(i)[0], tokenSet);
			ArrayList<String[]> venues1 = fsAPI.venues_NextVenues(url1);
			String nodeAttribute1 = test.setAttribute(fromList.get(i));
			String startNode = neo4j.createUniqueNode("Id", fromList.get(i)[0], nodeAttribute1);
			for(int j=0;j<venues1.size();j++){
				String nodeAttribute2 = test.setAttribute(venues1.get(j)); 
				String endNode = neo4j.createUniqueNode("Id", venues1.get(j)[0], nodeAttribute2);
				String relationId = fromList.get(i)[0]+"_to_"+venues1.get(j)[0];
				String relation = neo4j.addUniqueRelationship("Id", relationId, startNode, endNode, null);
			}
			
			
		}
		
		
	}	
	
}
