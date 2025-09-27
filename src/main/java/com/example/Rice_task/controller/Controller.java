package com.example.Rice_task.controller;

import com.example.Rice_task.entity.Entity;
import com.example.Rice_task.service.Service;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class Controller {

    private final Service service;

    public TransactionController(Service service) {
        this.service = service;
    }

    // Root endpoint for home page
    @GetMapping("/")
    public String home() {
        return "index";  // maps to src/main/resources/templates/index.html
    }

    // List all transactions
    @GetMapping("/transactions")
    public String listTransactions(Model model) {
        List<Entity> transactions = service.getAllDetails();
        model.addAttribute("transactions", transactions);
        return "transactions";  // maps to transactions.html
    }

    // Show form to add a new transaction
    @GetMapping("/transactions/new")
    public String showAddForm(Model model) {
        model.addAttribute("transaction", new Entity());
        return "transaction-form";  // maps to transaction-form.html
    }

    // Search transactions by date
    @GetMapping("/transactions/search")
    public String searchByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                               Model model) {
        List<Entity> transactions = service.getByDate(date);
        model.addAttribute("transactions", transactions);
        return "transactions";
    }

    // Save transaction (handle form post)
    @PostMapping("/transactions")
    public String saveTransaction(@ModelAttribute Entity details) {
        service.save(details);
        return "redirect:/transactions";
    }
}
