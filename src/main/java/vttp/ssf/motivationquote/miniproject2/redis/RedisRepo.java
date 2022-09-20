package vttp.ssf.motivationquote.miniproject2.redis;

import vttp.ssf.motivationquote.miniproject2.model.Journal;
import vttp.ssf.motivationquote.miniproject2.model.Quote;

public interface RedisRepo {
    public void save (final Journal journal);

    public Journal findById(final String UserId);

    public int update(final Journal journal);
    
}
