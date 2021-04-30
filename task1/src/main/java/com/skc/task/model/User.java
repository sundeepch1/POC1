package com.skc.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="first_name")
    @NotEmpty(message = "First name is required.")
    private String firstName;
    @NotEmpty(message = "Surname is required.")
    @Column(name="sur_name")
    private String surName;
    @NotEmpty(message = "Date of birth is required.")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date should be in 'yyyy-MM-dd' format.")
    @Column(name="date_of_birth")
    private String dateOfBirth;
    @NotEmpty(message="Email account is required.")
    @Column(name="email_account", unique=true)
    @Email(message = "This is invalid email account.")
    private String emailAccount;
    @NotEmpty(message = "Phone number is required.")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Phone number should be 10 digits.")
    @Column(name="phone_number")
    private String phoneNumber;
    @NotEmpty(message = "Joining date is required.")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date should be in 'yyyy-MM-dd' format.")
    @Column(name="joining_date")
    private String joiningDate;
    @NotNull(message = "Pin code is required.")
    @Column(name="pin_code")
    private int pinCode;

    @Getter
    private boolean status;
    @NotNull(message = "Last company package is required.")
    @Column(name="last_company_package")
    private float lastCompanyPackage;
    @NotEmpty(message = "Last company name is required.")
    @Column(name="last_company_name")
    private String lastCompanyName;
    @NotNull(message="Current company package is required.")
    @Column(name="current_company_package")
    private float currentCompanyPackage;
    @NotEmpty(message = "Current company name is required.")
    @Column(name="current_company_name")
    private String currentCompanyName;
    @NotNull(message = "Relevant experience is required.")
    @Column(name="relevant_experience")
    private float relevantExperience;
    @NotNull(message = "Total experience is required.")
    @Column(name="total_experience")
    private float totalExperience;

    private String role;
    @Getter(onMethod = @__( @JsonIgnore))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
