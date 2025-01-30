package Repositories;

import Model.Report;
import java.util.List;

public interface ReportRepository {
    void createReport(Report report);
    List<Report> getReportsByPatient(int patientId);
}