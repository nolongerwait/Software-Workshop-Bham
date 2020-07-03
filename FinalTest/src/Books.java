public class Books extends Items{
    // Field variable to represent the number of pages of the book.
    private int numberOfPages;

    Books(String name, double price, int numberOfPages) {
        super(name, price);
        this.numberOfPages = numberOfPages;
    }

    Books(String name, double price, double discountPercentage, int numberOfPages) {
        super(name, price, discountPercentage);
        this.numberOfPages = numberOfPages;
    }

    public int getNumberOfPages() {
        return this.numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return super.toString() + "Number of pages: " + this.numberOfPages + "\n";
    }
}