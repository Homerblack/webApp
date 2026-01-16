package crud.webApp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users_pubilc")
public class RoomUserInfoEntity {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int age ;
    private String sex;
    private int postalCode;
    private String province;
    private String district;
    private String ward;
    private String tole;
    private String password;
    private String confirmPassword;
    private Date createdDate;
    private Date updatedDate;


    // set only once
    // also set on create
    @PrePersist
    public void onCreate() {
        createdDate = new Date();
        updatedDate = new Date();
    }


    //update on every update
    @PreUpdate
    public void onUpdate() {
        updatedDate = new Date();
    }

}
