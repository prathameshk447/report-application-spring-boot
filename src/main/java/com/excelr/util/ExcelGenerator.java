package com.excelr.util;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import com.excelr.entity.CitizenPlan;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Component
public class ExcelGenerator {

    public void generate(HttpServletResponse response, List<CitizenPlan> records, File file) throws Exception {

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("plans-data");

        
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Id");
        headerRow.createCell(1).setCellValue("Citizen Name");
        headerRow.createCell(2).setCellValue("Plan Name");
        headerRow.createCell(3).setCellValue("Plan Status");
        headerRow.createCell(4).setCellValue("Plan Start Date");
        headerRow.createCell(5).setCellValue("Plan End Date");
        headerRow.createCell(6).setCellValue("Benefit Amt");

        
        int dataRowIndex = 1;
        for (CitizenPlan plan : records) {
            Row dataRow = sheet.createRow(dataRowIndex);

            dataRow.createCell(0).setCellValue(plan.getCitizenId());
            dataRow.createCell(1).setCellValue(plan.getCitizenName());
            dataRow.createCell(2).setCellValue(plan.getPlanName());
            dataRow.createCell(3).setCellValue(plan.getPlanStatus());

            
            if (plan.getPlanStartDate() != null) {
                dataRow.createCell(4).setCellValue(plan.getPlanStartDate().toString());
            } else {
                dataRow.createCell(4).setCellValue("N/A");
            }

            
            if (plan.getPlanEndDate() != null) {
                dataRow.createCell(5).setCellValue(plan.getPlanEndDate().toString());
            } else {
                dataRow.createCell(5).setCellValue("N/A");
            }

            
            if (plan.getBenefitAmt() != null) {
                dataRow.createCell(6).setCellValue(plan.getBenefitAmt().toString());
            } else {
                dataRow.createCell(6).setCellValue("N/A");
            }

            dataRowIndex++;
        }

        
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();

        
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
    }
}
