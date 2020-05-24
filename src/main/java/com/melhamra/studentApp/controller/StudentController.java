package com.melhamra.studentApp.controller;

import com.melhamra.studentApp.model.Student;
import com.melhamra.studentApp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/Student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;


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

}
