public class Student implements Comparable<Student>{
    private String IDnumber;
    private String firstName;
    private String lastName;
    private String teamName;

    Student(String IDnumber, String firstName, String lastName, String teamName) {
        this.IDnumber = IDnumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teamName = teamName;
    }

    public String getIDnumber() {
        return this.IDnumber;
    }

    public void setIDnumber(String IDnumber) {
        this.IDnumber = IDnumber;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return this.IDnumber + " " + this.firstName + " " + this.lastName + " " + this.teamName + "\n";
    }

    @Override
    public int compareTo(Student o) {
        if(this.getLastName().compareTo(o.getLastName()) > 0) {
            return this.getLastName().compareTo(o.getLastName());
        }
        else if(this.getLastName().compareTo(o.getLastName()) < 0) {
            return  this.getLastName().compareTo(o.getLastName());
        }
        else {
            if(this.getFirstName().compareTo(o.getFirstName()) > 0) {
                return this.getFirstName().compareTo(o.getFirstName());
            }
            else if(this.getFirstName().compareTo(o.getFirstName()) < 0) {
                return this.getFirstName().compareTo(o.getFirstName());
            }
            else {
                return this.getFirstName().compareTo(o.getFirstName());
            }
        }
    }
}