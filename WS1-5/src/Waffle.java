import java.util.Arrays;

import javafx.application.Application;
import javafx.stage.Stage;

public class Waffle extends Application {
    
    @Override
    public void start(Stage arg0) throws Exception {
        //Data of Expenditure.
        Expenditure[] expenditures = new Expenditure[] {
            new Expenditure("Salaries", 11000),
            new Expenditure("Paper", 2000),
            new Expenditure("Rent", 5000),
            new Expenditure("Most popular books on Java etc.",10000),
            new Expenditure("Heating", 3000),
            new Expenditure("Coffee/Tea", 7000),
            new Expenditure("Biscuits", 8000),
            new Expenditure("Travel", 18000),
            new Expenditure("Electricity", 1000),
            new Expenditure("Pencils", 3000)
        };

        //Sort Array expenditures
        Arrays.sort(expenditures, (Expenditure exp1, Expenditure exp2) -> exp2.getValue() - exp1.getValue());

        
    }
}