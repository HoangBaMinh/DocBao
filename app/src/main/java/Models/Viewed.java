package Models;

public class Viewed {
    public String category;
    public String title;
    public int imageResId;
    public String time;

    public Viewed(String category, String title,int imageResId, String time) {
        this.category = category;
        this.imageResId = imageResId;
        this.title = title;
        this.time = time;
    }
}
