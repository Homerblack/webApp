package crud.webApp.repository;


import crud.webApp.entity.PropertyImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyImageInfoRepository extends JpaRepository<PropertyImageEntity, Long> {

}
