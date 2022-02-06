package ch.bbw.NDB;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import java.io.*;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
public class FileController extends Reader {
    public String path = "./src/main/resources/FächerUndNoten.txt";
    List<GradedSubject> gradedSubjects = new ArrayList();
    public FileController() {
    }

    public List<GradedSubject> readInputStream(String fileName, InputStream inputStream) throws IOException {
        File targetFile = new File("./src/main/resources/" + fileName);
        java.nio.file.Files.copy(
                inputStream,
                targetFile.toPath(),
                StandardCopyOption.REPLACE_EXISTING);
        IOUtils.closeQuietly(inputStream);
        this.path = targetFile.getPath();
        return readGradedSubjectsFromFile();
    }

    public List<GradedSubject> readGradedSubjectsFromFile() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(this.path));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] line = row.split(";");
            String subjectName = line[0];
            gradedSubjects.add(new GradedSubject(subjectName, new ArrayList(Arrays.asList(line).subList(1, line.length))));
        }
        return gradedSubjects;
    }

    public List<GradedSubject> getGradedSubjects() {
        return this.gradedSubjects;
    }

    public List<GradedSubject> addGradedSubject(GradedSubject gradedSubject) throws IOException {
        this.gradedSubjects.add(gradedSubject);
        saveToFile();
        return this.gradedSubjects;
    }

    public void saveToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (GradedSubject gradedSubject : this.gradedSubjects) {
            writer.write(gradedSubject.getSubjectName() + ";");
            Iterator<String> iterator = gradedSubject.getGrades().iterator();
            while (iterator.hasNext()) {
                writer.write(iterator.next());
                if (iterator.hasNext()) {
                    writer.write(";");
                }
            }
            writer.newLine();
        }

        writer.close();
    }


    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
