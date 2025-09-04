package com.excelr.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.excelr.entity.CitizenPlan;
import com.excelr.repository.CitizenPlanRepo;
import com.excelr.request.SearchRequest;
import com.excelr.util.EmailUtil;
import com.excelr.util.ExcelGenerator;
import com.excelr.util.PdfGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private CitizenPlanRepo planRepo;

    @Autowired
    private ExcelGenerator excelGenerator;

    @Autowired
    private PdfGenerator pdfGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public List<String> getPlanNames() {
        return planRepo.getPlanNames();
    }

    @Override
    public List<String> getPlanStatus() {
        return planRepo.getPlanStatus();
    }

    @Override
    public List<CitizenPlan> search(SearchRequest request) {
        CitizenPlan entity = new CitizenPlan();

        if (request.getPlanName() != null && !request.getPlanName().isEmpty()) {
            entity.setPlanName(request.getPlanName());
        }
        if (request.getPlanStatus() != null && !request.getPlanStatus().isEmpty()) {
            entity.setPlanStatus(request.getPlanStatus());
        }
        if (request.getGender() != null && !request.getGender().isEmpty()) {
            entity.setGender(request.getGender());
        }

        List<CitizenPlan> plans = planRepo.findAll(Example.of(entity));

        // handle date range manually
        if (request.getStartDate() != null) {
            plans = plans.stream()
                         .filter(p -> !p.getPlanStartDate().isBefore(request.getStartDate()))
                         .toList();
        }
        if (request.getEndDate() != null) {
            plans = plans.stream()
                         .filter(p -> !p.getPlanEndDate().isAfter(request.getEndDate()))
                         .toList();
        }

        return plans;
    }

    @Override
    public boolean exportExcel(HttpServletResponse response) throws Exception {
        File f = new File("Plans.xls");

        List<CitizenPlan> plans = planRepo.findAll();
        excelGenerator.generate(response, plans, f);

        String subject = "Test mail subject";
        String body = "<h1>Test mail body </h1>";
        String to = "prathameshkulkarni47@gmail.com";

        emailUtil.sendEmail(subject, body, to, f);

        f.delete();
        return true;
    }

    @Override
    public boolean exportPdf(HttpServletResponse response) throws Exception {
        File f = new File("Plans.pdf");

        List<CitizenPlan> plans = planRepo.findAll();
        pdfGenerator.generate(response, plans, f);

        String subject = "Test mail subject";
        String body = "<h1>Test mail body </h1>";
        String to = "bhavadeeshkondeti34@gmail.com";

        emailUtil.sendEmail(subject, body, to, f);

        f.delete();
        return true;
    }
}
