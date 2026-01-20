package crud.webApp.service;

import crud.webApp.entity.UserInfoEntity;
import crud.webApp.repository.UserInfoRepository;
import crud.webApp.storage.ImageStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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


    //save all users
    public UserInfoEntity saveUser(UserInfoEntity  user) {
       return userInfoRepository.save(user);
    }

    //get all the  users
    public List<UserInfoEntity> getAllUsers() {
        return userInfoRepository.findAll();
    }

    //get users by id
    public Optional<UserInfoEntity> getUserById(long id) {
        return userInfoRepository.findById(id);
    }

    public void  deleteById(long id) {
       userInfoRepository.deleteById(id);
    }

}
