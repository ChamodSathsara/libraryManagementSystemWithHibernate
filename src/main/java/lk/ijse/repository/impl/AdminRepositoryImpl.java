package lk.ijse.repository.impl;

import lk.ijse.entity.Admin;
import lk.ijse.repository.AdminRepository;
import org.hibernate.Session;

public class AdminRepositoryImpl implements AdminRepository {
    private static AdminRepository adminRepository;

    private Session session;

    public static AdminRepository getInstance(){
        return adminRepository == null ? adminRepository = new AdminRepositoryImpl() : adminRepository;
    }
    @Override
    public String save(Admin admin) {

        session.save(admin);
        return " ";
    }

    @Override
    public void update(Admin admin) {

    }

    @Override
    public Admin get(String s) {
        return session.get(Admin.class,s);
    }

    @Override
    public void delete(Admin admin) {

    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
