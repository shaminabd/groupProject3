package Controllers;

import Model.Doctor;
import Model.Report;
import Services.ReportService;

public class ReportController {
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    public void createReport(Report report) {
        reportService.createReport(report);
    }

    public void removeReport(int reportId) {
    }

    public void viewReportsForDoctor(Doctor loggedInDoctor) {
    }

    public void viewReportsForPatient() {
    }
}