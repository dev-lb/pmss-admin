package jg.sso.pmss.admin.service;

import jg.sso.pmss.admin.config.DsConfig;
import jg.sso.pmss.admin.config.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private DsConfig dsConfig;

    public boolean login(String username, String password) {
        boolean suc = false;
        List<User> users = dsConfig.getUsers();
        for (User user :users){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                suc = true;
                break;
            }
        }
        return suc;
    }
}
