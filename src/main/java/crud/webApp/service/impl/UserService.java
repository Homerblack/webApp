package crud.webApp.service.impl;

import crud.webApp.entity.UserInfoEntity;
import crud.webApp.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
   private final UserInfoRepository userInfoRepository;

   public UserService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
   }

    //get all the active users
    public List<UserInfoEntity> getActiveUsers() {
        return userInfoRepository.findByIsActive(true);
    }


    //get all the active users
    public UserInfoEntity saveUser(UserInfoEntity  user) {
       return userInfoRepository.save(user);
    }

    //get all the active users
    public List<UserInfoEntity> getAllUsers() {
        return userInfoRepository.findAll();
    }

}
