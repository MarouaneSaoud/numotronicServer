package org.sid.api;

import lombok.RequiredArgsConstructor;
import org.sid.dao.entity.Reference;
import org.sid.service.ReferenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reference")
@RequiredArgsConstructor
public class ReferenceController{
    private final ReferenceService referenceService;

    @GetMapping("/")
    public List<Reference> refferencelist(){
        List<Reference> ref = referenceService.referencelist();
        return ref;
    }
    @PostMapping("/add")
    public Reference save (@RequestBody Reference reference){
        Reference ref1 = referenceService.addReference(reference);
        return ref1;
    }
    @DeleteMapping("/delete")
    public void delete(@PathVariable Long id){
        referenceService.delete(id);
    }
}
