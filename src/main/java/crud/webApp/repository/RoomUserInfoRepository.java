package crud.webApp.repository;


import crud.webApp.entity.RoomUserInfoEntity;
import crud.webApp.entity.UserIdPasswordEntity;
import crud.webApp.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomUserInfoRepository extends JpaRepository<RoomUserInfoEntity, Long> {


    /// To get all The Users By id
    List<UserInfoEntity> id(Long id);


    /// To get all The Users By id
    Optional<RoomUserInfoEntity> findAllById(long id);


    /// To get The max ID from the roomUser
    @Query("SELECT COALESCE(MAX(r.id), 0) FROM RoomUserInfoEntity r")
    Long getMaxId();


    /// To Check the User input is registered in Db or not
    Optional<RoomUserInfoEntity> findAllByEmailAndPassword(String email, String password);

}
