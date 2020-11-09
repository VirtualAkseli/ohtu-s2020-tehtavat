/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

/**
 *
 * @author aknu
 */
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

    Reader readerStub = new Reader() {
        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
            players.add(new Player("Malkin", "PIT", 8, 19));

            return players;
        }

    };

    Statistics stats;

    @Before
    public void setUp() {

        stats = new Statistics(readerStub);

    }
    
    @Test 
    public void playerSearchworks1() {
        
        assertEquals(null, stats.search("Nemo"));
    }
    
    @Test 
    public void playerSearchworks2() {
        
        assertEquals("Gretzky", stats.search("Gretzn ky").getName());
    }

    @Test
    public void playerPointsCorrect1() {
        assertEquals(98, stats.search("Yzerman").getPoints());
    }

    @Test
    public void teamSearchWorks1() {
        List<Player> l = stats.team("PIT");

        assertEquals(2, l.size());

    }

    @Test
    public void teamSearchWorks2() {
        List<Player> l = stats.team("NIL");

        assertEquals(0, l.size());

    }

    @Test
    public void topScorers() {
        assertEquals("Gretzky", stats.topScorers(1).get(0).getName().trim());
    }

    

}
