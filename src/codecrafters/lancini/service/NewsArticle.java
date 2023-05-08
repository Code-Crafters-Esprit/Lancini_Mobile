package codecrafters.lancini.service;

public class NewsArticle {

    private String title;
    private String description;

    public NewsArticle(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
