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
@RequestMapping("/transactions")
public class Controller {

    private final Service service;

    public TransactionController(Service service) {
        this.service = service;
    }

    // Redirect root path to the transactions list
    @GetMapping("/")
    public String redirectToTransactions() {
        return "redirect:/transactions";
    }

    // List all transactions
    @GetMapping
    public String listTransactions(Model model) {
        List<Entity> transactions = service.getAllDetails();
        model.addAttribute("transactions", transactions);
        return "transactions";
    }

    // Show form to add new transaction
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("transaction", new Entity());
        return "transaction-form";
    }

    // Handle form submission to save a transaction
    @PostMapping
    public String saveTransaction(@ModelAttribute("transaction") Entity details) {
        service.save(details);
        return "redirect:/transactions";
    }

    // Search transactions by date
    @GetMapping("/search")
    public String searchByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                               Model model) {
        List<Entity> transactions = service.getByDate(date);
        model.addAttribute("transactions", transactions);
        return "transactions";
    }
}
