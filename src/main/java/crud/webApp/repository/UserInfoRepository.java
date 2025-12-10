package crud.webApp.repository;

import crud.webApp.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository  extends JpaRepository<UserInfoEntity, Long> {

}
