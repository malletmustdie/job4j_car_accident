package ru.job4j.accident.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentJdbcService;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final AccidentJdbcService accidentJdbcService;

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidents = accidentJdbcService.findAllAccidents();
        model.addAttribute("accidents", accidents);
        return "index";
    }

}
