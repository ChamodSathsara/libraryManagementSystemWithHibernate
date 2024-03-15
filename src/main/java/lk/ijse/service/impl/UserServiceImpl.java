package lk.ijse.service.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.Admin;
import lk.ijse.entity.User;
import lk.ijse.repository.UserRepository;
import lk.ijse.repository.impl.UserRepositoryImpl;
import lk.ijse.service.UserService;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserServiceImpl implements UserService {

    private static UserService userService;

    private Session session;

    private final UserRepository userRepository;

    private UserServiceImpl(){
        userRepository = UserRepositoryImpl.getInstance();
    }
    public static UserService getInstance(){
        return userService == null ? userService = new UserServiceImpl() : userService;
    }

    @Override
    public boolean saveUser(UserDto userDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            userRepository.setSession(session);
            String isSave = userRepository.save(userDto.toEntity());
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
    public boolean updateUser(UserDto userDto) {
        return false;
    }

    @Override
    public UserDto getUser(String userName) {
        session = SessionFactoryConfig.getInstance().getSession();

        try{
            userRepository.setSession(session);
            User user = userRepository.get(userName);
            return user.toDto();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }
}
