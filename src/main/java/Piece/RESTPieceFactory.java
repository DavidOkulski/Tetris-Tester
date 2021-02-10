package Piece;

import java.util.Random;
import org.javalite.http.Http;
import org.javalite.http.Post;
import org.json.JSONObject;

public class RESTPieceFactory extends RandomPieceFactory {
    private int theGameId;

    private RESTPieceFactory() {
    }

    public RESTPieceFactory (String url) {
        int seed = 1234;
        theGameId = 0;

        try {
            Post post = Http.post(url);
            if (post.responseCode() == 200) {
                JSONObject obj = new JSONObject(post.text());
                seed = obj.getInt("rngseed");
                theGameId =  obj.getInt("id");
            }
            else {
                System.out.println("Unexpected repsonse code:"+ post.responseCode()  + post.responseMessage());
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("RESTPieceFactory: gameid: " + theGameId + " rngseed: " + seed);
        theRand = new Random(seed);
    }

    public int gameId() {
        return theGameId;
    }
}
