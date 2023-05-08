package codecrafters.lancini.service;

public class NewsArticle {

    private String title;
    private String description;
    private String urlToImage;

    public NewsArticle(String title, String description, String urlToImage) {
        this.title = title;
        this.description = description;
         this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    public String getUrlToImage(){
     return urlToImage;
    }
    
}
