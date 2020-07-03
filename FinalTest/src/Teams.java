import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Because the Teams class controls the information of all teams, then this means that we need a container with a Key-Value structure to store data such as team name-team members. Therefore, we should use the Map container. In addition, because we need to sort and print in alphabetical order, we should be ordered when storing data. TreeMap can be used to support ordered maps. Correspondingly, for the set of Students, we can also use TreeSet to achieve ordering for each team name.
 * 
 * The advantage of using TreeMap and TreeSet is that it uses the Comparable interface to compare according to the key values and then arranges them in alphabetical order.
 * 
 */
public class Teams {
    private TreeMap<String, TreeSet<Student>> memberOfTeams;

    /**
     * Constructor with an array of students.
     * @param students array of Student objects.
     */
    Teams(Student[] students) {
        memberOfTeams = new TreeMap<>();
        for (Student student : students) {
            addStudent(student);
        }
    }

    /**
     * Add new Student to Teams.
     * @param student the student to be added.
     * @return TRUE add successfully, FALSE fail to add.
     */
    public boolean addStudent(Student student) {
        if(this.memberOfTeams.containsKey(student.getTeamName())) {
            return this.memberOfTeams.get(student.getTeamName()).add(student);
        }
        else {
            TreeSet<Student> studentSet = new TreeSet<>();
            studentSet.add(student);
            this.memberOfTeams.put(student.getTeamName(), studentSet);
            return true;
        }
    }

    /**
     * Delete one student from Teams.
     * @param student the student to be deleted
     * @return TRUE delete successfully, FASLE fail to delete.
     */
    public boolean deleteStudent(Student student) {
        if(this.memberOfTeams.get(student.getTeamName()).isEmpty()) {
            return false;
        }
        else {
            return this.memberOfTeams.get(student.getTeamName()).remove(student);
        }
    }

    public void print() {
        if(!this.memberOfTeams.isEmpty()) {
            for (String team : memberOfTeams.keySet()) {
                StringBuilder printString = new StringBuilder(team);
                printString.append(": ");
                if (this.memberOfTeams.get(team).isEmpty()) {
                    printString.append("No Student Members.\n");
                }
                else {
                    for (Student s : this.memberOfTeams.get(team)) {
                        printString.append(s.getFirstName());
                        printString.append(" ");
                        printString.append(s.getLastName());
                        printString.append(", ");
                    }
                    printString.delete(printString.length()-2, printString.length()-1);
                    printString.append("\n");
                }
                System.out.print(printString);
            }
        }
        else {
            System.out.println("No Content!");
        }
    }
}