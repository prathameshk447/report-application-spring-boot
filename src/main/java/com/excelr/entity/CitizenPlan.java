package com.excelr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "CITIZEN_PLANS_INFO_NEW")
public class CitizenPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer citizenId;

    private String citizenName;
    private String gender;
    private String planName;
    private String planStatus;

    private LocalDate planStartDate;
    private LocalDate planEndDate;

    private Double benefitAmt;
    private String denialReason;

    private LocalDate terminatedDate;
    private String terminationRsn;
}
