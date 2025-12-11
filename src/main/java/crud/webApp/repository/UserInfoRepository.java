package crud.webApp.repository;

import crud.webApp.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository  extends JpaRepository<UserInfoEntity, Long> {

    List<UserInfoEntity> findByIsActive(Boolean isActive);


    Optional<UserInfoEntity> findById(Long id);


    @Modifying
    @Query("UPDATE UserInfoEntity u SET u.isActive = false WHERE u.id = :id")
    void deleteById(@Param("id") Long id);


}
