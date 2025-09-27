package com.example.Rice_task.controller;


import com.example.Rice_task.entity.Entity;
import com.example.Rice_task.service.Service;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@org.springframework.stereotype.Controller
@RequestMapping("/transactions")
public class Controller {

    private Service service;

    public Controller(Service service)
    {
        this.service = service;
    }

    @GetMapping("/")
    public String home() {
        return "✅ Spring Boot is deployed and connected to Aiven MySQL!";
    }

    @GetMapping
    public String listTransactions(Model model) {

        List<Entity> transactions = service.getAllDetails();
        model.addAttribute("transactions", transactions);
        return "transactions";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("transaction", new Entity());
        return "transaction-form";
    }

    @GetMapping("/search")
    public String searchByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                               Model model) {
        List<Entity> transactions = service.getByDate(date);
        model.addAttribute("transactions", transactions);
        return "transactions";
    }
    @PostMapping
    public String saveTransaction(@ModelAttribute Entity details) {
        service.save(details);
        return "redirect:/transactions";
    }

}









