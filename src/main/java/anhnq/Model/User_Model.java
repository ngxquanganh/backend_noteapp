package anhnq.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;

@Entity
@Table(name = "User")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "pwd", nullable = false)
    private String pwd;

    @Column(name = "gender", nullable = false)
    private int gender;

    @Column(name = "fullname")
    private String full_name;

    public User_Model(String name, String pwd, int gender, String full_name) {
        this.name = name;
        this.pwd = pwd;
        this.gender = gender;
        this.full_name = full_name;
    }


}