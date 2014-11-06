package Web.APIs.Foursquare;

public class FsURL {
	private String URL;
	FsToken fstoken = new FsToken();
	
	public String getURL() {
		return URL;
	}

	public void makeURL_venues_search(double radius, double latsql, double lngsql){
		String url = "https://api.foursquare.com/v2/venues/search?ll="+latsql+","+lngsql+"&radius="+radius+"&limit=50&&client_id="+fstoken.getClient_id()+"&client_secret="+fstoken.getClient_secret()+"&v="+fstoken.getTokenDate();
		URL = url;
	}
}
