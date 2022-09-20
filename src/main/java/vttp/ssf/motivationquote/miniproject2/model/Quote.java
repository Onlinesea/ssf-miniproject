package vttp.ssf.motivationquote.miniproject2.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.ssf.motivationquote.miniproject2.redis.RedisService;

@Component

public class Quote implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Quote.class);

    private String user;
    private String message;
    private String author;
    private String thoughts;
    private String date;  
    private String feelings;

    public static List createList(String json) throws IOException{

        Quote quote = new Quote();

        List<Quote> quoteList = new LinkedList<>();

        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);
            JsonArray o = r.readArray();
            quoteList = o.stream()
                        .map(v->(JsonObject)v)
                        .map(v-> quote.createQuote(v))
                        .toList(); 

            //RedisService svc = new RedisService();
            //Quote saveQuote = quoteList.get(1);
            //svc.save(saveQuote);            
        }

        return quoteList;
    }

    public static Quote createQuote(JsonObject v){

        Quote quote = new Quote();

        String message = v.getString("q"); 
        quote.setMessage(message);

        String author = v.getString("a");
        quote.setAuthor(author);   

        //logger.info("quote created******************************"); //Quote created is okay 

        return quote;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getThoughts() {
        return thoughts;
    }

    public void setThoughts(String thoughts) {
        this.thoughts = thoughts;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFeelings() {
        return feelings;
    }

    public void setFeelings(String feelings) {
        this.feelings = feelings;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    /*
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
 */

    
}
