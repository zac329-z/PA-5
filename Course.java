import java.util.ArrayList;
public class Course {
    //Data members
    private String number;
    private String title;
    private int credits;
    private ArrayList<Student> enrolledStudents;
    //Default constructor
    public Course(String num, String t, int c){
        number = num;
        title = t;
        credits = c;
    }
    //Accessor methods
    public String getNumber(){return number;}
    public String getTitle(){return title;}
    public int getCredits(){return credits;}
    //Modifier methods
    public void setNumber(String n){number = n;}
    public void setTitle(String t){title = t;}
    public void setCredits(int c){credits = c;}
    /*Accessor for the Course attributes
     *@return formatted string with the object attributes
     */
    public String toString(){
        return String.format("%s\t%s\t%-10d",number,title,credits);
    }
    /*Modifier method to enroll a student
     *@param s Student to be enrolled
     *@return true if enrolled successfully, false otherwise
     */
    public boolean enroll(Student s){
        if(!enrolledStudents.contains(s)){
            enrolledStudents.add(s);
            return true;
        }
        else{return false;}
    }
    /*Modifier method to drop a student
     *@param s Student to be dropped
     *@return true if dropped successfully, false otherwise
     */
    public boolean drop(Student s){
        if(!enrolledStudents.contains(s)){
            enrolledStudents.remove(s);
            return true;
        }
        else{return false;}
    }
    /*Accessor method to count the number of enrolled students
     *@return number of enrolled students
     */
    public int count(){return enrolledStudents.size();}
    /*Accessor method to get the list of enrolled students
     *@return ArrayList of enrolled students
     */
    public ArrayList<Student> getEnrolledStudents(){return enrolledStudents;}
    //Override equals method
    public boolean equals(Object o){
        if(o instanceof Course&&((Course)o).getNumber().equals(number)){
            return true;
        }
        else{return false;}
    }
}
