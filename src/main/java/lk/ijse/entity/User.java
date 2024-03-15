package lk.ijse.entity;

import lk.ijse.dto.AdminDto;
import lk.ijse.dto.UserDto;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_name" , nullable = false )
    private String userName;

    @Column(name = "email")
    private String eMail;

    @Column(name = "password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY,mappedBy = "user")
    private List<Barrow> barrowList;

    public User() {
    }

    public User(String userName, String eMail, String password) {
        this.userName = userName;
        this.eMail = eMail;
        this.password = password;
    }

    public UserDto toDto(){
        UserDto user = new UserDto();
        user.setUserName(this.userName);
        user.setEMail(this.eMail);
        user.setPassword(this.password);

        return user;
    }
}
