package Services;

import Model.Report;
import Repositories.Impl.ReportRepositoryImpl;
import Model.Doctor;
import Model.Patient;
import Repositories.ReportRepository;

import java.util.List;

public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void createReport(Report report) {
        reportRepository.createReport(report);
    }

    public List<Report> getReportsByPatient(Patient patient) {
        return reportRepository.getReportsByPatient(patient.getId());
    }

    public List<Report> getReportsByDoctor(Doctor doctor) {
        return reportRepository.getReportsByDoctor(doctor.getId());
    }

    public void removeReport(int reportId) {
        reportRepository.deleteReport(reportId);
    }
}
