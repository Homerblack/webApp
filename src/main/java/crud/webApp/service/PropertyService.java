package crud.webApp.service;

import crud.webApp.dto.PropertyRegistrationDto;
import crud.webApp.entity.PropertyEntity;
import crud.webApp.entity.PropertyImageEntity;
import crud.webApp.repository.PropertyImageInfoRepository;
import crud.webApp.repository.PropertyInfoRepository;
import crud.webApp.storage.ImageStorageService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class PropertyService {


    /// property info registration
    private final PropertyInfoRepository propertyInfoRepository;

    /// property info image registration
    private final PropertyImageInfoRepository propertyImageInfoRepository;

    private final ImageStorageService imageStorageService;

    public PropertyService(PropertyInfoRepository propertyInfoRepository,
                           PropertyImageInfoRepository propertyImageInfoRepository,
                           ImageStorageService imageStorageService) {
        this.propertyInfoRepository = propertyInfoRepository;
        this.propertyImageInfoRepository = propertyImageInfoRepository;
        this.imageStorageService = imageStorageService;
    }


    /// inserting property info
    @Transactional
    public PropertyEntity saveProperty(PropertyRegistrationDto dto, Long userId) {
        PropertyEntity property = new PropertyEntity();
        //System.out.println("Before any setters - isNew? " + propertyInfoRepository.isNew(property));
        property.setUserId(userId);
        property.setTitle(dto.getTitle());
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

        // Custom dynamic fields – convert parallel lists → Map

        //Map the custom key
        Map<String, String> customMap = new LinkedHashMap<>();
        if (dto.getCustomKeys() != null && dto.getCustomValues() != null) {
            int minSize = Math.min(dto.getCustomKeys().size(), dto.getCustomValues().size());
            for (int i = 0; i < minSize; i++) {
                String key = dto.getCustomKeys().get(i);
                String value = dto.getCustomValues().get(i);

                if (key != null && !key.trim().isEmpty()) {
                    String trimmedKey = key.trim();
                    if (trimmedKey.length() > 100) {
                        trimmedKey = trimmedKey.substring(0, 100);
                    }
                    String safeValue = (value != null) ? value.trim() : "";
                    if (safeValue.length() > 500) {
                        safeValue = safeValue.substring(0, 500);
                    }
                    customMap.put(trimmedKey, safeValue);
                }

            }
        }
        property.setCustomFields(customMap);

        PropertyEntity savedProperty = propertyInfoRepository.save(property);


        //this is all about image
        if (dto.getImages() != null && dto.getImages().length > 0) {
            for (MultipartFile file : dto.getImages()) {
                if (!file.isEmpty()) {
                    PropertyImageEntity image = null;
                    try {
                        String urlOrPlaceholder = imageStorageService.storeImage(file);
                        image = new PropertyImageEntity();
                        image.setFileName(file.getOriginalFilename());
                        image.setFilePath(urlOrPlaceholder);  // ← Now holds Google Drive URL or placeholder
                        // image.setIsPrimary(...);  // you can add logic later
                        image.setProperty(savedProperty);

                        propertyImageInfoRepository.save(image);
                    } catch (IOException e) {
                        // Log it, maybe throw or continue
                        System.err.println("Image upload failed: " + e.getMessage());
                    }
                    image.setProperty(savedProperty);

                    propertyImageInfoRepository.save(image);
                }

            }


        }

        return savedProperty;
    }

    /*
    //TODO
    //This is to update property info todo
    @Transactional
    public PropertyEntity updateProperty(PropertyRegistrationDto dto, Long userId) {
        PropertyEntity property = new PropertyEntity();
        System.out.println("ID before update: " + property.getPropertyId());
        return propertyInfoRepository.findById(property.getPropertyId()).orElse(null);
    }
     */

    // Get all the items registered by some user
    //get all the active users
    public List<PropertyEntity> getALlRegisteredProperty(long userId ) {
        List<PropertyEntity> properties = propertyInfoRepository.findByUserId(userId);
        System.out.println("DB returned " + properties.size() + " properties for user " + userId);
        return properties;
    }



}

