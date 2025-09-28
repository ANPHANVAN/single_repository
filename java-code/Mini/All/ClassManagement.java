import java.util.List;
import java.util.ArrayList;

public class Person {
    public Person(){

    }
}

public class Score {
    private String subject;
    private float score;
    public Score(float score, String subject) {
        this.score = score;
        this.subject = subject;
    }

    public void changeScoreInfo(float newScore, String changeSubject){
        this.score = newScore;
        this.subject = changeSubject;
    }

    public float getScore(){ return this.score; }
    public String getSubject(){ return this.subject; }
    public void printScoreInfo(){ System.out.println("Score: " + this.score + ", subject: "+ this.subject); }
}

public class Student {
    private String id;
    private String name;
    private int age;
    private List<Score> scores;

    public Student (String id, String name, int age ) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.scores = new ArrayList<>();
    }

    public Student () {
    }

    public void setName (String name) {
        this.name = name;
    }
    public void setAge (int age) {
        this.age = age;
    }
    public void setId (String id) {
        this.id = id;
    }

    public void showStudentInfo(){
        System.out.println("Student Info: Name - " + this.name + ", Age: " + this.age);
    }

    public void addScore(Score score){
        this.scores.add(score);
    }

    public void seeAllScoreList(){
        System.out.println("############# All Score of student " + this.name + " ###########" );
        if ( this.scores.size() == 0 ){
        System.out.println("Dont have any score in this student" );
        }

        for (Score score : this.scores) {
            System.out.println("Subject: "+ score.getSubject() + " - Score: " + score.getScore());
        }
        System.out.println("#####################################" );
    }
}


public class ClassManagement {
    private String className;
    private List<Student> students;

    public ClassManagement(String className){
        this.className = className;
        this.students = new ArrayList<>();
        System.out.println("Success create class: " + this.className);
    }

    public String getClassName(){
        return this.className;
    }

    public void addNewStudent(Student student){
        this.students.add(student);
        System.out.println("Add success student " + student.getStudentName() + " into class " + this.getClassName());
    }

    public void showAllStudentInfo(){
        System.out.println("############ All Student info in class " + this.className + " ############");
        for ( Student student : this.students ){
            System.out.print("##  ");
            student.showStudentInfo();
        }
        System.out.println("#################################################################");
    }

    public void changeClassName(String newName) {
        this.className = newName;
    }

    public static void main(String[] args){
        // hập dữ liệu bằng CMD
        // hiện thị điều hướng CMD bằng swicth case
        // sắp xếp ten hoc sinh, id
        // import, export dữ liệu excel or csv khi chạy chương trình, update dữ liệu
        // while, do while khác gì nhau, for foreach
        // add people class (dia chi, thong tin ca nhan, salary) role abtract
        // Check lop ton tai chua, roi moi tao class
        // docs comments rõ ràng, theo quy chuẩn (comment theo class, method)
        // Khác nhau của string và new string Object
        
    }
}