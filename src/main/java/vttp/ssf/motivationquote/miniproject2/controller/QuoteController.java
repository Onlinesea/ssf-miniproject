package vttp.ssf.motivationquote.miniproject2.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import vttp.ssf.motivationquote.miniproject2.model.Quote;
import vttp.ssf.motivationquote.miniproject2.service.QuoteService;

@Controller
public class QuoteController {
    
    private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);

    @Autowired
    private QuoteService quoteSvc;

    @GetMapping 
    public String getQuotes(Model model){

        Optional<List<Quote>> opt = quoteSvc.getQuote();

        logger.info(opt.get().get(1).getMessage());

        model.addAttribute("quoteList", opt.get().toArray()); 

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
