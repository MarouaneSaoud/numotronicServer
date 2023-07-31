package org.numo.service.impl;
import lombok.AllArgsConstructor;
import org.numo.dao.entity.Reference;
import org.numo.dao.repository.ReferenceRepository;
import org.numo.service.ReferenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@AllArgsConstructor
@Transactional
@Service
public class ReferenceServiceImpl implements ReferenceService {
    private final ReferenceRepository referenceRepository;
    @Override
    public List<Reference> referencelist() {
        List<Reference> referenceList = referenceRepository.findAll();
        return referenceList;

    }
    @Override
    public Reference addReference(Reference reference) {
        Reference save = referenceRepository.save(reference);
        return save;
    }

    @Override
    public void delete(Long id) {
        referenceRepository.deleteById(id);
    }

    @Override
    public Reference findReferenceByName(String name) {
        return referenceRepository.findReferenceByName(name);
    }
}
