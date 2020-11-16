
package ohtu;

public class Player {
    private String name;
    private String team;
    private String nationality;
    private int goals;
    private int assists;
 

    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(String name) {
        this.team = team;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public String getNationality() {
        return nationality;
    }

    public int getPoints() {
        return goals + assists;
    }

    @Override
    public String toString() {
        return  name  + " team " + team + " " + goals + " + " + assists + " = " + getPoints();
    }
      
}
