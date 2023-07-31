package org.numo.api;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.numo.dao.entity.Reference;
import org.numo.service.ReferenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reference")
@RequiredArgsConstructor
@Api(tags = "reference", description = "Endpoints to manage references")

public class ReferenceController{
    private final ReferenceService referenceService;
   /*@PostConstruct
    void init(){
        referenceService.addReference(new Reference(null,"test", null));
    }*/
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
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        referenceService.delete(id);
    }
    @GetMapping("/{name}")
    public Reference findReferenceByName(@PathVariable String name){
        return referenceService.findReferenceByName(name);
    }
}
