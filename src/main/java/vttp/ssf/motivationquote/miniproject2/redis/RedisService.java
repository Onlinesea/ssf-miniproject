package vttp.ssf.motivationquote.miniproject2.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import vttp.ssf.motivationquote.miniproject2.model.Quote;

@Service
public class RedisService implements RedisRepo {
    
    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Override
    public int save(final Quote quote){
        logger.info("quote inside redisservice >>>>>" + quote.getId());
        logger.info("quote inside redisservice >>>>>" + quote.getMessage());


        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.opsForValue().set(quote.getId(), (Quote)quote); // the problem is this 
        logger.info("Something not wrong with this");

        Quote result = (Quote)redisTemplate.opsForValue().get(quote.getId());
        
        if(result != null){
            logger.info("result >>>>>>>>>>>>>>>>>> 1 ");

            return 1;
        }
        logger.info("result >>>>>>>>>>>>>>>>>> 0 ");

        return 0;

    }

    @Override
    public Quote findById(String UserId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int update(String UserId) {
        // TODO Auto-generated method stub
        return 0;
    }
}
