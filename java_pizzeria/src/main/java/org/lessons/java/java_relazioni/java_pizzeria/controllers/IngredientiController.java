package org.lessons.java.java_relazioni.java_pizzeria.controllers;

import org.lessons.java.java_relazioni.java_pizzeria.repositories.IngredientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {

    @Autowired
    private IngredientiRepository ingredientiRepository;

    // rotta index
    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredienti", ingredientiRepository.findAll());
        return "ingredienti/index";
    }

    // rotta show
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("ingrediente", ingredientiRepository.findById(id).get());
        return "ingredienti/show";
    }
}
