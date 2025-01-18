public class DataInformation <T>{

    T info;
    String location;
    String distance;
    String stroke;
    String relay;
    String gender;
    String team;
    String athleteName;
    double result;
    int rank;
    int year;

    DataInformation(T info){
        this.info = info;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String backstroke) {
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

    public String toString(){
        String message;
        message = getLocation() + " " + getYear() + " " + getDistance() + " " + getStroke() + " " + getRelay() + " " +
                getGender() + " " + getTeam() + " " + getAthleteName() + " " + getResult() + " " + getRank();
        return message;
    }
}