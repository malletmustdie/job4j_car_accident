package ru.job4j.accident.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.job4j.accident.dto.AccidentDto;
import ru.job4j.accident.service.AccidentRuleService;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;

@Controller
@RequiredArgsConstructor
public class AccidentController {

    private final AccidentService accidentService;

    private final AccidentTypeService accidentTypeService;

    private final AccidentRuleService accidentRuleService;

    @GetMapping("/create")
    public String create(Model model) {
        var accidentTypes = accidentTypeService.findAllAccidentTypes();
        var accidentRules = accidentRuleService.findAllAccidentRules();
        model.addAttribute("types", accidentTypes);
        model.addAttribute("rules", accidentRules);
        return "accident/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") Integer id, Model model) {
        var accidentDto = accidentService.findAccidentById(id);
        var accidentTypes = accidentTypeService.findAllAccidentTypes();
        model.addAttribute("types", accidentTypes);
        model.addAttribute("accident", accidentDto);
        return "accident/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid AccidentDto accident,
                       BindingResult bindingResult,
                       HttpServletRequest request,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/create";
        }
        var accidentType = accidentTypeService.findAccidentTypeById(
                Integer.parseInt(request.getParameter("type.id"))
        );
        var accidentRules = request.getParameterValues("ruleIds");
        accident.setAccidentType(accidentType);
        accidentService.addAccident(accident, accidentRules);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute @Valid AccidentDto accident,
                         BindingResult bindingResult,
                         HttpServletRequest request,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/edit?id="+ request.getParameter("id");
        }
        var accidentType = accidentTypeService.findAccidentTypeById(
                Integer.parseInt(request.getParameter("type.id"))
        );
        accident.setAccidentType(accidentType);
        accidentService.updateAccident(accident);
        return "redirect:/";
    }

}
