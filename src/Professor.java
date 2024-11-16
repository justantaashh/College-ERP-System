import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Professor extends User {
    static List<String> teachingCourses;
    Scanner sc = new Scanner(System.in);

    public Professor(String email, String password)
    {
        super(email, password);
        teachingCourses = new ArrayList<>();
    }

    public boolean verify_pass(String pass)
    {
        return this.password.equals(pass);
    }

    public static void addCourse(String course)
    {
        teachingCourses.add(course);
        System.out.println("Added course to teach: " + course);
    }

    @Override
    public boolean verifyPassword(String password) {
        return false;
    }

    @Override
    public void displayMenu()
    {
        System.out.println("1. Manage Courses: ");
        System.out.println("2. Add a course to teach");
        System.out.println("3. View Enrolled Students");
        System.out.println("4. View Feedback");
        System.out.println("5. Logout");
        int choice=sc.nextInt();
        if(choice==1)
        {
            sc.nextLine();
            System.out.println(teachingCourses);
            System.out.println("Select Course");
            String z=sc.nextLine();
            Course Selected = null;
            for(int i=0;i<CourseRegistrationSystem.cl.size();i++)
            {
                if(CourseRegistrationSystem.cl.get(i).getTitle().equals(z))
                    Selected=CourseRegistrationSystem.cl.get(i);
            }
            if(Selected==null)
            {
                System.out.println("No Such Course");
                displayMenu();
            }
            System.out.println("1. Change Class Timings: ");
            System.out.println("2. Change Course Credits: ");
            int in_choice=sc.nextInt();
            sc.nextLine();
            if(in_choice==1)
            {
                System.out.println("Enter Class Timings: ");
                String x=sc.nextLine();
                Selected.setTimings((x));
                System.out.println("Timings Edited! ");
                displayMenu();
            }
            else if(in_choice==2)
            {
                System.out.println("Updated Credits are: ");
                int credit=sc.nextInt();
                Selected.setCredits(credit);
                System.out.println("Course Credits edited!");
                displayMenu();
            }
        }
        else if(choice==2)
        {
            System.out.println("Semester: ");
            int s=sc.nextInt();
            List<Course> c= CourseRegistrationSystem.getSemwise_courses().get(s);
            for(int i=0;i<c.size();i++)
            {
                System.out.println(i+1+" "+c.get(i).getTitle());
            }
            System.out.println("Select Course");
            int n=sc.nextInt();
            teachingCourses.add(c.get(n-1).getTitle());
            displayMenu();
        }
        else if(choice==3)
        {
            System.out.println("Semester: ");
            int s=sc.nextInt();
            List<Course> c= CourseRegistrationSystem.getSemwise_courses().get(s);
            for(int i=0;i<c.size();i++)
            {
                System.out.println(i+1+" "+c.get(i).getTitle());
            }
            System.out.println("Select Course");
            int n=sc.nextInt();
            Course x=c.get(n-1);
            for(int i = 0; i< x.enrolledStudents.size(); i++)
            {
                for(Student student: CourseRegistrationSystem.st)
                {
                    if(student.getEmail().equals(x.enrolledStudents.get(i)))
                        System.out.println(student.getEmail() +" "+ student.student_grade.get(x.getTitle()));
                }
            }
            displayMenu();
        }
        else if(choice==4)
        {
            System.out.println(teachingCourses);
            sc.nextLine();
            System.out.println("Feedback of course : ");
            String c=sc.nextLine();
            Course x=null;
            for(int i=0;i<CourseRegistrationSystem.cl.size();i++)
            {

                if(CourseRegistrationSystem.cl.get(i).getTitle().equals(c))
                    x=CourseRegistrationSystem.cl.get(i);
            }
            for(int i=0;i<x.feedbackList.size();i++)
            {
                System.out.println(x.feedbackList.get(i).getFeedback());
            }
            displayMenu();
        }

    }
}