package vs.metalarchivesrest;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import vs.metalarchivesrest.entities.Album;
import vs.metalarchivesrest.entities.Band;
import vs.metalarchivesrest.entities.Song;

/**
 *
 * @author vasouv
 */
@Stateless
@Path("resource")
public class MetalArchivesResource {

    private final String API_ENDPOINT = "http://em.wemakesites.net";

    Client client;
    WebTarget target;

    //Properties file that holds the api key
    Properties properties;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target(API_ENDPOINT);

        //Reads the properties file and loads the contents
        InputStream inputStream = this
                .getClass()
                .getClassLoader()
                .getResourceAsStream("key.properties");
        properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(MetalArchivesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    /**
     * Find an album by ID.
     *
     * @param albumid number
     * @return Album in JSON
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("album/{albumID}")
    public Album findAlbum(@PathParam("albumID") String albumid) {

        //Holds the String response
        String job = target
                .path("album")
                .path(albumid)
                .queryParam("api_key", properties.getProperty("api_key"))
                .request()
                .get(String.class);

        //Creates a json object by reading the String response
        JsonObject initialResponse;

        try (JsonReader jsonReader = Json.createReader(new StringReader(job))) {
            initialResponse = jsonReader.readObject();
        }

        //Extracts the data (this is the useful object)
        JsonObject dataObject = initialResponse.getJsonObject("data");

        //Extracts the band object and underlying values
        JsonObject bandObject = dataObject.getJsonObject("band");
        String bandName = bandObject.getString("band_name");
        String bandId = bandObject.getString("id");

        //Extracts the album object and underlying values
        JsonObject albumObject = dataObject.getJsonObject("album");
        String albumTitle = albumObject.getString("title");
        String albumId = albumObject.getString("id");
        String albumYear = albumObject.getString("release date");

        //Extracts the songs list
        JsonArray songsArray = albumObject.getJsonArray("songs");

        //Iterating the songs array and populating a List
        List<Song> tracklist = new ArrayList();
        for (JsonValue jsonValue : songsArray) {
            JsonObject track = (JsonObject) jsonValue;

            int trackNo = songsArray.indexOf(jsonValue) + 1;
            String trackTitle = track.getString("title");
            String trackLength = track.getString("length");

            Song song = new Song();
            song.setNo(trackNo);
            song.setTitle(trackTitle);
            song.setLength(trackLength);

            tracklist.add(song);
        }

        //Creates and returns the found album
        Album album = new Album();

        album.setBandID(bandId);
        album.setBandName(bandName);
        album.setAlbumTitle(albumTitle);
        album.setAlbumID(albumId);
        album.setReleaseDate(albumYear);
        album.setTrackList(tracklist);

        return album;
    }

    /**
     * Fetches the upcoming albums.
     *
     * @return list of albums in JSON
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("upcoming")
    public List<Album> upcomingAlbums() {

        //Holds the String response
        String job = target
                .path("albums/upcoming")
                .queryParam("api_key", properties.getProperty("api_key"))
                .request()
                .get(String.class);

        //Creates a JsonObject by reading the response String
        JsonObject initialResponse;

        try (JsonReader jsonReader = Json.createReader(new StringReader(job))) {
            initialResponse = jsonReader.readObject();
        }

        //Gets the data object
        JsonObject dataObject = initialResponse.getJsonObject("data");

        //Now it's an array of objects
        JsonArray upcomingAlbums = dataObject.getJsonArray("upcoming_albums");

        //Instantiating the list of albums to be returned
        List<Album> returnUpcoming = new ArrayList();

        //Iterating the array and reading the objects
        for (JsonValue jsonValue : upcomingAlbums) {
            JsonObject upcoming = (JsonObject) jsonValue;

            JsonObject bandObject = upcoming.getJsonObject("band");
            String bandID = bandObject.getString("id");
            String bandName = bandObject.getString("name");

            JsonObject albumObject = upcoming.getJsonObject("album");
            String albumID = albumObject.getString("id");
            String albumTitle = albumObject.getString("title");

            String releaseDate = upcoming.getString("release_date");

            Album album = new Album();

            album.setBandID(bandID);
            album.setBandName(bandName);
            album.setAlbumID(albumID);
            album.setAlbumTitle(albumTitle);
            album.setReleaseDate(releaseDate);

            returnUpcoming.add(album);
        }

        return returnUpcoming;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("band/{bandID}")
    public Band findBand(@PathParam("bandID") String bandid) {
        String job = target
                .path("band")
                .path(bandid)
                .queryParam("api_key", properties.getProperty("api_key"))
                .request()
                .get(String.class);
        
        //Creates a json object by reading the String response
        JsonObject initialResponse;

        try (JsonReader jsonReader = Json.createReader(new StringReader(job))) {
            initialResponse = jsonReader.readObject();
        }

        //Extracts the data (this is the useful object)
        JsonObject dataObject = initialResponse.getJsonObject("data");
        
        String bandID = dataObject.getString("id");
        
        String bandName = dataObject.getString("band_name");
        
        String photoURL = dataObject.getString("photo");
        
        String bio = dataObject.getString("bio");
        
        JsonObject details = dataObject.getJsonObject("details");
        String genre = details.getString("genre");
        
        JsonArray jsonDiscography = dataObject.getJsonArray("discography");
        
        List<Album> discography = new ArrayList();
        for (JsonValue jsonValue : jsonDiscography) {
            JsonObject jsonAlbum = (JsonObject) jsonValue;
            
            String albumTitle = jsonAlbum.getString("title");
            String albumID = jsonAlbum.getString("id");
            String albumYearRelease = jsonAlbum.getString("year");
            
            Album album = new Album();
            album.setAlbumID(albumID);
            album.setAlbumTitle(albumTitle);
            album.setReleaseDate(albumYearRelease);

            discography.add(album);
        }
        
        Band band = new Band();
        band.setId(bandID);
        band.setGenre(genre);
        band.setName(bandName);
        band.setPhoto(photoURL);
        band.setBio(bio);
        band.setAlbums(discography);
        
        return band;
    }

}
