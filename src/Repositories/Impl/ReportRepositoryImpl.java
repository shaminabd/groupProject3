package Repositories.Impl;

import Model.Report;
import Repositories.ReportRepository;
import java.util.ArrayList;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository {
    private List<Report> reports = new ArrayList<>();

    @Override
    public void createReport(Report report) {
        reports.add(report);
    }

    @Override
    public List<Report> getReportsByPatient(int patientId) {
        List<Report> result = new ArrayList<>();
        for (Report report : reports) {
            if (report.getPatientId() == patientId) {
                result.add(report);
            }
        }
        return result;
    }
}
