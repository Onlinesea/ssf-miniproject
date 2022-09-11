package vttp.ssf.motivationquote.miniproject2.redis;

import vttp.ssf.motivationquote.miniproject2.model.Quote;

public interface RedisRepo {
    public int save (final Quote quote);

    public Quote findById(final String UserId);

    public int update(final String UserId);
    
}
