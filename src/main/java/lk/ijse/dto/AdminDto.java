package lk.ijse.dto;

import lk.ijse.entity.Admin;
import lk.ijse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminDto {
    private String userName;
    private String eMail;
    private String password;

    public Admin toEntity(){
        Admin user = new Admin();
        user.setUserName(this.userName);
        user.setEMail(this.eMail);
        user.setPassword(this.password);

        return user;
    }
}
