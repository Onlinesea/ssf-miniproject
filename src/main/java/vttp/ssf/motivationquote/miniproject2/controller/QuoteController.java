package vttp.ssf.motivationquote.miniproject2.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.ssf.motivationquote.miniproject2.model.Journal;
import vttp.ssf.motivationquote.miniproject2.model.Quote;
import vttp.ssf.motivationquote.miniproject2.redis.RedisService;
import vttp.ssf.motivationquote.miniproject2.service.AccountService;
import vttp.ssf.motivationquote.miniproject2.service.QuoteService;

@Controller

public class QuoteController {
    
    private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);

    @Autowired
    private QuoteService quoteSvc;

    @Autowired
    private RedisService redisSvc;

    @Autowired
    private AccountService accSvc;

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("journal", new Journal());

        return "login";
    }

    @PostMapping("/login")
    public String entryList(Journal journal, Model model){

        logger.info(journal.getUser());
        
        //Retrieving the jounral form redis dataBase if it exist,
        //if not journalLoad will be empty and user add to list of Users
        Journal journalLoad = accSvc.checkUser(journal.getUser());
        logger.info(journalLoad.getUser());
        //logger.info(journalLoad.getEntryList().get(0).getMessage());
        model.addAttribute("journalLoad", journalLoad);
        if(journal.getEntryList() != null)
        model.addAttribute("entryList", journal.getEntryList().toArray());
        
        return "entryList";
    }


    @GetMapping("/motivationQuotes")
    public String getQuotes(Model model, String user){
        
        List<Quote> quoteList = quoteSvc.getQuote();
        Quote quote = quoteList.get(1);
        //logger.info("Movitation Quote selected >>>>>" + quote.getMessage());
        //logger.info("Movitation Author selected >>>>>" + quote.getAuthor());
        //logger.info("Movitation ID selected >>>>>" + quote.getId());
        //logger.info("User selected >>>>>" + user);
        Journal journalLoad = accSvc.checkUser(user);

        Quote currentEntry = new Quote();
        currentEntry.setUser(user);

        journalLoad.setCurrentEntry(currentEntry);
        model.addAttribute("quoteList", quoteList.toArray());
        model.addAttribute("journal", journalLoad.currentEntry);

        return "QuoteList"; 
    }
    
    @GetMapping("/entry")
    public String entry(Model model, String user, String message, String author){

        logger.info("Movitation Quote selected >>>>>" + message);
        logger.info("Movitation Author selected >>>>>" + author);
        logger.info("User inside entry >>>>>" + user);
        Quote newQuote = new Quote();       
        Journal journalLoad = redisSvc.findById(user);
        journalLoad.currentEntry.setUser(user);
        journalLoad.currentEntry.setMessage(message); 
        journalLoad.currentEntry.setAuthor(author);

        model.addAttribute("journal", journalLoad);
        model.addAttribute("currentEntry", journalLoad.getCurrentEntry());


        //model.addAttribute("journal", journalLoad);

        return "entry";
    }
    @PostMapping("/entrySaved")
    public String savingEntry(Model model, Quote currentEntry){

        logger.info("User inside the entrySaved>>>>>>" + currentEntry.getUser());
        Journal journalLoad = accSvc.checkUser(currentEntry.getUser());
        logger.info("EntrySaved >>>>>>>>>" + currentEntry.getMessage());
        logger.info("EntrySaved Author>>>>>>>>>" + currentEntry.getAuthor());

        
        //logger.info("Journal user inside entrySaved >>>" + journalLoad.getUser());
        journalLoad.addQuote(currentEntry);
        int result =redisSvc.update(journalLoad);
        logger.info("1 saved, 0 is not >>>>" + result);
        model.addAttribute("form", currentEntry);
        return "save";
    }

    

}
