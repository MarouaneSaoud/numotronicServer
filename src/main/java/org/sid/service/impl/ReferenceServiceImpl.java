package org.sid.service.impl;

import lombok.AllArgsConstructor;
import org.sid.dao.entity.Reference;
import org.sid.dao.repository.ReferenceRepository;
import org.sid.service.ReferenceService;
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
        return null;
    }

    @Override
    public Reference addReference(Reference reference) {
        Reference save = referenceRepository.save(reference);
        System.out.println(reference);
        return save;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Reference findReferenceByName(String name) {
        return null;
    }
}
