package vttp.ssf.motivationquote.miniproject2.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.ssf.motivationquote.miniproject2.model.Journal;
import vttp.ssf.motivationquote.miniproject2.redis.RedisService;

@Service
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    
    List<String> Users = new LinkedList<>();
    
    @Autowired
    RedisService redisSvc;

    public Journal checkUser(String User){
        Journal journal = new Journal(User);
/* 
        for(String existUser:Users){
            if(User.equalsIgnoreCase(existUser)){
                journal.setEntryList(redisSvc.findById(User).getEntryList());
                return journal;
            }
        }
        Users.add(User);
        return journal;
    }
    */
        Journal loadJournal = redisSvc.findById(User);
        //logger.info("Size of the loaded journal >>>>> " + loadJournal.getEntryList().size() );
        if (loadJournal != null){
            //logger.info("loadjournal >" + loadJournal );
            return loadJournal;
        }else{

        redisSvc.save(journal);
        //logger.info("journal >" + journal);

        return journal;
        }
    }
}
