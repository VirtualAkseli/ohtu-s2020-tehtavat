package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
          
        QueryBuilder query = new QueryBuilder();
        Matcher m = query.build();

        
        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }

    }
}
