package com.sapo.store_management.dto.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CustomerDTO {
    private int id;

    @NotBlank(message = "Fullname cannot be empty or blank")
    private String fullname;

    @NotNull(message = "Birthday cannot be empty or blank")
    private LocalDate birthday;

    @NotBlank(message = "Phone cannot be empty or blank")
    private String phone;

    @NotBlank(message = "Gender cannot be empty or blank")
    private String gender;

    @NotNull(message = "District code cannot be null")
    @Pattern(regexp = "\\d+", message = "District code must be a number")
    private String districtCode;

    @NotNull(message = "Ward code cannot be null")
    @Pattern(regexp = "\\d+", message = "Ward code must be a number")
    private String wardCode;

    @NotBlank(message = "Address detail cannot be empty or blank")
    private String addressDetail;

    private String note;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "Fullname cannot be empty or blank") String getFullname() {
        return fullname;
    }

    public void setFullname(@NotBlank(message = "Fullname cannot be empty or blank") String fullname) {
        this.fullname = fullname;
    }

    public @NotNull(message = "Birthday cannot be empty or blank") LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(@NotNull(message = "Birthday cannot be empty or blank") LocalDate birthday) {
        this.birthday = birthday;
    }

    public @NotBlank(message = "Phone cannot be empty or blank") String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank(message = "Phone cannot be empty or blank") String phone) {
        this.phone = phone;
    }

    public @NotBlank(message = "Gender cannot be empty or blank") String getGender() {
        return gender;
    }

    public void setGender(@NotBlank(message = "Gender cannot be empty or blank") String gender) {
        this.gender = gender;
    }

    public @NotNull(message = "District code cannot be null") @Pattern(regexp = "\\d+", message = "District code must be a number") String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(@NotNull(message = "District code cannot be null") @Pattern(regexp = "\\d+", message = "District code must be a number") String districtCode) {
        this.districtCode = districtCode;
    }

    public @NotNull(message = "Ward code cannot be null") @Pattern(regexp = "\\d+", message = "Ward code must be a number") String getWardCode() {
        return wardCode;
    }

    public void setWardCode(@NotNull(message = "Ward code cannot be null") @Pattern(regexp = "\\d+", message = "Ward code must be a number") String wardCode) {
        this.wardCode = wardCode;
    }

    public @NotBlank(message = "Address detail cannot be empty or blank") String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(@NotBlank(message = "Address detail cannot be empty or blank") String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
