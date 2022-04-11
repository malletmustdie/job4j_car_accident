package ru.job4j.accident.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final AccidentService accidentService;

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidents = accidentService.findAllAccidents();
        model.addAttribute("accidents", accidents);
        return "index";
    }

}
