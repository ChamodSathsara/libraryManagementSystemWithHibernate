package lk.ijse.dto;

import lk.ijse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@AllArgsConstructor
@NoArgsConstructor@Data
public class UserDto {
    private String userName;
    private String eMail;
    private String password;

    public User toEntity(){
        User user = new User();
        user.setUserName(this.userName);
        user.setEMail(this.eMail);
        user.setPassword(this.password);

        return user;
    }
}
