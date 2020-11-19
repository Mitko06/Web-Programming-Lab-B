package mk.ukim.finki.wp.lab.web.controllers;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping()
    public String getCoursesPage(@RequestParam(required = false) String error, Model model){

        if (error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("errorMessage","That ID doesn't exist");
        }
        else{
            model.addAttribute("hasError",false);
        }
        model.addAttribute("courses",courseService.listAll());

        return "listcourses";

    }

    @GetMapping("/add")
    public String getAddCoursePage(Model model){
        List<Teacher> teacherList = teacherService.findAll();
        model.addAttribute("teachers",teacherList);
        return "addcourse";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String name, @RequestParam String description, @RequestParam String teacher){
        System.out.println();
        courseService.addCourse(name,description,Long.parseLong(teacher));
        return "redirect:/courses";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model){

        try{
            courseService.getCourseById(id);

        }catch (NoSuchElementException exception){
            exception.getMessage();
            return "redirect:/courses?error=true";
        }
        Course editingCourse = courseService.getCourseById(id);
        List<Teacher> teacherList = teacherService.findAll();
        model.addAttribute("editingCourse",editingCourse);
        model.addAttribute("teachers",teacherList);
        return "addcourse";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseService.delete(id);
        return "redirect:/courses";
    }

}
