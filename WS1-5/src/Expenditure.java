/**
 * This class represents  an  expenditure  with  the  two  field  variables private String description and private int value.
 * @author Zetian Qin zxq876
 * @version 2019-11-22 12:59:41
 */
public class Expenditure {
    private String description;
    private int value;

    /**
     * Standard constructor of the abstract class Expenditure.
     * @param description The description of Expenditure.
     * @param value The value of Expenditure.
     */
    public Expenditure(String description, int value) {
        this.description = description;
        this.value = value;
    }

    /**
     * Getter method to return the description of class Expenditure.
     * @return The description of Expenditure.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter method to return the value of class Expenditure.
     * @return The value of Expenditure.
     */
    public int getValue() {
        return this.value;
    }
}