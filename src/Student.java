import java.util.*;
import java.util.ArrayList;

class Student extends User
{

    List<Course> enrolledCourses;
    HashMap<String,Integer> student_grade=new HashMap<>();
    static List<String> complaint = new ArrayList<>();
    List<String> status = new ArrayList<>();
    boolean log_out=false;

    public   void register_complaint(String s)
    {
        complaint.add(s);
        status.add("Pending");
    }
    public  void change_complaint_status(int i,String status)
    {
        this.status.set(i,status);
    }
    public  void print_complaint_status()
    {
        for(int i=0;i<complaint.size();i++)
        {
            System.out.println(i+1+" "+complaint.get(i)+" "+status.get(i));
        }
    }

    public  void setPass(String pass)
    {
        this.password=pass;
    }

    public HashMap<String,Integer> getStudent_grade()
    {
        return student_grade;
    }
    public Student(String email, String password)
    {
        super(email, password);
        this.enrolledCourses = new ArrayList<>();
    }
    public String getEmail()
    {
        return this.email;
    }
    public String getPass()
    {
        return this.password;
    }

    public void update_grade(String c,int x)
    {
        this.student_grade.put(c,x);
    }
    public void enrollCourse(Course course) throws CourseFullException
    {
        if(course.enrolledStudents.size()> course.cap)
        {
            throw new CourseFullException("Course capacity reached. Cannot enroll!");
        }
//            List<String> pre = course.getPrerequisites();
//            System.out.println(pre.get(0));
//            for (int i = 0; i < pre.size(); i++) {
//                if (student_grade.get(pre.get(i)) == 0 || student_grade.get(pre.get(i)) == null) {
//                    System.out.println("Prerequisite not fulfilled");
//                    return;
//                }
//            }
            enrolledCourses.add(course);
            System.out.println("Successfully Enrolled in course: " + course.getTitle());

    }
    public void printSGPAandCGPA() {
        for (HashMap.Entry<String, Integer> entry : student_grade.entrySet()) {
            System.out.println("Course: " + entry.getKey() + ", Grade: " + entry.getValue());
            }
        int totalCourses = student_grade.size();
        int totalSemesters = totalCourses / 5;  // Assuming each semester has exactly 5 courses
        double totalPoints = 0;
        int courseCount = 0;

        for (Integer grade : student_grade.values()) {
            totalPoints += grade;
            courseCount++;
        }

        double cgpa = (courseCount > 0) ? totalPoints / (courseCount*10) : 0;
        System.out.printf("CGPA is: %.2f\n", cgpa);

        if (totalSemesters > 0) {
            for (int i = 0; i < totalSemesters; i++) {
                double semesterPoints = 0;
                int start = i * 5;  // starting index for the semester
                int end = start + 5;  // ending index for the semester

                int currentCourse = 0;
                for (Integer grade : student_grade.values()) {
                    if (currentCourse >= start && currentCourse < end) {
                        semesterPoints += grade;
                    }
                    currentCourse++;
                }

                double sgpa = semesterPoints / 50;  // Each semester has 5 courses
                System.out.printf("SGPA for Semester %d: %.2f\n", i + 1, sgpa);
            }
        } else {
            System.out.println("Not enough data to calculate SGPA.");
        }
    }

    @Override
    public boolean verifyPassword(String password) {
        return false;
    }

