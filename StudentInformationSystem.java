import java.util.*;

class Student {
    private int id;
    private String name;
    private List<String> courses;
    private Map<String, Integer> marks;
    private Map<String, Double> attendance;
    private Map<String, Integer> credits;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
        this.marks = new HashMap<>();
        this.attendance = new HashMap<>();
        this.credits = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void addCourse(String course, int credit) {
        if (!courses.contains(course)) {
            courses.add(course);
            marks.put(course, 0);
            attendance.put(course, 0.0);
            credits.put(course, credit);
        }
    }

    public void setMarks(String course, int mark) {
        if (courses.contains(course)) {
            marks.put(course, mark);
        }
    }

    public void setAttendance(String course, double attend) {
        if (courses.contains(course)) {
            attendance.put(course, attend);
        }
    }

    public void setCredit(String course, int credit) {
        credits.put(course, credit);
    }

    public Integer getMark(String course) {
        return marks.get(course);
    }

    public Double getAttendance(String course) {
        return attendance.get(course);
    }

    public Integer getCredit(String course) {
        return credits.get(course);
    }

    public String getGrade(String course) {
        Integer mark = marks.get(course);
        if (mark == null) return "N/A";
        if (mark >= 90) return "S";
        else if (mark >= 80) return "A";
        else if (mark >= 70) return "B";
        else if (mark >= 60) return "C";
        else if (mark >= 50) return "D";
        else if (mark >= 40) return "E";
        else return "F";
    }

    private double gradePoint(String grade) {
        switch (grade) {
            case "S": return 10.0;
            case "A": return 9.0;
            case "B": return 8.0;
            case "C": return 7.0;
            case "D": return 6.0;
            case "E": return 5.0;
            case "F": return 0.0;
            default: return 0.0;
        }
    }

    public boolean isDebarred(String course) {
        Double att = attendance.get(course);
        return att != null && att < 75.0;
    }

    public double calculateCGPA() {
        double totalPoints = 0.0;
        int totalCredits = 0;
        for (String c : courses) {
            int credit = getCredit(c);
            String grade = getGrade(c);
            double gp = gradePoint(grade);
            totalPoints += credit * gp;
            totalCredits += credit;
        }
        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student{id=").append(id)
          .append(", name=").append(name)
          .append(", CGPA=").append(String.format("%.2f", calculateCGPA()))
          .append(", courses=[");
        for (String c : courses) {
            sb.append(c)
              .append("(Credit:").append(getCredit(c))
              .append(", Mark:").append(marks.get(c))
              .append(", Grade:").append(getGrade(c))
              .append(", Attendance:").append(attendance.get(c)).append("%")
              .append(", Debarred:").append(isDebarred(c) ? "Yes" : "No")
              .append("), ");
        }
        if (!courses.isEmpty()) sb.setLength(sb.length() - 2);
        sb.append("]}");
        return sb.toString();
    }
}

public class StudentInformationSystem {
    private Map<Integer, Student> studentData = new HashMap<>();
    private Scanner input = new Scanner(System.in);

    public void addStudent() {
        System.out.print("Enter student ID: ");
        String idInput = input.nextLine().trim();
        if (!idInput.matches("\\d+")) {
            System.out.println("Error: Student ID must contain only digits.");
            return;
        }
        int id = Integer.parseInt(idInput);

        System.out.print("Enter student name: ");
        String name = input.nextLine().trim();
        if (name.matches(".*\\d.*")) {
            System.out.println("Error: Name cannot contain numbers.");
            return;
        }

        if (studentData.containsKey(id)) {
            System.out.println("Error: Student ID already exists.");
            return;
        }

        Student student = new Student(id, name);
        studentData.put(id, student);
        System.out.println("Student added successfully.");
    }

    public void addCourseToStudent() {
        System.out.print("Enter student ID: ");
        int id;
        try {
            id = Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid student ID.");
            return;
        }
        Student student = studentData.get(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter course name to add: ");
        String course = input.nextLine().trim();
        if (!course.matches("[a-zA-Z ]+")) {
            System.out.println("Error: Course name must contain only letters and spaces.");
            return;
        }
        System.out.print("Enter course credit (positive integer): ");
        String creditInput = input.nextLine().trim();
        int credit;
        try {
            credit = Integer.parseInt(creditInput);
            if (credit <= 0) {
                System.out.println("Error: Credit must be a positive integer.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid credit value.");
            return;
        }
        student.addCourse(course, credit);

        System.out.print("Enter attendance percentage for the course (0.0 - 100.0): ");
        String attendanceInput = input.nextLine().trim();
        double attendance;
        try {
            attendance = Double.parseDouble(attendanceInput);
            if (attendance < 0.0 || attendance > 100.0) {
                System.out.println("Error: Attendance should be between 0.0 and 100.0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid attendance input.");
            return;
        }
        student.setAttendance(course, attendance);

        if (attendance < 75.0) {
            student.setMarks(course, 0);
            System.out.println("Student is debarred from " + course + " due to low attendance (<75%). Marks set to 0.");
            return;
        }

        System.out.print("Enter marks for the course (0-100): ");
        String marksInput = input.nextLine().trim();
        int marks;
        try {
            marks = Integer.parseInt(marksInput);
            if (marks < 0 || marks > 100) {
                System.out.println("Error: Marks should be between 0 and 100.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid marks input.");
            return;
        }
        student.setMarks(course, marks);

        System.out.println("Course, credit, attendance, marks, grade, and debar status updated for student.");
    }

    public void displayStudents() {
        if (studentData.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("Students:");
        for (Student s : studentData.values()) {
            System.out.println(s);
        }
    }

    public void menu() {
        while (true) {
            System.out.println();
            System.out.println("1. Add Student");
            System.out.println("2. Add Course, Marks & Attendance to Student");
            System.out.println("3. Display Students");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice;
            try {
                choice = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option, please enter a number.");
                continue;
            }
            switch (choice) {
                case 1: addStudent(); break;
                case 2: addCourseToStudent(); break;
                case 3: displayStudents(); break;
                case 4: System.exit(0);
                default: System.out.println("Invalid option, please try again.");
            }
        }
    }

    public static void main(String[] args) {
        StudentInformationSystem sis = new StudentInformationSystem();
        sis.menu();
    }
}
