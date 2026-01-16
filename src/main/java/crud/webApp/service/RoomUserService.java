package crud.webApp.service;


import crud.webApp.entity.RoomUserInfoEntity;
import crud.webApp.repository.RoomUserInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomUserService {

    private final RoomUserInfoRepository  roomUserInfoRepository;


    public RoomUserService( RoomUserInfoRepository roomUserInfoRepository) {
        this.roomUserInfoRepository = roomUserInfoRepository;

    }


    //get users by id
    public Optional<RoomUserInfoEntity> getUserById(long id) {
        return roomUserInfoRepository.findAllById(id);
    }

    /// inserting room users
    public RoomUserInfoEntity saveRoomUser(RoomUserInfoEntity roomUserInfoEntity) {
        roomUserInfoEntity.setId(roomUserInfoRepository.getMaxId()+1);
        return roomUserInfoRepository.save(roomUserInfoEntity);
    }


    public Optional<RoomUserInfoEntity> getUserById(String email , String password) {
        return roomUserInfoRepository.findAllByEmailAndPassword(email, password);
    }


}
