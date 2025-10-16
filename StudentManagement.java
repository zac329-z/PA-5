import java.util.ArrayList;
import java.util.Comparator;
public class StudentManagement {
    //Data members
    private ListRepository<Integer,Student> students;
    private ListRepository<String, Course> courses;
    //Default constructor
    public StudentManagement(){
        students = new ListRepository<Integer,Student>();
        courses = new ListRepository<String,Course>();
    }
    /*
     * Modifier method for students
     * @param s Student to be added
     * @return true if added successfully, false otherwise
     * Time complexity: O(n)
     */
    public boolean addStudent(Student s){
        return students.add(s.getID(),s);
    }
    /*
     * Modifier method for courses
     * @param c Course to be added
     * @return true if added successfully, false otherwise
     * Time complexity: O(n)
     */
    public boolean addCourse(Course c){
        return courses.add(c.getNumber(),c);
    }
    /*
     * Accessor method for students
     * @param id ID of the student to be found
     * @return Student if found, null otherwise
     * Time complexity: O(n)
     */
    public Student findStudent(int id){
        return students.find(id);
    }
    /*
     * Accessor method for courses
     * @param courseNumber Course number of the course to be found
     * @return Course if found, null otherwise
     * Time complexity: O(n)
     */
    public Course findCourse(String courseNumber){
        return courses.find(courseNumber);
    }
    /*
     * Modifier method for students
     * @param id ID of the student to be removed
     * @return Student if removed successfully, null otherwise
     * Time complexity: O(n)
     */
    public Student removeStudent(int id){
        return students.remove(id);
    }
    /*
     * Modifier method for courses
     * @param courseNumber Course number of the course to be removed
     * @return Course if removed successfully, null otherwise
     * Time complexity: O(n)
     */
    public Course removeCourse(String courseNumber){
        return courses.remove(courseNumber);
    }
    /*
     * Enroll a student in a course
     * @param id ID of the student to be enrolled
     * @param courseNumber Course number of the course to enroll in
     * @return 1 if enrolled successfully, 0 if already enrolled, -1 if course not found, -2 if student not found
     * Time complexity: O(n)
     */
    public int enrollStudent(int id, String courseNumber){
        if(students.find(id)==null){
            return -2;
        }
        else if(courses.find(courseNumber)==null){
            return -1;
        }
        else{
            if(courses.find(courseNumber).enroll(students.find(id))){
                return 1;
            }
            else{
                return 0;
            }
        }
    }
    /*
     * Drop a student from a course
     * @param id ID of the student to be dropped
     * @param courseNumber Course number of the course to drop from
     * @return 1 if dropped successfully, 0 if not enrolled, -1 if course not found, -2 if student not found
     * Time complexity: O(n)
     */
    public int dropStudent(int id, String courseNumber){
        if(students.find(id)==null){
            return -2;
        }
        else if(courses.find(courseNumber)==null){
            return -1;
        }
        else{
            if(courses.find(courseNumber).drop(students.find(id))){
                return 1;
            }
            else{
                return 0;
            }
        }
    }
    /*
     * Accessor method for student count
     * @return number of students
     * Time complexity: O(1)
     */
    public int getStudentCount(){
        return students.count();
    }
    /*
     * Accessor method for course count
     * @return number of courses
     * Time complexity: O(1)
     */
    public int getCourseCount(){
        return courses.count();
    }
    /*
     * Get a list of course numbers of courses a student is enrolled in
     * @param id ID of the student
     * @return ArrayList of course numbers, null if student not found
     * Time complexity: O(c*n) where c is number of courses and n is number of students in a course
     */
    public ArrayList<String> getStudentCourses(int id){
        ArrayList<String> sCourses = new ArrayList<>();
        if(students.find(id)==null){
            return null;
        }
        for(Course c:courses.all()){
            for(Student s:c.getEnrolledStudents()){
                if(s.equals(students.find(id))){
                    sCourses.add(c.getNumber());
                }
            }
        }
        return sCourses;
    }
    /*
     * Get a list of students with a specific major
     * @param major String to filter by
     * @return ArrayList of students with the specified major
     * Time complexity: O(n)
     */
    public ArrayList<Student> filterStudentsByMajor(String major){
        ArrayList<Student> majorTakers = new ArrayList<>();
        for(Student s:students.all()){
            if(s.getMajor().equals(major)){
                majorTakers.add(s);
            }
        }
        return majorTakers;
    }
    /*
     * Get a list of the top n students by GPA
     * @param n number of top students to retrieve
     * @return ArrayList of top n students, or all students if n exceeds total count
     * Time complexity: O(n log n)
     */
    public ArrayList<Student> getTopStudent(int n){
        ArrayList<Student> tops = new ArrayList<>();
        class GPAComparator implements Comparator<Student>{
            public int compare(Student s1, Student s2){
                Double gpa1 = s1.getGPA();
                Double gpa2 = s2.getGPA();
                return gpa1.compareTo(gpa2);
            }
        }
        if(n>students.count()){
            for(Student s:students.all()){
                tops.add(s);
            }
        }
        else{
            ArrayList<Student> copyS = students.all();
            copyS.sort(new GPAComparator());
            for(int i=0;i<n;i++){
                tops.add(copyS.get(i));
            }
        }
        return tops;
    }
    /*
     * Get a list of students with a GPA above a certain threshold
     * @param floor GPA threshold
     * @return ArrayList of students with GPA above the threshold
     * Time complexity: O(n log n)
     */
    public ArrayList<Student> getStudentsAboveGPA(double floor){
        class GPAComparator implements Comparator<Student>{
            public int compare(Student s1, Student s2){
                Double gpa1 = s1.getGPA();
                Double gpa2 = s2.getGPA();
                return gpa1.compareTo(gpa2);
            }
        }
        ArrayList<Student> above = new ArrayList<>();
        ArrayList<Student> copyS = students.all();
        copyS.sort(new GPAComparator());
        for(Student s:copyS){
            if(s.getGPA()<floor){
                break;
            }
            above.add(s);
        }
        return above;
    }
    /*
     * Get a list of courses with enrollment below a certain number
     * @param enrolledNumber enrollment threshold
     * @return ArrayList of courses with enrollment below the threshold
     * Time complexity: O(n)
     */
    public ArrayList<Course> getSmallCourses(int enrolledNumber){
        ArrayList<Course> small = new ArrayList<>();
        for(Course c:courses.all()){
            if(c.getEnrolledStudents().size()<enrolledNumber){
                small.add(c);
            }
        }
        return small;
    }
    /*
     * Get a list of courses with enrollment above a certain number
     * @param enrolledNumber enrollment threshold
     * @return ArrayList of courses with enrollment above the threshold
     * Time complexity: O(n)
     */
    public ArrayList<Course> getLargeCourses(int enrolledNumber){
        ArrayList<Course> large = new ArrayList<>();
        for(Course c:courses.all()){
            if(c.getEnrolledStudents().size()>enrolledNumber){
                large.add(c);
            }
        }
        return large;
    }
    /*
     * Get a list of courses sorted by enrollment size in ascending order
     * @return ArrayList of courses sorted by enrollment size
     * Time complexity: O(n log n)
     */
    public ArrayList<Course> getCoursesSortedByEnrollment(){
        class SizeComparator implements Comparator<Course>{
            public int compare(Course c1, Course c2){
                Integer size1 = c1.getEnrolledStudents().size();
                Integer size2 = c2.getEnrolledStudents().size();
                return size1.compareTo(size2);
            }
        }
        ArrayList<Course> byEnrollment = courses.all();
        byEnrollment.sort(new SizeComparator());
        return byEnrollment;
    }

}
