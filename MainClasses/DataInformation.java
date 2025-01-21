public class DataInformation{
    private String location;
    private String distance;
    private String stroke;
    private String relay;
    private String gender;
    private String team;
    private String athleteName;
    private double result;
    private int rank;
    private int year;

    public String getStroke() {
        return stroke;
    }
    public void setStroke(String stroke) {
        this.stroke = stroke;
    }
    public String getAthleteName() {
        return athleteName;
    }
    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getDistance() {
        return distance;
    }
    public void setDistance(String distance) {
        this.distance = distance;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public String getRelay() {
        return relay;
    }
    public double getResult() {
        return result;
    }
    public void setResult(double result) {
        this.result = result;
    }
    public void setRelay(String relay) {
        this.relay = relay;
    }
    @Override
    public String toString() {
        return String.format(
                "Location: %s, Year: %d, Distance: %s, Stroke: %s, Relay: %s, Gender: %s, Team: %s, Athlete Name: %s, Result (in seconds): %.2f, Rank: %d",
                location, year, distance, stroke, relay, gender, team, athleteName, result, rank
        );
    }
}