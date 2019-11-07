import java.util.*;

/**
 * Salaries is a class for the data of salaries of employees in a company.
 * This class has one field object variables.
 * <pre>
 * allSalaries, a ArrayList<double[]> to store the salaries of employees in company.
 * </pre>
 * @author Zetian Qin zxq876
 * @version 2019-10-28 14:59:13
 */
public class Salaries {
    private ArrayList<double[]> allSalaries;

    /**
     * The constructor of the class Salaries, with no parameters, is to create an initially empty ArrayList.
     */
    public Salaries() {
        this.allSalaries = new ArrayList<double[]>();
    }

    /**
     * This method is used to add the salaries of one employee to the field variable allSalaries.
     * @param employeeSalaries
     */
    public void add(double[] employeeSalaries) {
        this.allSalaries.add(employeeSalaries);
    }

    /**
     * This method computes the average salary for an employee.
     * @param employeeSalaries The salaries which the employee earned.
     * @return The average salary for an employee.
     * @throws IllegalArgumentException All the values in parameter employeeSalaries are zero.
     */
    public static double average(double[] employeeSalaries) throws IllegalArgumentException {
        double sum = 0;
        int countEmployee = 0;
        boolean flagNotZero = false;
        for(int i = 0; i < employeeSalaries.length; i++) {
            if(employeeSalaries[i] != 0) {
                sum += employeeSalaries[i];
                countEmployee++;
                flagNotZero = true;
            }
        }
        if(flagNotZero == true) {
            return sum / countEmployee;
        }
        else {
            throw new IllegalArgumentException("All values in the employeeSalaries array are zero.");
        }
    }
    
    /**
     * This method compute the average salaries of the employees in the company from the field variable ArrayList<double[]> allSalaries.
     * @return the average salaries of the employees.
     */
    public ArrayList<Double> averageSalaries() {
        ArrayList<Double> averageSalaries = new ArrayList<Double>();
        Iterator<double[]> itr = this.allSalaries.iterator();
        while(itr.hasNext()) {
            try {
                averageSalaries.add(average(itr.next()));
            }
            catch(IllegalArgumentException exception) {
            	exception.printStackTrace();
                continue;
            }
        }
        return averageSalaries;
    }

    /**
     * This method is an auxiliary method, compute the average salaries of the ohter employees.
     * @param allAverageSalaries The average salaries of all employees, storing in ArrayList<Double>
     * @param index The index of the excluding employee
     * @return the average salaries of the other employees, excluding the index employee
     */
    public double averageOtherSalaries(ArrayList<Double> allAverageSalaries, int index) {
        Iterator<Double> itr = allAverageSalaries.iterator();
        double sum = 0;
        while(itr.hasNext()) {
            sum += itr.next();
        }
        sum -= allAverageSalaries.get(index);
        return sum / (allAverageSalaries.size() - 1);
    }

    /**
     * This method checks whether for each employee with at least one non-zero monthly salary their average salary is not higher than three times the overall average salary of the other employees.
     * @return if it exists average salary of someone is higher than three times the overall average salary of the other employees(true - Exists, fasle - Not exist)
     */
    public boolean not3TimesHigher() {
        boolean flagNot3TimesHigher = true;
        ArrayList<Double> allAverageSalaries = this.averageSalaries();
        for(int i = 0; i < allAverageSalaries.size(); i++) {
            double theOtherAverageSalaries = averageOtherSalaries(allAverageSalaries, i);
            if(allAverageSalaries.get(i) > (3 * theOtherAverageSalaries)) {
                flagNot3TimesHigher = false;
                return flagNot3TimesHigher;
            }
        }
        return flagNot3TimesHigher;
    }
}