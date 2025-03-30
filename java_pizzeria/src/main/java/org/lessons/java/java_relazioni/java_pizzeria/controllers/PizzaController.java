package org.lessons.java.java_relazioni.java_pizzeria.controllers;

import java.util.List;
import org.lessons.java.java_relazioni.java_pizzeria.models.Pizza;
import org.lessons.java.java_relazioni.java_pizzeria.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizze")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    // creo la rotta index
    @GetMapping
    public String index(Model model) {
        // creo la lista di pizze
        List<Pizza> pizze = repository.findAll();

        model.addAttribute("pizze", pizze);

        return "pizze/index";
    }

    // creo la rotta show
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        // creo la singola pizza
        Pizza pizza = repository.findById(id).get();
        model.addAttribute("pizza", pizza);

        return "pizze/show";
    }

    // creo la rotta create
    @GetMapping("/create")
    public String create(Model model) {
        // creo una nuova pizza
        model.addAttribute("pizza", new Pizza());

        return "pizze/create";
    }

    // creo un metodo per aggiungere una pizza
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model) {
        // verifico che il processo di validazione sia avvenuto correttamente
        if (bindingResult.hasErrors()) {
            return "pizze/create";
        }

        // salvo il dato
        repository.save(pizzaForm);

        return "redirect:/pizze";
    }

    // creo la rotta edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());
        return "pizze/edit";
    }

    // creo update per modificare gli elementi della card
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model) {

        // verifico che il processo di validazione sia avvenuto correttamente
        if (bindingResult.hasErrors()) {
            return "pizze/edit";
        }

        // aggiorno il dato
        repository.save(pizzaForm);

        return "redirect:/pizze";
    }

    // creo la rotta delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        // cancello la pizza
        repository.deleteById(id);

        return "redirect:/pizze";
    }
}
