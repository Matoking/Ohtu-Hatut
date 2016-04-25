package ohtuhatut.service;

import ohtuhatut.domain.ReferenceList;
import ohtuhatut.repository.ReferenceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author iilumme
 */

@Service
public class ReferenceListService {

    @Autowired
    private ReferenceListRepository referenceListRepository;

    public ReferenceList getReferenceList(Long id) { return referenceListRepository.findOne(id); }

    public void save(ReferenceList referenceList) {
        referenceListRepository.save(referenceList);
    }
    
    public List<ReferenceList> getAllReferenceLists() {
        return referenceListRepository.findAll();
    }

}
