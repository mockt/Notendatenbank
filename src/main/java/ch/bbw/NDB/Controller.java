package ch.bbw.NDB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@org.springframework.stereotype.Controller
public class Controller {
    FileController fileController = new FileController();

    public Controller() {
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("home");
        return modelView;
    }

    @PostMapping("/grades")
    public ModelAndView load(@RequestParam("file") MultipartFile file) throws IOException {
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("grades");
        if (file.isEmpty()){
            modelView.getModel().put("GradedSubjects", fileController.readGradedSubjectsFromFile());
        }else {
            modelView.getModel().put("GradedSubjects", fileController.readInputStream(file.getOriginalFilename(), file.getInputStream()));
        }
        return modelView;
    }

    @GetMapping("/close")
    public ModelAndView close() {
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("grades");
        return modelView;
    }

    @GetMapping("/addGrade")
    public ModelAndView addGrade(
            @RequestParam(name = "subjectName") String subjectName,
            @RequestParam(name = "grade") String grade
    ) throws IOException {
        if (subjectName != null && grade != null && !grade.isBlank()) {
            List<GradedSubject> gradedSubjects = fileController.getGradedSubjects();
            GradedSubject subject = gradedSubjects.stream().filter(gradedSubject -> gradedSubject.getSubjectName().equals(subjectName))
                    .findFirst().get();
            subject.addGrade(grade);
            fileController.saveToFile();
        }

        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("grades");
        modelView.getModel().put("GradedSubjects", fileController.getGradedSubjects());
        return modelView;
    }

    @GetMapping("/add")
    public ModelAndView add(
            @RequestParam(name = "newSubjectName") String newSubjectName,
            @RequestParam(name = "newGrades") String newGradesString
    ) throws IOException {
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("grades");
        String[] split = newGradesString.split(",");
        List<String> newGrades = new ArrayList(Arrays.asList(split));
        modelView.getModel().put("GradedSubjects", fileController.addGradedSubject(new GradedSubject(newSubjectName, newGrades)));
        return modelView;
    }
}
