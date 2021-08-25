package hello_world.portlet;
import lombok.Data;
@Data
public class Users {
    private String firstName;
    private String lastName;
    private String email;

    public Users(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
