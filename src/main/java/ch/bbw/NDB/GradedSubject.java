package ch.bbw.NDB;
import java.util.List;
public class GradedSubject {
    private String subjectName;
    private List<String> grades;
    private double average;
    public GradedSubject(String subjectName, List<String> grades) {
        this.subjectName = subjectName;
        this.grades = grades;
        calculateAverage();
    }

    private void calculateAverage() {
        double total = 0;
        for (String grade : this.grades) {
            total += Double.parseDouble(grade);
        }

        this.average =  total / grades.size();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public List<String> getGrades() {
        return grades;
    }

    public void addGrade(String grade){
        this.grades.add(grade);
        this.calculateAverage();
    }


    public double getAverage() {
        return average;
    }

    @Override
    public String toString() {
        return "GradedSubject{" +
                "subjectName='" + subjectName + '\'' +
                ", grades=" + grades +
                '}';
    }
}
