import java.util.*;

class CourseRegistrationSystem
{
    public CourseRegistrationSystem()
    {
        sc = new Scanner(System.in);
    }

    static List<Student> st;
    static List<Professor> pf;
    static List<Course> cl=new ArrayList<>();
    Scanner sc;
    public static HashMap<Integer, List<Course>> semwise_courses = new HashMap<>();

    public static HashMap<Integer,List<Course>> getSemwise_courses()
    {
        return semwise_courses;
    }

    public static void add_course(HashMap<Integer,List<Course>> sem,Course c,int s)
    {
        sem.get(s).add(c);
    }
    public static void prepopulate()
    {
        pf=new ArrayList<>();
        pf.add(new Professor("Arun","prof123"));
        st = new ArrayList<>();
        st.add(new Student("antash","antiiit"));
        st.add(new Student("parth","pariiit"));
        st.add(new Student("garvit","gariiit"));
        String[] courses = {"Intro To Programming", "Intro To Design", "Communication Skills", "Linear Algebra", "Digital Circuits"};
        String[] course_code = {"CSE101", "DES101", "COM101", "MTH101", "DC101"};
        String[] course_timing = {"10:00-11:15", "11:30-12:45", "14:00-15:00", "15:15-16:15", "16:30-17:30"};
        String[] professors = {"Arun Gupta", "Priya Sharma", "Rajesh Kumar", "Sunita Rao", "Anil Singh"};
        int[] course_credits = {4, 4, 4, 4, 4};
        String[] venue = {"C101", "C102", "C101", "C102", "C201"};
        List<Course> sem_course = new ArrayList<>();
        List<String> ens=new ArrayList<>();
        ens.add("antash");
        ens.add("parth");
        ens.add("garvit");
        for (int i = 0; i < 5; i++)
        {
            List<String> pre = new ArrayList<>();
            Course c = new Course(course_code[i], courses[i], professors[i], course_credits[i], pre, course_timing[i], venue[i],ens,60);
            st.get(0).enrolledCourses.add(c);
            sem_course.add(c);
            cl.add(c);
        }
        Course.insert_semwise_course(1, sem_course);
        for(int i=0;i<5;i++)
        {
            st.get(0).update_grade(courses[i],100);
            st.get(1).update_grade(courses[i],80);
            st.get(2).update_grade(courses[i],0);

        }
        sem_course=new ArrayList<>();
        String[] courses_2 = {"Data Structures", "Graphic Design", "Advanced Communication", "Calculus", "Microprocessors"};
        String[] course_code_2 = {"CSE201", "DES201", "COM201", "MTH201", "EC201"};
        String[] course_timing_2 = {"09:00-10:15", "10:30-11:45", "13:00-14:15", "14:30-15:45", "16:00-17:15"};
        String[] professors_2 = {"Nitin Verma", "Sneha Joshi", "Vikas Pandey", "Meera Nair", "Karan Mehta"};
        int[] course_credits_2 = {4, 4, 3, 4, 4};
        String[] venue_2 = {"C301", "C302", "C303", "C304", "C305"};
        for (int i = 0; i < 5; i++)
        {
            List<String> enst=new ArrayList<>();
            List<String> pre = new ArrayList<>();
            pre.add(courses[i]);
            Course c = new Course(course_code_2[i], courses_2[i], professors_2[i], course_credits_2[i], pre, course_timing_2[i], venue_2[i],enst,60);
            sem_course.add(c);
        }
        Course.insert_semwise_course(2,sem_course);


    }
    public void signUp()
    {
        System.out.println("Are you a Student or Professor? (1. Student, 2. Professor, 3.Admin): ");
        System.out.println("4.Go Back");
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline
        if(choice==4)
        {
            return;
        }
        if(choice==3)
        {
            System.out.println("Admin? No need to Register....");
            return;
        }

        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (choice == 1)
        {
            st.add(new Student(email,password));
            System.out.println("Student signed up successfully!");
        }
        else if (choice == 2)
        {
            pf.add(new Professor(email, password));
            System.out.println("Professor signed up successfully!");
        }
        else
            System.out.println("Invalid choice selected.");
    }

    public void login() throws InvalidLoginException {
        System.out.println("Login as 1.Student 2.Professor 3.Admin");
        int ch=sc.nextInt();
        sc.nextLine();
        if(ch==1)
        {
            System.out.println("Login As 1.TA 2.Student?");
            int c=sc.nextInt();
            sc.nextLine();
            System.out.print("Enter email: ");
            String email = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            Student loggedin=null;
            for(Student s : st)
            {
                if(s.email.equals(email)&&s.verify_pass(password)) {
                    loggedin = s;
                    break;
                }
            }
            if(loggedin!=null) {
                System.out.println("Login Successfully");
                if(c==1)
                {
                    TA t=new TA(loggedin);
                    t.displayMenu();
                }
                else if(c==2)
                loggedin.displayMenu();
            }
            else
                throw new InvalidLoginException("Invalid username or password.");
        }
        else if(ch==2)
        {
            System.out.print("Enter email: ");
            String email = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            Professor loggedin=null;
            for(Professor s : pf)
            {
                if(s.email.equals(email)&&s.verify_pass(password)) {
                    loggedin = s;
                    break;
                }
            }
            if(loggedin!=null) {
                System.out.println("Login Successfully");
                loggedin.displayMenu();
            }
            else
                System.out.println("Invalid Email or Password");
        }
        else if(ch==3) {
            System.out.print("Enter email: ");
            String email = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            if (email.equals("admin@iiitd.com") && password.equals(Administrator.ADMIN_PASSWORD)) {
                Administrator admin = new Administrator(email);
                System.out.println("Admin logged in successfully!");
                admin.displayMenu();
            } else {
                System.out.println("Invalid email or password.");
            }

        }
    }

    public static void main(String[] args) {
        prepopulate();


        CourseRegistrationSystem system = new CourseRegistrationSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Course Registration System!");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                system.signUp();
            } else if (choice == 2) {
                try {
                    system.login();
                } catch (InvalidLoginException e) {
                    System.out.println(e.getMessage());
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }
}