package duy.project.yoot_management.service.impl;

import duy.project.yoot_management.domains.Parent;
import duy.project.yoot_management.dto.request.ParentUpsertRequest;
import duy.project.yoot_management.dto.response.ParentResponse;
import duy.project.yoot_management.repository.ParentRepository;
import duy.project.yoot_management.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;
    private final ModelMapper mapper;


    @Override
    public List<Parent> findAll() {
        return parentRepository.findAll();
    }

    @Override
    public Optional<Parent> findById(Long id) {
        return parentRepository.findById(id);
    }

    @Override
    public Parent save(ParentUpsertRequest parent) {

        Parent creatingParent = new Parent();
        creatingParent.setFullName(parent.getFullName());
        creatingParent.setPhone(parent.getPhone());
        creatingParent.setEmail(parent.getEmail());
        creatingParent.setAddress(parent.getAddress());

        return parentRepository.save(creatingParent);
    }

    @Override
    public Parent update(Long id, ParentUpsertRequest parent) {

        Optional<Parent> optionalParent = parentRepository.findById(id);
        if (optionalParent.isEmpty()) {
            throw new RuntimeException("Parent not found with id: " + id);
        }

        Parent updatingParent = optionalParent.get();
        updatingParent.setFullName(parent.getFullName());
        updatingParent.setPhone(parent.getPhone());
        updatingParent.setEmail(parent.getEmail());
        updatingParent.setAddress(parent.getAddress());

        return parentRepository.save(updatingParent);
    }

    @Override
    public void deleteById(Long id) {
        if (!parentRepository.existsById(id)) {
            throw new RuntimeException("Parent not found with id: " + id);
        }
        parentRepository.deleteById(id);
    }



    // PRIVATE METHODS
    private ParentResponse mapParentResponse(Parent parent) {
        return mapper.map(parent, ParentResponse.class);
    }

}
