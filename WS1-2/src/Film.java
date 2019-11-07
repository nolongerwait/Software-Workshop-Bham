/** Film is a class for a very simple infromation of films.
 * This class distinguish four field object varibales.
 * <pre>
 *  title, a String to the title of the film
 *  releaseDate, a Date to the release date of the film
 *  length, an int to the length of the film
 * </pre>
 * @author Zetian Qin zxq876
 * @version 2019-10-11 13:37:15
 */

public class Film{
    private String title;
    private Date releaseDate;
    private int length;
    
    /**
     * Film constructor creates a film information from three parts:
     * title, release date and the length of the film, which are a String, a Date and an int, respectively.
     * @param title The title of the film as String
     * @param releaseDate The date when the film releases as Date
     * @param length The length of the film as int
     */
    public Film(String title, Date releaseDate, int length){
        this.title = title;
        this.releaseDate = releaseDate;
        this.length = length;
    }

    /**
     * get the title from a Film
     * @return the title of the film
     */
    public String getTitle(){
        return title;
    }

    /**
     * get the releaseDate from a Film
     * @return the date when the film releases
     */
    public Date getReleaseDate(){
        return releaseDate;
    }

    /**
     * get the length from a Film
     * @return the length of the film
     */
    public int getLength(){
        return length;
    }

    /**
     * set the releaseDate of a Film
     * @param releaseDate The date when the film releases as Date class
     */
    public void setReleaseDate(Date releaseDate){
        this.releaseDate = releaseDate;
    }

    /**
     * toString defines how to print the Film
     * @return the print type of the film
     */
    public String toString(){
        return "Title: " + title + "\r\nThe release date: " + releaseDate.toString() + "\r\nThe length: " + length + " min";
    }

    /**
     * main method to run this Java programme
     */
    public static void main(String[] args){
        Film adAstra = new Film("Ad Astra", new Date(18, "September", 2019), 123);
        System.out.println(adAstra);
    }
}