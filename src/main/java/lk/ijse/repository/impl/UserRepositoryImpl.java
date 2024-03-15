package lk.ijse.repository.impl;

import lk.ijse.entity.User;
import lk.ijse.repository.UserRepository;
import org.hibernate.Session;

public class UserRepositoryImpl implements UserRepository {
    private static UserRepository userRepository;

    private Session session;

    public static UserRepository getInstance(){
        return userRepository == null ? userRepository = new UserRepositoryImpl() : userRepository;
    }
    @Override
    public String save(User user) {
        session.save(user);
        return "Oya hari lassanay lamayo Aththatama ";
    }

    @Override
    public void update(User user) {

    }

    @Override
    public User get(String s) {
       return session.get(User.class,s);
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

}
