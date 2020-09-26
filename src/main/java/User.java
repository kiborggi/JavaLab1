public class User {
    private int id;
    private String name;
    private String pass;
    private String role;




    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getRole() {
        return role;
    }

    public User(int id, String name, String pass, String role) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String name, String pass, String role) {
        this.name = name;
        this.pass = pass;
        this.role = role;
    }
    public User (){}

    public String toString(){
        return this.id + " " + this.name + " " + this.pass + " " + this.role ;
    }
}
