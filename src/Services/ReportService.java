package Services;

import Model.Report;

import Repositories.ReportRepository;
import java.util.List;

public class ReportService {
    private ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void createReport(Report report) {
        reportRepository.createReport(report);
    }
}
