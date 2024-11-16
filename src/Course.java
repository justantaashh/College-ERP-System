import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class Course {
    private String courseCode;
    private String title;
    private String professor;
    private int credits;
    private List<String> prerequisites;
    private String timings;
    private String venue;
    public List<String> enrolledStudents;
    public List<Feedback<Object>> feedbackList=new ArrayList<>();
    public int cap;

    public void addStudent(String st) {
        enrolledStudents.add(st);
    }
    public void addFeedback(Feedback<Object> f)
    {
        feedbackList.add(f);
    }

    public Course(String courseCode, String title, String professor, int credits, List<String> prerequisites, String timings, String ven, List<String> es,int c) {
        this.courseCode = courseCode;
        this.title = title;
        this.professor = professor;
        this.credits = credits;
        this.prerequisites = prerequisites;
        this.timings = timings;
        this.venue = ven;
        this.enrolledStudents = es;
        this.cap=c;
    }

    public static void insert_semwise_course(int sem, List<Course> c) {
        CourseRegistrationSystem.semwise_courses.put(sem, c);
    }

    public static void del_semwise_course(int sem, int i) {
        CourseRegistrationSystem.semwise_courses.get(sem).remove(i);
    }

    public static List<Course> get_semwise_course(int sem) {
        return CourseRegistrationSystem.semwise_courses.get(sem);
    }

    public static void print_semcourses(int sem) {
        List<Course> courses = CourseRegistrationSystem.semwise_courses.get(sem);
        if (courses != null) {
            int i = 1;
            for (Course course : courses) {
                System.out.println(i + " " + course.getTitle());
                i++;
            }
        } else {
            System.out.println("No courses found for semester " + sem);
        }
    }


    // Getters and Setters
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public static void printTimetable(Course c) {
        System.out.println("Course: " + c.title);
        System.out.println("Professor: " + c.professor);
        System.out.println("Timings: " + c.timings);
        System.out.println("Venue: " + c.venue);
    }
}
