package vttp.ssf.motivationquote.miniproject2.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;


public class Quote implements Serializable {
    private String message;
    private String author;
    private String thoughts;
    private String date; 
    private String feelings;

    public static Quote create(String json) throws IOException{

        Quote quote = new Quote();

        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);

            JsonObject o = r.readObject();

            o.getJsonArray(""); 



            
        }

        return quote;
    }

}
