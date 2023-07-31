package org.numo.service;
import org.numo.dao.entity.Reference;


import java.util.List;

public interface ReferenceService {
    List<Reference> referencelist();
    public Reference addReference(Reference reference);
    public void delete(Long id);
    public Reference findReferenceByName(String name);
}
