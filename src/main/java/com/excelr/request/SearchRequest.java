package com.excelr.request;

import lombok.Data;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class SearchRequest {

    private String planName;
    private String planStatus;
    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
