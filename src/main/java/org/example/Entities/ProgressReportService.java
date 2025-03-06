package org.example.Dao;

import jakarta.persistence.OptimisticLockException;
import org.example.Entities.ProgressReport;
import org.example.ProgressReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProgressReportService {

    private final ProgressReportRepository progressReportRepository;

    public ProgressReportService(ProgressReportRepository progressReportRepository) {
        this.progressReportRepository = progressReportRepository;
    }

    @Transactional
    public ProgressReport updateProgressReport(ProgressReport updatedReport) {
        try {
            return progressReportRepository.save(updatedReport);
        } catch (OptimisticLockException e) {
            throw new RuntimeException("Update failed due to concurrent modifications. Please reload and try again.", e);
        }
    }
}
