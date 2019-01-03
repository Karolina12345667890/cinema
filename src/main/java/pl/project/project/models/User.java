package pl.project.project.models;

import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import pl.project.project.validators.UniqueUsername;
import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //@UniqueUsername
    @Length(min = 2, max = 15)
    private String username;
    private String password;
    private int acount;
    @Transient//pole nie będzie odwzorowane w db
    private String passwordConfirm;
    private boolean enabled = false;

//    public User(@Length(min = 2, max = 15) String name, @Length(min = 2, max = 10) String password, int acount, boolean enabled) {
//        this.username = name;
//        this.password = password;
//        this.acount = acount;
//        this.enabled = enabled;
//    }
@AssertTrue
private boolean isPasswordsEquals(){
    return password == null || passwordConfirm == null || password.equals(passwordConfirm);
}
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(String username){
        this(username, false);
    }

    public User(String username, boolean enabled){
        this.username = username;
        this.enabled = enabled;
    }


//    public User() {
//    }


    public int getAcount() {
        return acount;
    }

    public void setAcount(int acount) {
        this.acount = acount;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public int getAcount() {
//        return acount;
//    }
//
//    public void setAcount(int acount) {
//        this.acount = acount;
//    }
}
