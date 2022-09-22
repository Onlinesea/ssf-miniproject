package vttp.ssf.motivationquote.miniproject2.model;

import java.beans.JavaBean;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import vttp.ssf.motivationquote.miniproject2.redis.RedisService;

@Component
public class Journal implements Serializable {

    private String User;
    private static List<Quote> entryList = new LinkedList<>();
    public static Quote currentEntry = new Quote();
    @Autowired
    RedisService svc; 

    public Journal(){

    }
    public Journal(String User){
        this.User= User; 
    }

    public String getUser() {
        return User;
    }
    public void setUser(String user) {
        User = user;
    }
    
    public List<Quote> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Quote> entryList) {
        this.entryList = entryList;
    }

    public static Quote getCurrentEntry() {
        return currentEntry;
    }

    public static void setCurrentEntry(Quote currentEntry) {
        Journal.currentEntry = currentEntry;
    }

    public static void addQuote(Quote quote){
            
       entryList.add(quote);
    }
    
}
