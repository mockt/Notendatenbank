package ch.bbw.NDB;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileNotFoundException;
import java.io.IOException;

@org.springframework.stereotype.Controller
public class Controller {
    TextFileReader textFileReader = new TextFileReader();

    public Controller() throws FileNotFoundException {
    }

    @GetMapping("/home")
    public ModelAndView showStock() throws IOException {
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("home");

        modelView.getModel().put("GradedSubjects", textFileReader.readFileToArray());

        return modelView;
    }

    @GetMapping("/close")
    public ModelAndView close() {
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("home");
        return modelView;
    }
}
