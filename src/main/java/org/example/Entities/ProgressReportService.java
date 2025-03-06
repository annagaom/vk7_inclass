package org.example.Entities;
import jakarta.persistence.OptimisticLockException;
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
            throw new RuntimeException("更新失败：数据已被其他用户修改，请刷新后重试！", e);
        }
    }
}
