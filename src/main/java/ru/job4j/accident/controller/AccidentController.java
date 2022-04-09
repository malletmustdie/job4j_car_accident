package ru.job4j.accident.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentJdbcService;

@Controller
@RequiredArgsConstructor
public class AccidentController {

    private final AccidentJdbcService accidentJdbcService;

    @GetMapping("/create")
    public String create(Model model) {
        var accidentTypes = accidentJdbcService.findAllAccidentTypes();
        var accidentRules = accidentJdbcService.findAllAccidentRules();
        model.addAttribute("types", accidentTypes);
        model.addAttribute("rules", accidentRules);
        return "accident/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") Integer id, Model model) {
        var accident = accidentJdbcService.findAccidentById(id);
        var accidentTypes = accidentJdbcService.findAllAccidentTypes();
        model.addAttribute("types", accidentTypes);
        model.addAttribute("accident", accident);
        return "accident/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest request) {
        var accidentType = accidentJdbcService.findAccidentTypeById(
                Integer.parseInt(request.getParameter("type.id"))
        );
        var accidentRules = request.getParameterValues("ruleIds");
        accident.setAccidentType(accidentType);
        accidentJdbcService.addAccident(accident, accidentRules);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, HttpServletRequest request) {
        var accidentType = accidentJdbcService.findAccidentTypeById(
                Integer.parseInt(request.getParameter("type.id"))
        );
        accident.setAccidentType(accidentType);
        accidentJdbcService.updateAccident(accident);
        return "redirect:/";
    }

}
