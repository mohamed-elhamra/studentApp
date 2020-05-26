package com.melhamra.studentApp.controller;

import com.melhamra.studentApp.model.Student;
import com.melhamra.studentApp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/Student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Value("${imagesDir}")
    private String imageDir;


    @GetMapping("/Index")
    public String getStudentByName(Model model,
                                   @RequestParam(name="page" ,defaultValue = "0") int page,
                                   @RequestParam(name ="keyWord", defaultValue = "") String keyWord){
        Page<Student> list = studentRepository.searchByName(keyWord, PageRequest.of(page, 2));
        int pageCount = list.getTotalPages();
        int[] pages = new int[pageCount];
        for (int i = 0; i < pageCount ; i++) pages[i] = i;

        model.addAttribute("pages", pages);
        model.addAttribute("students", list);
        model.addAttribute("keyWord", keyWord);
        return "students";
    }

    @GetMapping("/form")
    public String studentForm(Model model){
        model.addAttribute("student",new Student());
        return "studentForm";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@Valid Student student,
                              BindingResult bindingResult,
                              @RequestParam(name = "picture") MultipartFile multipartFile) throws IOException {
        if (bindingResult.hasErrors()){
            return "studentForm";
        }

        if(!multipartFile.isEmpty()){
            student.setPhoto(multipartFile.getOriginalFilename());
            multipartFile.transferTo(new File(imageDir+multipartFile.getOriginalFilename()));
        }

        studentRepository.save(student);
        return "redirect:/Student/Index";
    }


}
