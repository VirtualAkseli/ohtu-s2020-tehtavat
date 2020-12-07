package statistics;

import statistics.matcher.All;
import statistics.matcher.Matcher;
import statistics.Player;


public class QueryBuilder {
    Matcher query;

    public QueryBuilder() {
        query = new Query();
    }
    
    public Query query() {
        return query;
    }

    public Matcher build() {
        this.query = new All();
        return this.query;
    }
}