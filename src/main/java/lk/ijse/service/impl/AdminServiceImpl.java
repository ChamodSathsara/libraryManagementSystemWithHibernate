package lk.ijse.service.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dto.AdminDto;
import lk.ijse.entity.Admin;
import lk.ijse.repository.AdminRepository;
import lk.ijse.repository.impl.AdminRepositoryImpl;
import lk.ijse.service.AdminService;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AdminServiceImpl implements AdminService {
    private static AdminService adminService;
    private Session session ;

    private final AdminRepository adminRepository;

    private AdminServiceImpl(){
        adminRepository = AdminRepositoryImpl.getInstance();
    }

    public static AdminService getInstance(){
        return adminService == null ? adminService = new AdminServiceImpl() : adminService;
    }
    @Override
    public boolean saveAdmin(AdminDto adminDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            adminRepository.setSession(session);
            String isSave = adminRepository.save(adminDto.toEntity());
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean updateAdmin(AdminDto adminDto) {
        return false;
    }

    @Override
    public AdminDto getAdmin(String userName) {
        session = SessionFactoryConfig.getInstance().getSession();

        try{
            adminRepository.setSession(session);
            Admin admin = adminRepository.get(userName);
            return admin.toDto();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }
}
