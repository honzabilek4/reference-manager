package cz.fi.muni.pa165.referenceManager.service;

import cz.muni.fi.pa165.referenceManager.dao.UserDao;
import cz.muni.fi.pa165.referenceManager.entity.User;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private ConfigurablePasswordEncryptor passwordEncryptor;


    public User findUserById(Long id) {
        return userDao.findById(id);
    }

    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    public void registerUser(User user, String plainPassword) {
        user.setPasswordHash(createPasswordHash(plainPassword));
        userDao.create(user);
    }

    public Collection<User> getAllUsers() {
        return userDao.findAll();
    }

    public boolean authenticate(User user, String password) {
        return verifyPassword(user.getPasswordHash(),password);
    }

    private boolean verifyPassword(String password, String correctPasswordHash){
        if(password == null){
            return false;
        }
        if(correctPasswordHash == null){
            throw new IllegalArgumentException("Password hash cannot be null.");
        }
        return passwordEncryptor.checkPassword(correctPasswordHash,password);


    }
    private String createPasswordHash(String password){
        return passwordEncryptor.encryptPassword(password);
    }
}
