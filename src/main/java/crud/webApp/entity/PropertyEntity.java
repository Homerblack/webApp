package crud.webApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "property_info")
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long propertyId ;

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

    // Custom Extra Fields (user-defined key-value pairs)
    //@Column(columnDefinition = "JSON", nullable = true)
    //private String extraFields;

    // Timestamps
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Images (One-to-Many relationship)
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyImageEntity> images = new ArrayList<>();


}
