public class Song {
    private String title;
    private double duration;
    private boolean isFavorite;

    public Song(String title, double duration) {
        this.title = title;
        this.duration = duration;
        this.isFavorite = false;
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void toggleFavorite() {
        this.isFavorite = !this.isFavorite;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", duration=" + duration +
                ", isFavorite=" + (isFavorite ? "Yes" : "No") +
                '}';
    }
}
