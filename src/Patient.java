public class Patient {
    private String firstname, lastname, identifier, hasvisited, isnew, status, hasinsurance, provider;
    private boolean visited, insured, n, food;
    private int num = 0;
    public Patient(String firstname, String lastname, boolean insured, boolean visited, boolean isnew, String identifier, boolean food, String provider) {
        this.firstname = firstname;
        this.lastname = lastname;
        status = "Waiting";
        this.visited = visited; 
        this.insured = insured;
        n = isnew;
        if(visited)
            hasvisited = "Has visited in 2018";
        else
            hasvisited = "Has not visited in 2018";
        if(isnew)
            this.isnew = "N";
        else 
            this.isnew = "F";
        this.food = food;
        this.provider = provider;
        this.identifier = identifier;

    }

    public Patient(String identifier) {
        this.identifier = identifier;
    }

    public Patient(Patient temp) {
        firstname = temp.getFirstName();
        lastname = temp.getLastName();
        status = temp.getStatus();
        visited = temp.getVisited(); 
        insured = temp.getInsured();
        n = temp.isNew();
        if(visited)
            hasvisited = "Has visited in 2018";
        else
            hasvisited = "Has not visited in 2018";
        if(temp.isNew())
            isnew = "N";
        else 
            isnew = "F";
        food = temp.getFood();
        provider = temp.getProvider();
        identifier = temp.toString();

    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getName() {
        return firstname + " " + lastname;
    }

    public boolean getVisited() {
        return visited;
    }

    public boolean getInsured() {
        return insured;
    }

    public boolean isNew() {
        return n;
    }

    public boolean getFood(){
        return food;
    }

    public String getStatus() {
        return status;
    }

    public String getProvider(){
        return provider;
    }

    public void update() {

    }

    public String toString() {
        return identifier;
    }

    public void setStatus(String temp) {
        status = temp;
    }

    public int getNum(){
        return num;
    }

    public void setNum(int num){
        this.num = num;
    }

    public void editData(Patient temp) {
        firstname = temp.getFirstName();
        lastname = temp.getLastName();
        status = temp.getStatus();
        visited = temp.getVisited(); 
        insured = temp.getInsured();
        status = temp.getStatus();
        n = temp.isNew();
        food = temp.getFood();
        if(visited)
            hasvisited = "Has visited in 2018";
        else
            hasvisited = "Has not visited in 2018";
        if(temp.isNew())
            isnew = "N";
        else 
            isnew = "F";
        this.identifier = temp.toString();
    }
}