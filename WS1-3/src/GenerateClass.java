import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The class GenerateClass could generate from the field variables a constructor, the setters, and the getters automatically just as Eclipse does
 * This class has three field object variables.
 * <pre>
 * className, the name of one class as String
 * variableNames, the names of the variables store in String array;
 * variableTypes, the types of the variables store in String array;
 * </pre>
 * @author Zetian Qin zxq876
 * @version 2019-10-31 09:42:42
 */
public class GenerateClass {
    private String className;
    private String[] variableNames;
    private String[] variableTypes;

    /**
     * The constructor of the class GenerateClass
     * @param className the name of one class
     * @param variableNames the names of variables store in String array
     * @param variableTypes the types of variables store in String array
     */
    public GenerateClass(String className, String[] variableNames, String[] variableTypes) {
        this.className = className;
        this.variableNames = variableNames;
        this.variableTypes = variableTypes;
    }

    /**
     * This method returns a String with a declaration of the corre- sponding field variables.
     * @return A declaration of the corresponding field variables as String
     */
    public String makeFields() {
        String makeField = "";
        //Create every declaration statement of the field variable.
        int numOfFieldVariable = variableNames.length;
        for(int i = 0; i < numOfFieldVariable; i++){
            makeField = makeField + "  private " + variableTypes[i] + " " + variableNames[i] + ";\n";
        }
        makeField += "\n";
        return makeField;
    }

    /**
     * This method returns a String with a corresponding constructor.
     * @return A  corresponding constructor as String
     */
    public String makeConstructor() {
        String makeConstructor = "";
        //Create the Constructor method signature
        makeConstructor = makeConstructor + "  public "  + this.className + "(";
        int numOfFieldVariable = variableNames.length;
        for(int i = 0; i < numOfFieldVariable; i++){
            makeConstructor = makeConstructor + variableTypes[i] + " " + variableNames[i];
            if(i != numOfFieldVariable - 1) {
                makeConstructor += ", ";
            }
        }
        makeConstructor += ")";
        //Create the content of the Constructor method
        if(numOfFieldVariable != 0) {
            makeConstructor += "{\n";
            for(int i = 0; i < numOfFieldVariable; i++) {
                makeConstructor = makeConstructor + "    this." + variableNames[i] + " = " + variableNames[i] + ";\n";
            }
            makeConstructor += "  }\n";
            return makeConstructor;
        }
        else {
            makeConstructor += "{}\n";
            return makeConstructor;
        }
    }

    /**
     * This method returns a String with all getters in the order given by variableNames/variableTypes.
     * @return All getter of the field variables as String.
     */
    public String makeGetters() {
        String makeGetter = "";
        int numOfFieldVariable = variableNames.length;
        for(int i = 0; i < numOfFieldVariable; i++) {
            makeGetter = makeGetter + "  public " + variableTypes[i] + " get";
            char[] tmp = variableNames[i].toCharArray();
            tmp[0] -= 32;
            makeGetter = makeGetter + String.valueOf(tmp) + "()" + "{\n    return " + variableNames[i] + ";\n  }\n";
        }
        return makeGetter;
    }

    /**
     * This method returns a String with all setters in the order given by variableNames/variableTypes.
     * @return All setter of the field variables as String.
     */
    public String makeSetters() {
        String makeSetter = "";
        int numOfFieldVariable = variableNames.length;
        for(int i = 0; i < numOfFieldVariable; i++) {
            makeSetter = makeSetter + "  public void set";
            char[] tmp = variableNames[i].toCharArray();
            tmp[0] -= 32;
            makeSetter = makeSetter + String.valueOf(tmp) + "(" + variableTypes[i] + " " + variableNames[i] + ")" + "{\n    this." + variableNames[i] + " = " + variableNames[i] + ";\n  }\n";
        }
        return makeSetter;
    }
    
    /**
     * This method writes a rudimentary class, starting with the classname and the opening {, the field variables, the constructor, the getters, the setters, and the closing } to a file with the name given by the classname extended by .java
     * @throws IOException
     */
    public void writeFile() throws IOException {
        File checkFile = new File(this.className + ".java");
        FileWriter writer = null;
        try {
            if(!checkFile.exists()) {
                checkFile.createNewFile();
            }
            writer = new FileWriter(checkFile, true);
            writer.append("public class " + this.className + " {\n" + this.makeFields() + this.makeConstructor() + this.makeGetters() + this.makeSetters() + "}\n");
            writer.flush();
        }
        catch(IOException exception) {
            exception.printStackTrace();
        }
        finally {
            if (null != writer) {
                writer.close();
            }
        }
    }

    public static void main(String[] args) {
        String className = "Person";
        String[] variableNames = {"name", "dob"};
        String[] variableTypes = {"String", "Date"};
        GenerateClass test = new GenerateClass(className, variableNames, variableTypes);
        System.out.printf(test.makeFields());
        System.out.printf(test.makeConstructor());
        System.out.printf(test.makeGetters());
        System.out.printf(test.makeSetters());
        try{
            test.writeFile();
        }
        catch(IOException exception) {
            exception.printStackTrace();
        }
    }
}