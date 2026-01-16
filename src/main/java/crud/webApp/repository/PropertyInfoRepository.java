package crud.webApp.repository;

import crud.webApp.entity.PropertyEntity;
import crud.webApp.entity.RoomUserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyInfoRepository extends JpaRepository<PropertyEntity, Long> {


}
