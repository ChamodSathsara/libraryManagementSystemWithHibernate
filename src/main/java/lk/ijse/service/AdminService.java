package lk.ijse.service;

import lk.ijse.dto.AdminDto;

public interface AdminService {
    boolean saveAdmin(AdminDto adminDto);

    boolean updateAdmin(AdminDto adminDto);

    AdminDto getAdmin(String userName);
}
