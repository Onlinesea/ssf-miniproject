package vttp.ssf.motivationquote.miniproject2.controller;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vttp.ssf.motivationquote.miniproject2.model.Journal;
import vttp.ssf.motivationquote.miniproject2.model.Quote;
import vttp.ssf.motivationquote.miniproject2.redis.RedisService;
import vttp.ssf.motivationquote.miniproject2.service.QuoteService;


@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)

public class QuoteRestController {

    private static final Logger logger = LoggerFactory.getLogger(QuoteRestController.class);

    @Autowired
    private QuoteService quoteSvc;

    @Autowired
    private RedisService redisSvc;

    @GetMapping(path = "/{UserId}")
    //Create a function to get the gameBoard with pathvariable
    public ResponseEntity<Journal> getUserEntryList(@PathVariable String UserId) {
        Journal m = redisSvc.findById(UserId);
        return ResponseEntity.ok(m);
    }
    
    @GetMapping(path = "/")
    //Create a function to get the gameBoard with pathvariable
    public ResponseEntity<Quote> getUserEntryDate(@RequestParam String UserId, @RequestParam String date) {
        logger.info("findByDate >>>>>>>" + date);
        Journal m = redisSvc.findById(UserId);
        for(Quote quote:m.getEntryList()){
            if(quote.getDate().equalsIgnoreCase(date)){
                return ResponseEntity.ok(quote);
            }
        }
        Quote emptyQuote = new Quote();
        emptyQuote.setMessage("Entry not found");
        return ResponseEntity.ok(emptyQuote);
    }

}
