package org.lessons.java.java_relazioni.java_pizzeria.controllers;

import org.lessons.java.java_relazioni.java_pizzeria.models.OffertaSpeciale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/offerte")
public class OffertaSpecialeController {

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offertaSpeciale") OffertaSpeciale offertaSpecialeForm,
            BindingResult bindingResult, Model model) {

        // verifico che la validazione sia avvenuta correttamente
        if (bindingResult.hasErrors()) {
            return "offerte_speciali/create";
        }

        return "redirect:/pizze";
    }
}
