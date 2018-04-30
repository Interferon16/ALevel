package entitys;

public class User extends Entity {

    public User(){
        super();
    }

    public User(int id, String name,String email,String pass){
        super(id);
        this.name=name;
        this.email=email;
        this.pass=pass;
    }

    private String name;
    private String email;
    private String pass;


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    @Override
    public String toString() {
        return "user{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
