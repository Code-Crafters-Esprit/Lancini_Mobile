package codecrafters.lancini.service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;

public class NewsService extends Observable {

    private static final String API_KEY = "e97a484d80d2443f8f52fb394b308b13";
    private static final String API_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + API_KEY;

  public void fetchNewsArticles() {
    ConnectionRequest request = new ConnectionRequest() {
        @Override
        protected void readResponse(InputStream input) throws IOException {
            JSONParser parser = new JSONParser();
            Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));

            // Extract articles from the response
            if (response.containsKey("articles")) {
                ArrayList<Map<String, Object>> articles = (ArrayList<Map<String, Object>>) response.get("articles");
                for (Map<String, Object> article : articles) {
                    String title = (String) article.get("title");
                    String description = (String) article.get("description");

                    // Notify observers with the article data
                    NewsArticle newsArticle = new NewsArticle(title, description);
                    setChanged();
                    notifyObservers(newsArticle);
                }
            } else {
                // Handle error case where articles are not present in the response
                Dialog.show("Error", "Failed to retrieve news articles", "OK", null);
            }
        }

        @Override
        protected void handleException(Exception err) {
            // Handle API request error
            Dialog.show("Error", "Failed to retrieve news articles", "OK", null);
        }
    };

    request.setUrl(API_URL);
    request.setPost(false);
    request.setHttpMethod("GET");

    request.setDuplicateSupported(true);
    request.setFailSilently(true);
    request.setReadResponseForErrors(true);

    // Add the request to the queue to send it
    NetworkManager.getInstance().addToQueue(request);
}
}
