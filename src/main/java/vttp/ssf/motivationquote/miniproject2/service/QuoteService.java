package vttp.ssf.motivationquote.miniproject2.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import vttp.ssf.motivationquote.miniproject2.model.Quote;

@Service
public class QuoteService {
    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);
    
    private static String URL = "https://zenquotes.io/api/quotes";

    public Optional<Quote> getQuote (){
        
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;
        
        String quoteUrl = URL;

        try{
            resp = template.getForEntity(quoteUrl, String.class);
            Quote w = Quote.create(resp.getBody());
            return Optional.of(w);
            
        }catch(Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return Optional.empty();
    }

}
