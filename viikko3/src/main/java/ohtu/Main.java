package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import java.util.Date;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
        Date instant = new Date();
        //datan muoto: {"name":"Elvis Merzlikins","nationality":"LVA","assists":0,"goals":0,"penalties":0,"team":"CBJ","games":33}

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        
        System.out.println("Players from FIN " + instant + "\n");
        
        Arrays.sort(players, (Player a, Player b) -> Integer.compare(b.getPoints(), a.getPoints()));
        

        for (Player player : players) {

            
            if (player.getNationality().equals("FIN")) {
                System.out.println(player);
            }
            
        }   
    }
  
}

