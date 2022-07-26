public class User {
    public String username;
    public String identifier;
    public String firstName;
    public String lastName;

    public User(String username, String identifier, String first_name, String last_name) {
        this.username = username;
        this.identifier = identifier;
        this.firstName = first_name;
        this.lastName = last_name;
    }

    public String toString(){
        return "Username : " + this.username + " Identifier : " + this.identifier + " First name : " + this.firstName +
                " Last name : " + this.lastName;
    }
}
