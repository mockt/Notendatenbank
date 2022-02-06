package ch.bbw.NDB;

import java.util.ArrayList;
import java.util.List;

public class GradedSubject {
    private String subjectName;
    private List<String> grades = new ArrayList<>();

    public GradedSubject(String subjectName, List<String> grades) {
        this.subjectName = subjectName;
        this.grades = grades;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<String> getGrades() {
        return grades;
    }

    public void setGrades(List<String> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "GradedSubject{" +
                "subjectName='" + subjectName + '\'' +
                ", grades=" + grades +
                '}';
    }
}
