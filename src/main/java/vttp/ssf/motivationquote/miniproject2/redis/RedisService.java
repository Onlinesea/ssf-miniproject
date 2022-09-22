package vttp.ssf.motivationquote.miniproject2.redis;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import vttp.ssf.motivationquote.miniproject2.model.Journal;
import vttp.ssf.motivationquote.miniproject2.model.Quote;

@Service
public class RedisService implements RedisRepo {
    
    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    @Qualifier("Entry")
    RedisTemplate<String, Journal> redisTemplate;

    @Override
    public void save(final Journal journal){
        redisTemplate.opsForValue().set(journal.getUser(), journal); // the problem is this 
        //Quote result = (Quote)redisTemplate.opsForValue().get(quote.getId());

    }

    @Override
    public Journal findById(String UserId) {
        logger.info("find journal by id> " + UserId);
        Journal result = (Journal) redisTemplate.opsForValue().get(UserId);
        logger.info("journal return" + result);
        return result;
    }

    @Override
    public int update(Journal journal) {
        logger.info("Save mastermind > " + logger);
        redisTemplate.opsForValue().setIfPresent(journal.getUser(), journal);
        Journal result = (Journal) redisTemplate.opsForValue().get(journal.getUser());

        //This is to check whether the json is uploaded successfully 
        if (result != null)
            return 1;
        return 0;
    }
}
