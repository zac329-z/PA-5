public class Student {
    //Data members
    private int id;
    private String name;
    private String major;
    private double gpa;
    //Default constructor
    public Student(int i, String n, String m, double g){
        id = i;
        name = n;
        major = m;
        gpa = g;
    }
    //Accessor methods
    public int getID(){return id;}
    public String getName(){return name;}
    public String getMajor(){return major;}
    public double getGPA(){return gpa;}
    //Modifier methods
    public void setID(int i){id=i;}
    public void setName(String n){name=n;}
    public void setMajor(String m){major=m;}
    public void setGPA(double g){gpa=g;};
    /*Accessor for the Student attributes
     *@return formatted string with the object attributes
     */
    public String toString(){
        return String.format("%-10d\t%s\t%s\t%-10d",id,name,major,gpa);
    }
    //Override equals method
    public boolean equals(Object o){
         if(o instanceof Student&&id==((Student)o).getID()){
            return true;
        }
        else{return false;}
    }
}
