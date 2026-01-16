package crud.webApp.service;

import crud.webApp.dto.PropertyRegistrationDto;
import crud.webApp.entity.PropertyEntity;
import crud.webApp.entity.PropertyImageEntity;
import crud.webApp.repository.PropertyImageInfoRepository;
import crud.webApp.repository.PropertyInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;

@Service
public class PropertyService {



    /// property info registration
    private final PropertyInfoRepository propertyInfoRepository;

    /// property info image registration
    private final PropertyImageInfoRepository propertyImageInfoRepository;

    public PropertyService(PropertyInfoRepository propertyInfoRepository, PropertyImageInfoRepository propertyImageInfoRepository) {
        this.propertyInfoRepository = propertyInfoRepository;
        this.propertyImageInfoRepository = propertyImageInfoRepository;
    }


    /// inserting property info
    @Transactional
    public PropertyEntity saveProperty(PropertyRegistrationDto dto, Long userId) {
        PropertyEntity property = new PropertyEntity();
        //System.out.println("Before any setters - isNew? " + propertyInfoRepository.isNew(property));
        System.out.println("ID before save: " + property.getPropertyId());
        property.setUserId(userId);
        property.setTitle(dto.getTitle());
        property.setDescription(dto.getDescription());
        property.setPropertyType(dto.getPropertyType());
        property.setDescription(dto.getDescription());
        property.setPropertyType(dto.getPropertyType());
        property.setRentAmount(dto.getRentAmount());
        property.setCurrency("NPR");
        property.setDistrict(dto.getDistrict());
        property.setCityArea(dto.getCityArea());
        property.setAddress(dto.getAddress());
        property.setBedrooms(dto.getBedrooms());
        property.setBathrooms(dto.getBathrooms() != null ? dto.getBathrooms().intValue() : null);
        property.setFurnishing(dto.getFurnishing());
        property.setAvailableFrom(dto.getAvailableFrom());
        property.setDepositAmount(dto.getDepositAmount());
        property.setAmenities(new HashSet<>(dto.getAmenities()));
        property.setPreferredTenants(dto.getPreferredTenants());
        property.setPetsAllowed(dto.getPetsAllowed());
        property.setSmokingAllowed(dto.getSmokingAllowed());
        // Custom fields (key-value pairs)
        //property.setExtraFields("{}");



        PropertyEntity savedProperty = propertyInfoRepository.save(property);


        //this is all about image
        if (dto.getImages() != null) {
            for (MultipartFile file : dto.getImages()) {
                String filePath = "/uploads/" + file.getOriginalFilename();
                PropertyImageEntity image = new PropertyImageEntity();
                image.setFileName(file.getOriginalFilename());
                image.setFilePath(filePath);
                image.setProperty(savedProperty);

                propertyImageInfoRepository.save(image);
            }
            return savedProperty;
        }

        return  propertyInfoRepository.save(property);
    }




}
