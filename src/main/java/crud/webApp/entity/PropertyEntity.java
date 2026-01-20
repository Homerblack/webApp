package crud.webApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@Table(name = "property_info")
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    //This one is form the session
    @Column(name = "user_id", nullable = false)
    private Long userId;

    private String title;
    private String description;

    private String propertyType;

    private BigDecimal rentAmount;
    private String currency;

    private String district;
    private String cityArea;
    private String address;

    private Integer bedrooms;
    private Integer bathrooms;

    private String furnishing;

    // Availability
    @Column(name = "available_from")
    private LocalDate availableFrom;

    @Column(name = "is_available")
    private boolean isAvailable = true;

    private BigDecimal depositAmount;

    // Amenities (stored as comma-separated or Set)
    @ElementCollection
    @CollectionTable(name = "property_amenities", joinColumns = @JoinColumn(name = "property_id"))
    @Column(name = "amenity")
    private Set<String> amenities = new HashSet<>();

    // Tenant Preferences
    @Column(name = "preferred_tenants")
    private String preferredTenants;

    private Boolean petsAllowed;
    private Boolean smokingAllowed;


    // I have no idea how element collection works and collection table works and how join column works
    @ElementCollection
    @CollectionTable(
            name = "property_custom_fields",  // Separate table for customs
            joinColumns = @JoinColumn(name = "property_id")
    )
    @MapKeyColumn(name = "field_key")     // Column for keys
    @Column(name = "field_value")         // Column for values (adjust length if needed, e.g., @Column(length = 1024))
    private Map<String, String> customFields = new LinkedHashMap<>();

    // Timestamps
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Images (One-to-Many relationship)
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyImageEntity> images = new ArrayList<>();


}
