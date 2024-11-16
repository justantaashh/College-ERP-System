Course Registration System
A Java application called the Course Registration System is used to handle student course enrolments at a school. Users can register as instructors, administrators, or students, and then carry out different tasks based on their job.

Features
•	User Registration and Login: Assistance with account creation and system login for educators, administrators, and students.
•	Administrators have the ability to add and manage courses, as well as establish requirements and schedules.
•	Enrolment: Students have the ability to examine their schedules, manage their enrolled courses, and enrol in new courses.
Managing and seeing their courses is a feature that professors have access to.

Application
•	Register: Select the option to register as a new user (as a student, instructor, or administrator) from the main menu.
•	Login: Click the login button and provide your login information if you already have an account.
•	Navigate: Each kind of user will get a unique menu after logging in, which enables them to carry out tasks associated with their role.

Prepopulated Data
   	Preloaded with starting data, the Course Registration System mimics a realistic setting where the system is already in operation. Sample users, courses, and enrolments are included in this data to show how the system works with actual user information.
Students : 3 Students –
•	UserID - antash , Password - antiiit
•	UserID – parth , Password – pariiit
•	UserID – garvit, Password – gariiit
Professor : 1 Professor-
•	UserID – Arun, Password – prof123
Admin : 1 Admin-
•	UserID - admin@iiitd.com, Password-admin123
Course : 10 Courses Over 2 Semesters 
Data was filled using prepopulate function in CourseRegistrationSystem.


Various OOP’s Concepts have been applied in the code – 
•	Encapsulation: Private characteristics are used in student, professor, and course classes to store information such as passwords, email addresses, course information, and student lists. These classes offer public methods like enrollCourse in Student or addCourse in Professor, as well as getters and setters for different fields, to access and edit the data.
•	Abstraction: The abstract method displayMenu() is part of the abstract User class, and its subclasses implement it in accordance with their roles within the system.
•	Interface: In order to authenticate users and expand the functionality across all classes, IUserAuthenticable integrates password verification.
•	Inheritance: Classes can inherit characteristics (properties and methods) from another class through inheritance. It has the advantage of offering a transparent relationship model and encouraging code reuse.
•	Polymorphism: The User class's displayMenu() method is overridden by the Student and Professor classes to provide particular functionality depending on the object type at runtime.
•	Association: Within the CourseRegistrationSystem, classes have relationships with terms like Student, Professor, and Course. For instance, a professor and student are connected by the courses they both teach or take.

Assignment - 2

Feedback class is used to implement generic programming where student can give course feedback to prof in rating terms or comments
and CourseFullException, InvalidLoginException are made for custom exception classes, to throw custom exception when a student tries to register of a course that has raeched its capacity and when a user tries to login with wrong credentials respectively.`
There are several Assumptions in the code which are specified in the demonstration.

Demonstration - The student can log in as TA and commit to their duties that is view and assign grades just like professor but not manage course ... it is assumed that the TA knows and selects their course only.
