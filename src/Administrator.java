import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Administrator extends User {
    static final String ADMIN_PASSWORD = "admin123";

    public Administrator(String email)
    {
        super(email,ADMIN_PASSWORD);
    }

    public static boolean verifyAdminPassword(String password)
    {

        return ADMIN_PASSWORD.equals(password);
    }


    @Override
    public boolean verifyPassword(String password) {
        return false;
    }

    @Override
    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Manage Courses");
        System.out.println("2. Manage Students");
        System.out.println("3. Manage Faculty");
        System.out.println("4. Manage Complaints");
        System.out.println("5. Logout");
        int choice=sc.nextInt();
        if(choice==1)
        {
            System.out.println("Enter Semester to Deal with: ");
            int s=sc.nextInt();
            Course.print_semcourses(s);
            System.out.println("1. Add Course");
            System.out.println("2. Delete Course");
            int opt=sc.nextInt();
            sc.nextLine();
            if(opt==1)
            {
                System.out.println("Enter Course Title: ");
                String t=sc.nextLine();
                System.out.println("Enter Course Code: ");
                String code=sc.nextLine();

                List<String> pre=new ArrayList<>();
                while(sc.hasNext())
                {
                    String p=sc.next();
                    if(p.equals("done"))
                        break;
                    pre.add(p);
                }
                List<String> es=new ArrayList<>();
                Course n = new Course(code,t,"",0,pre,"","",es,0);
                CourseRegistrationSystem.add_course(CourseRegistrationSystem.getSemwise_courses(),n,s);
                System.out.println("Course Added Successfully");
                System.out.println("1.Back");
                System.out.println("2.Logout");
                int choice_inner=sc.nextInt();
                if(choice_inner==1)
                    displayMenu();
                else if(choice_inner ==2)
                    return;
            }
            else if(opt==2)
            {
                System.out.println("Which Course To Delete? ");
                int del=sc.nextInt();
                Course.del_semwise_course(s,del-1);
                System.out.println("Course Deleted Successfully");
                System.out.println("1.Back");
                System.out.println("2.Logout");
                int choice_inner=sc.nextInt();
                if(choice_inner==1)
                    displayMenu();
                else if(choice_inner ==2)
                    return;
            }
        }
        else if(choice==2)
        {
            System.out.println("1. View Student Records");
            System.out.println("2. Update Student Records");
            int option = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.println("Enter Student's email");
                    String e=sc.nextLine();
                    Student x = null;
                    for(Student s : CourseRegistrationSystem.st)
                    {
                        if(s.getEmail().equals(e))
                            x=s;
                    }
                    if(x==null) {
                        System.out.println("No such Student");
                        break;
                    }
                    System.out.println("Student Pass: " + x.getPass());
                    x.printSGPAandCGPA();
                    break;
                case 2:
                    System.out.println("Enter Student's email");
                    String em=sc.nextLine();
                    Student y = null;
                    for(Student s : CourseRegistrationSystem.st)
                    {
                        if(s.getEmail().equals(em))
                            y=s;
                    }
                    if(y==null)
                    {
                        System.out.println("No such Student");
                        break;
                    }
                    System.out.println("1.Change Password 2.Assign Grade");
                    int op=sc.nextInt();
                    sc.nextLine();
                    if(op==1)
                    {
                        System.out.println("Enter New Password: ");
                        String p = sc.nextLine();
                        y.setPass(p);
                        break;
                    }
                    if(op==2)
                    {
                        System.out.println("Enter Course Title");
                        String co=sc.nextLine();
                        System.out.println("Enter Updated Grades");
                        int grade=sc.nextInt();
                        y.update_grade(co,grade);
                        System.out.println("Grades updated Successfully!");
                        break;
                    }
                default:
                    System.out.println("Invalid option.");
            }
            System.out.println("1.Back");
            System.out.println("2.Logout");
            int choice_inner=sc.nextInt();
            if(choice_inner==1)
                displayMenu();
            else if(choice_inner ==2)
                return;

        }
        else if(choice==3) {
            sc.nextLine();
            System.out.println("Enter Professor's UserID: ");
            String email = sc.nextLine();
            Professor x = null;
            for (Professor p : CourseRegistrationSystem.pf) {
                if (p.getEmail().equals(email)) {
                    x = p;
                }
            }
            if (x == null) {
                System.out.println("No Such Professor: ");
                displayMenu();
            }
            System.out.println("Enter Course To Add in Prof's Teaching List");
            String c = sc.nextLine();
            Professor.addCourse(c);
            for (Course course : CourseRegistrationSystem.cl)
            {
                if(course.getTitle().equals(c))
                    course.setProfessor(email);
            }
            displayMenu();
        }

        else if(choice==4)
        {
            sc.nextLine();
            System.out.println("Manage Complaint of Student: ");
            String user=sc.nextLine();
            Student x=null;
            for(Student s: CourseRegistrationSystem.st)
            {
                if(s.getEmail().equals(user))
                    x=s;
            }
            if(x==null)
            {
                System.out.println("No Such Student");
                displayMenu();
            }
            x.print_complaint_status();
            System.out.println("Resolve Complaint No.");
            int comp=sc.nextInt();
            x.change_complaint_status(comp-1,"Resolved");
            System.out.println("Complaint Resolved Successfully");
            displayMenu();
        }
        else if(choice==5)
            return;
    }
}