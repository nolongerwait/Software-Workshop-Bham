/**
 * The class is used to represent music titles in form of a title, an artist, and a price, of types String, String, and int,respectively.
 * @author Zetian Qin zxq876
 * @version 2019-11-09 23:19:38
 */

public class MusicTitle implements MusicTitleInterface {
    private String title;
    private String artist;
    private int price;
    
    /**
     * Standard constructor for the class MusicTitle
     * @param title The title of the music title.
     * @param artist The artist of the music title.
     * @param price
     */
    public MusicTitle(String title, String artist, int price) {
        this.title = title;
        this.artist = artist;
        this.price = price;
    }

    /**
     * Standard getter for the title
     * @return The title of the music title.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Standard getter for the artist
     * @return The artist of the music title.
     */
    public String getArtist() {
        return this.artist;
    }

    /**
     * Standard getter for the price
     * @return The price of the music title.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Standard toString method to print the object
     * @return The object in a human readable form.
     */
    public String toString() {
        return "MusicTitle{" + "title:" + this.title + ", artist:" + this.artist + ", price:" + this.price + "}";
    }
}