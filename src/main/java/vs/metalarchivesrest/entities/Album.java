package vs.metalarchivesrest.entities;

import java.util.List;

/**
 *
 * @author vasouv
 */
public class Album {
    
    private String albumTitle;
    private String albumID;
    private String bandName;
    private String bandID;
    private String releaseDate;
    private String coverURL;
    private List<Song> trackList;

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getAlbumID() {
        return albumID;
    }

    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getBandID() {
        return bandID;
    }

    public void setBandID(String bandID) {
        this.bandID = bandID;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Song> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Song> trackList) {
        this.trackList = trackList;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }
    
}
