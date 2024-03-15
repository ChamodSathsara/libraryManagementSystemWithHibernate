package lk.ijse.service;

import lk.ijse.dto.AdminDto;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;

public interface UserService {

    boolean saveUser(UserDto userDto);
    boolean updateUser(UserDto userDto);

    UserDto getUser(String userName);

}
