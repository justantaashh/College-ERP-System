import java.util.List;
import java.util.Scanner;
public class TA extends Student{
    public TA(Student s) {

        super(s.email,s.password);
    }
    public void displayMenu()
    {
        System.out.println("TA Duties");
        ta_duty();
    }
    public void ta_duty()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("1.View Grades");
        System.out.println("2.Assign Grades");
        System.out.println("3.Log Out");
        int ch=sc.nextInt();
        if(ch==1)
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
        else if(ch==2)
        {
            assign_grade();
            displayMenu();
        }
        else
            return;
    }
    public void assign_grade()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Student's email");
        String em=sc.nextLine();
        Student y = null;
        for(Student s : CourseRegistrationSystem.st)
        {
            if(s.getEmail().equals(em))
                y=s;
        }
        System.out.println("Enter Course Title");
        String co=sc.nextLine();
        System.out.println("Enter Updated Grades");
        int grade=sc.nextInt();
        assert y != null;
        y.update_grade(co,grade);
        System.out.println("Grades updated Successfully!");
    }
}
