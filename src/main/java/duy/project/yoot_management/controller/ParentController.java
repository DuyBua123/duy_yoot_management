package duy.project.yoot_management.controller;

import duy.project.yoot_management.domains.Parent;
import duy.project.yoot_management.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;


    @GetMapping
    public ResponseEntity<List<Parent>> getParents() {
        return ResponseEntity.ok(parentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parent> getParentById(Long id) {
        return parentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Parent> createParent(ParentUpsertRequest parent) {
        return ResponseEntity.ok(parentService.save(parent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parent> updateParent(
            @PathVariable("id") Long id,
            @RequestBody ParentUpsertRequest parent
    ) {
        try {
            return ResponseEntity.ok(parentService.update(id, parent));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable("id") Long id) {
        try {
            parentService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
