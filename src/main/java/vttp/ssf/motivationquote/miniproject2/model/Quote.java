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

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.ssf.motivationquote.miniproject2.redis.RedisService;


public class Quote implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Quote.class);

    private String id;
    private String message;
    private String author;
    private String thoughts;
    private String date;  
    private String feelings;

    public Quote(){
        this.id = this.generateId(8);
    }
    private synchronized String generateId(int numchars) {
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while (strBuilder.length() < numchars) {
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString().substring(0, numchars);
    }

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

        logger.info("quote created******************************"); //Quote created is okay 

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
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    
}
