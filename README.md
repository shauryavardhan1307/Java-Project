# Java-Project
Student Information System
This is a simple console-based Java application for managing student records, courses, marks, attendance, credits, and calculating CGPA (Cumulative Grade Point Average). It enables users to add students, assign courses with attendance and marks, and view full student details with computed grades and CGPA.

**Features:**

Add new student records (ID & Name)

Assign courses with credit value to students

Enter attendance and marks for each course

Marks conversion to grades, including special handling:

Marks ≥ 90: S (10 points)

Marks ≥ 80: A (9 points)

Marks ≥ 70: B (8 points)

Marks ≥ 60: C (7 points)

Marks ≥ 50: D (6 points)

Marks ≥ 40 and < 50: 5 (5 points)

Marks < 40: F (0 points)

Attendance check before marks entry:

If attendance < 75%, student is debarred and marks auto-assigned as 0


**Menu options**:

1. Add Student:
Enter ID and name for a new student.
(IDs must be digits, names must not contain numbers.)

2. Add Course, Marks & Attendance to Student:
Enter existing student ID, course name, credit, attendance, and marks.
(Attendance is checked before marks. Low attendance de-bars.)

3. Display Students:
Shows detailed information for all students, including CGPA.

4. Exit:
Ends the application.

**Data Validation**

Student ID: Only digits allowed, must be unique.

Student Name: Cannot contain digits.

Course Name: Only letters and spaces allowed.

Course Credit: Must be positive integer.
CGPA and Grade Calculation
Grade points table:

Grade	Marks	Points
S	≥ 90	10
A	≥ 80	9
B	≥ 70	8
C	≥ 60	7
D	≥ 50	6
5	≥ 40 < 50	5
F	< 40	0
CGPA formula:
CGPA
=
∑
(
credits
×
grade points
)
∑
(
credits
)
CGPA= 
∑(credits)
∑(credits×grade points)
 

Debarring:
Any course attendance < 75% leads to automatic debarring and marks set to 0 (grade F).
Attendance: 0.0 – 100.0 only.

Marks: 0 – 100 only.



