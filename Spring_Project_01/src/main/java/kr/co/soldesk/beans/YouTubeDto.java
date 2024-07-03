package kr.co.soldesk.beans;

public class YouTubeDto {
    private String videoId;
    private String title; 
    private String thumbnailUrl;
    private String highThumbnailUrl;

    // Getters and Setters
    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {	
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    
    public String getHighThumbnailUrl() {
        return highThumbnailUrl;
    }

    public void setHighThumbnailUrl(String highThumbnailUrl) {
        this.highThumbnailUrl = highThumbnailUrl;
    }
}
