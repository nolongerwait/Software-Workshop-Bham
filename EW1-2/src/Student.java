/**
 *  The original class has several problems:
 *  <pre>
 *  - In totalMark() "for (int i = 0; i <= weights.length; i++)"
 *    i should be less than weights.length.
 *
 *  - In totalMark() "if (totalWeight <= 50)"
 *    should be less than
 *
 *  - In passed() the condiion should be >= 50, not == 50
 * 
 *  - in toString() the call to passed() should be surronded by
 *    a try-catch expression, since passed() may throw an
 *    IllegalArgumentException.
 *  <pre>
 * ****************************************************************
 *  The class contains a method totalMark() that determines the total
 *  mark from a number of component marks that come with a particular
 *  weight each. In case that a student was excused for a particular
 *  assignment (indicated by a -1 entry) the corresponding assignment
 *  is taken out of the computation of the total mark. However, if
 *  more than half of the marks have been waived, a total mark of -1
 *  is returned in order to indicate that the mark cannot be
 *  determined. Correspondingly there is a method passed() that
 *  determines whether a student has passed, that is, has a total mark
 *  of at least 50. (In case that the total mark cannot be determined,
 *  an IllegalArgumentException is thrown.)
 *
 *  @version 2019-11-15
 *  @author Manfred Kerber
 */

public class Student {

    private String registrationNumber;
    private int[] marks;
    //Static constant for the weights of the assignments
    public static final int[] weights = {3,3,3,3,1,1,1,3,2,10,70};

    /**
     *  The constructor initializes the fields
     *  @param registrationNumber The registration number of the student.
     *  @param marks The array contain the 14 component marks.
     */
    public Student(String registrationNumber, int[] marks) {
        this.registrationNumber = registrationNumber;
        this.marks = marks;
    }

    /**
     *  Getter for the registrationNumber.
     *  @return The registration number of the student. 
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     *  Getter for the marks.
     *  @return The marks of the student. 
     */
    public int[] getMarks() {
        return marks;
    }

    /**
     *  Setter for the registrationNumber.
     *  @param registrationNumber The new registration number of the student. 
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     *  Setter for the marks.
     *  @param marks The new marks array of the student. 
     */
    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    /**
     *  Setter for a single mark.
     *  @param assignmentNumber The number of the assignment as a
     *  number between 1 and 14.
     *  @param mark The new mark for the assignment with the given
     *  assignmentNumber.
     */
    public void setAssignmentMark(int assignmentNumber, int mark) {
        this.getMarks()[assignmentNumber-1] = mark;
    }

    /** 
     *  Method to compute the total module mark of a student.
     *  @return The total mark for a student is computed from the 14
     *  component marks that come with a particular weight each
     *  (weighted average) and returned. The total number is rounded
     *  to one digit after the decimal point. In case that a student
     *  was excused for a particular assignment (indicated by a -1
     *  entry) the corresponding assignment is taken out of the
     *  computation of the total mark. However, if more than half of
     *  the marks (measured by weight) have been waived, a total mark
     *  of -1 is returned in order to indicate that the mark cannot be
     *  determined.
     */
    public double totalMark() {
        int marksAchieved = 0;
        int totalWeight = 0;
        /* The method iterates over all component marks and if one is
         * not waived (that is, not -1) then it is multiplied by its
         * weight and added to the total of marks achieved;
         * furthermore the weight is added to the totalWeight in this
         * case.
         */
        for (int i = 0; i < weights.length; i++) {
            if (getMarks()[i] !=-1) {
                marksAchieved += getMarks()[i] * weights[i];
                totalWeight += weights[i];
            }
        }
        /* If the weight of all marks going into the computation is
         * below 50, a -1 is returned to indicate that the total mark
         * cannot be determined.
         */
        if (totalWeight < 50) {
            return -1;
        } else {
            /* The total mark is computed as the marks achieved
             * divided by the total weight. In order to round the
             * number to one decimal after the decimal point the
             * number is first multiplied by 10, then rounded, and
             * finally divided by 10 again.
             */
            return Math.round(marksAchieved * 10.0/totalWeight)/10.0;
        }
    }

    /**
     *  Method to compute whether a student has passed the module.
     *  @return true if the student has a mark of at least 50, false
     *  else.
     *  @exception IllegalArgumentException if the student has not
     *  attempted at least 50% of the assignements measured by their
     *  weights.
     */
    public boolean passed() {
        if (totalMark() == -1) {
            throw new IllegalArgumentException();
        }
        return totalMark() >= 50;
    }

    /** 
     *  toString method for a student object.
     *  @return A human readable String for a student object
     *  containing the registration number, the component mark, the
     *  total mark, and an indication of whether the student has
     *  passed or failed the module.
     */
    public String toString() {
        String result = getRegistrationNumber();
        // Each component mark is added to the result
        for (int el : getMarks()) {
            result += " " + el;
        }
        result += " Total: " + totalMark();
        // Possible exception from method passed caught.
        try {
            if (passed()) {
                return result + " PASSED";
            } else {
                return result + " FAILED";
            }
        }
        catch (IllegalArgumentException e){
            return result +
                " Insufficient info for determining whether passed.";
        }
    }

    /*
     * Main method for initial tests.
     */
    public static void main(String[] args) {
        int[] tonysMarks = {50,50,50,50,50,50,50,50,50,-1,100};
        Student tony = new Student("9111110", tonysMarks);
        System.out.println(tony);

        int[] samsMarks = {50,60,65,60,65,70,55,66,60,73,45};
        Student sam = new Student("9111111", samsMarks);
        System.out.println(sam);
        
        int[] billysMarks = {50,60,-1,60,65,70,55,66,60,73,65};
        Student billy = new Student("9111112", billysMarks);
        System.out.println(billy);

        int[] tobysMarks = {100,100,100,100,100,100,100,100,100,100,25};
        Student toby = new Student("9111113", tobysMarks);
        System.out.println(toby);

        int[] eddysMarks = {50,60,-1,55,66,60,73,65,45,68,-1};
        Student eddy = new Student("9111114", eddysMarks);
        System.out.println(eddy);
    }
}
