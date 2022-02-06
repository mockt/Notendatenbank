package ch.bbw.NDB;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextFileReader extends Reader {
    public final String pathToCsv = "./src/main/resources/FächerUndNoten.txt";

    BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));

    public TextFileReader() throws FileNotFoundException {
    }

    public List<GradedSubject> readFileToArray() throws IOException {
        List<GradedSubject> gradedSubjects = new ArrayList<>();
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] line = row.split(";");
            String subjectName = line[0];
            List<String> grades = Arrays.asList(line).subList(1, line.length);
            gradedSubjects.add(new GradedSubject(subjectName, grades));
        }
        return gradedSubjects;
    }


    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
