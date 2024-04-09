package comx.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Component
@Scope("prototype")
@Table(name = "users")
public class Login {

    @Id
    @Column(name = "email")
    private String username;

    @Column(name = "pass")
    private String password;

    @Column(name = "admin")
    private Boolean role;

    // Getters and setters
    // Constructors

    // Note: You can use boolean for the 'admin' field if it's represented as a tinyint(1) in the database.
    // If it's stored as int in the database, you might need to use Integer or Boolean type instead.

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

    public Boolean getRole() {
        return role != null;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }
}