package efrei.carrental.model.jpa;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "applicationuser", schema = "public", catalog = "postgres")
public class ApplicationuserJpa {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "username", nullable = false, length = 255)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Basic
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Basic
    @Column(name = "user_type", nullable = false, length = 10)
    private String userType;

    @ElementCollection
    @CollectionTable(
            name = "user_cart",
            joinColumns=@JoinColumn(name = "user_id", referencedColumnName = "id"
            )
    )
    private List<RentalJpa> cart = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationuserJpa that = (ApplicationuserJpa) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(userType, that.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, userType);
    }
}
