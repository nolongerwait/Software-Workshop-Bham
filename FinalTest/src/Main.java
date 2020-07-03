/**
 * Main Class for Items DVDs and Books to test.
 */
public class Main {
    public static void main(String[] args) {
        DVDs cd1 = new DVDs("What I hava done", 39.99, 143);
        System.out.print(cd1);
        DVDs cd2 = new DVDs("Yesterday Once More", 23.99, 80, 121);
        System.out.print(cd2);
        cd1.setDiscountPercentage(60);
        System.out.print(cd1);
        Books book1 = new Books("C++ Primer Plus", 59.88, 95, 768);
        System.out.print(book1);
        Books book2 = new Books("Effecient C++", 36.59, 432);
        System.out.print(book2);
    }
}