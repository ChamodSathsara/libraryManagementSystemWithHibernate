package lk.ijse.entity;

import lk.ijse.dto.AdminDto;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "user_name" , nullable = false )
    private String userName;

    @Column(name = "email", nullable = false )
    private String eMail;

    @Column(name = "password", nullable = false )
    private String password;

    public Admin() {
    }

    public Admin(String userName, String eMail, String password) {
        this.userName = userName;
        this.eMail = eMail;
        this.password = password;
    }

    public AdminDto toDto(){
        AdminDto user = new AdminDto();
        user.setUserName(this.userName);
        user.setEMail(this.eMail);
        user.setPassword(this.password);

        return user;
    }
    
}
