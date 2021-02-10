import org.javalite.http.Http;
import org.javalite.http.Put;

import java.io.BufferedReader;
import java.io.FileReader;

public class RESTAPIResultCollector implements ResultCollector {
	protected String theURL;
	protected String fileName;
	protected String theAuth;

	public RESTAPIResultCollector (String authFileName, String baseURL) {
		String auth;

		fileName = authFileName;
		theURL = baseURL;
        try {
            BufferedReader br = new BufferedReader(new FileReader(authFileName));
            auth = br.readLine();
        }
        catch (Exception e) {
            System.out.println("Didn't find auth.txt.  Can't submit results.");
            return;
        }
	}
	public boolean submitGameResult (int gameId, int score, int lineCount) {
        String auth;
        try {
            BufferedReader br = new BufferedReader(new FileReader("auth.txt"));
            auth = br.readLine();
        }
        catch (Exception e) {
            System.out.println("Didn't find auth.txt.  Can't submit results.");
			return false;
        }
        String actualURL = theURL + "/" + gameId;
        // This should probably use some json helper rather than building a string...
        String json = String.format("{\"id\":%d,\"score\":%d,\"lines\":%d}", gameId, score, lineCount);

        try {
            Put put = Http.put(actualURL,json).header("X-SENG275-Authentication", auth).header("Accept", "application/json").header("Content-Type", "application/json");
            if (put.responseCode() != 200 ) {
                System.out.println(actualURL + json + put.responseCode());
				return false;
            } else {
                System.out.println("Submitted result:" + put.text());
				return true;
            }
        } catch (Exception e) {
			return false;
        }
    }
}
