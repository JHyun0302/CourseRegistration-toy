package courseregistration.system.service;

import courseregistration.system.entity.Major;
import courseregistration.system.exception.ResourceNotFoundException;
import courseregistration.system.repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MajorService {
    private final MajorRepository majorRepository;

    public Major findById(Long majorId) {
        return majorRepository.findById(majorId)
                .orElseThrow(() -> new ResourceNotFoundException("Fail: No Major Info"));
    }

    public List<Major> findAll() {
        return majorRepository.findAll();
    }
}