    public void displayMenu() {
        System.out.println("1. View enrolled courses");
        System.out.println("2. Enroll in a course");
        System.out.println("3. View Time Table");
        System.out.println("4. View Transcript");
        System.out.println("5. Drop A course");
        System.out.println("6. Submit a Complaint");
        System.out.println("7. Submit a Feedback");
        System.out.println("8. Logout");
        sc=new Scanner(System.in);
        int choice=sc.nextInt();

        if(choice==1)
        {
            int i=1;
            for (Course enrolledCourse : enrolledCourses)
            {
                System.out.println(i + " " + enrolledCourse.getTitle());
                i++;
            }
            System.out.println("1.Back");
            System.out.println("2.Logout");
            int choice_inner=sc.nextInt();
            if(choice_inner==1)
                displayMenu();
            else if(choice_inner ==2)
                return;
        }
        else if(choice==2)
        {
            int sem;
            System.out.println("Semester: ");
            sem=sc.nextInt();
            sc.nextLine();
            System.out.println("Available Courses are: ");
            Course.print_semcourses(sem);

            while(enrolledCourses.size()!=5*sem)
            {
                System.out.println("Enter Course to Enroll in: ");
                int course_choice=sc.nextInt();
                Course c = Course.get_semwise_course(sem).get(course_choice-1);
                try {
                    enrollCourse(c);
                } catch (CourseFullException e) {
                    System.out.println(e.getMessage());
                }
                c.addStudent(email);
            }
            System.out.println("1.Back");
            System.out.println("2.Logout");
            int choice_inner=sc.nextInt();
            if(choice_inner==1)
                displayMenu();
            else if(choice_inner ==2)
                return;
        }
        else if(choice==3)
        {
            int sem;
            System.out.println("Semester: ");
            sem=sc.nextInt();
            for (int i=(sem-1)*5;i<sem*5;i++)
            {
                Course.printTimetable(enrolledCourses.get(i));
            }
            System.out.println("1.Back");
            System.out.println("2.Logout");
            int choice_inner=sc.nextInt();
            if(choice_inner==1)
                displayMenu();
            else if(choice_inner ==2)
                return;
        }
        else if(choice==4)
        {
            printSGPAandCGPA();
            System.out.println("1.Back");
            System.out.println("2.Logout");
            int choice_inner=sc.nextInt();
            if(choice_inner==1)
                displayMenu();
            else if(choice_inner ==2)
                return;
        }
        else if(choice==5)
        {
            System.out.println("Which Course Do you want to Drop: ");
            int i=1;
            for(Course enrolledCourse: enrolledCourses)
            {
                System.out.println(i+" "+enrolledCourse.getTitle());
                i++;
            }
            int ch=sc.nextInt();
            enrolledCourses.remove(ch-1);
            System.out.println("1.Back");
            System.out.println("2.Logout");
            int choice_inner=sc.nextInt();
            if(choice_inner==1)
                displayMenu();
            else if(choice_inner ==2)
                return;
        }
        else if (choice == 6)
        {
            System.out.println("1.Submit a new Complaint ");
            System.out.println("2.View Complaint Status");
            int ch=sc.nextInt();
            sc.nextLine();
            if(ch==1)
            {
                System.out.println("Enter Complaint: ");
                String comp=sc.nextLine();
                register_complaint(comp);
            }
            else if(ch==2)
            {
                if(complaint.isEmpty())
                    System.out.println("No Complaints to show");
                else
                    print_complaint_status();
            }
            System.out.println("1.Back");
            System.out.println("2.Logout");
            int choice_inner=sc.nextInt();
            if(choice_inner==1)
                displayMenu();
            else if(choice_inner ==2)
                return;
        }
        else if(choice==7) {
            System.out.println("Enter Course Title");
            sc.nextLine();
            String c = sc.nextLine();
            Course x = null;
            for (int i = 0; i < enrolledCourses.size(); i++) {
                if (enrolledCourses.get(i).getTitle().equals(c))
                    x = enrolledCourses.get(i);

            }
            if (x == null) {
                System.out.println("No Such Course");
                displayMenu();
            }
            System.out.println("Give feedback in term of rating: ");
            Feedback<Object> f_1 = new Feedback<>();
            Feedback<Object> f_2 = new Feedback<>();
            f_1.setFeedback(sc.nextInt());
            sc.nextLine();
            System.out.println("Any additional Comments : ");
            f_2.setFeedback(sc.nextLine());
            assert x != null;
            x.addFeedback(f_1);
            x.addFeedback(f_2);
            System.out.println("Thank You For Your Valuable Feedback");
            displayMenu();
        }
        else if(choice==8)
            log_out=true;
    }
}