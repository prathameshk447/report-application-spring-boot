package com.excelr.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.excelr.entity.CitizenPlan;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {

    public void generate(HttpServletResponse response, List<CitizenPlan> plans, File f) throws Exception {

        Document document = new Document(PageSize.A4);

        
        PdfWriter.getInstance(document, response.getOutputStream());
        PdfWriter.getInstance(document, new FileOutputStream(f));

        document.open();

        
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);

      
        Paragraph p = new Paragraph("Citizen plan info", fontTitle);

       
        p.setAlignment(Paragraph.ALIGN_CENTER);

       
        document.add(p);

      
        PdfPTable table = new PdfPTable(6); 
        table.setSpacingBefore(5); 

       
        table.addCell("Id");
        table.addCell("Citizen Name");
        table.addCell("Plan Name");
        table.addCell("Plan Status");
        table.addCell("Start Date");
        table.addCell("End Date");

       
        for (CitizenPlan plan : plans) {
            table.addCell(String.valueOf(plan.getCitizenId()));
            table.addCell(plan.getCitizenName());
            table.addCell(plan.getPlanName());
            table.addCell(plan.getPlanStatus());
            table.addCell(plan.getPlanStartDate() + ""); 
            table.addCell(plan.getPlanEndDate() + "");
        }

       
        document.add(table);

     
        document.close();
    }
}
