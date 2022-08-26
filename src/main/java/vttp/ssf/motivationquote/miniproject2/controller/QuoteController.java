package vttp.ssf.motivationquote.miniproject2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import vttp.ssf.motivationquote.miniproject2.model.Quote;

@Controller
public class QuoteController {
    
    @GetMapping
    public String getQuotes(Model model){
        return "home";
    }

    @PostMapping
    public String displayQuote(Model model, Quote quote ){


        model.addAttribute("form", quote);
        return "quote";
    }

    @GetMapping("/{username}/{date}")
    public String findByDate(Model model, @PathVariable String username, @PathVariable String date){

        return "quote";
    }
}
