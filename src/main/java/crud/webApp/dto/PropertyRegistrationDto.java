package crud.webApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyRegistrationDto {

    // Basic Info
    private String title;
    private String propertyType;
    private String description;
    private BigDecimal rentAmount;
    private BigDecimal depositAmount;

    // Location
    private String district;
    private String cityArea;
    private String address;

    // Rooms & Details
    private Integer bedrooms;
    private Double bathrooms;
    private String furnishing;
    private LocalDate availableFrom;

    // Amenities (checkboxes → List)
    private List<String> amenities = new ArrayList<>();

    // Tenant Preferences
    private String preferredTenants;
    private Boolean petsAllowed = false;
    private Boolean smokingAllowed = false;

    // Dynamic Custom Fields
    private List<String> customKeys = new ArrayList<>();
    private List<String> customValues = new ArrayList<>();

    // Images
    private MultipartFile[] images;

}
