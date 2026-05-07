package duy.project.yoot_management.service;

import duy.project.yoot_management.domains.Parent;
import duy.project.yoot_management.dto.request.ParentUpsertRequest;

import java.util.List;
import java.util.Optional;

public interface ParentService {

    List<Parent> findAll();

    Optional<Parent> findById(Long id);

    Parent save(ParentUpsertRequest parent);

    Parent update(Long id, ParentUpsertRequest parent);

    void deleteById(Long id);

}
