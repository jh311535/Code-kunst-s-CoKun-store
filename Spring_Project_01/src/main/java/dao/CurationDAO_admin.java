package dao;

import dto.CurationDTO_admin;
import filter.CurationFilterDTO_admin;
import mapper.CurationMapper_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CurationDAO_admin {
    @Autowired
    private CurationMapper_admin curationMapper;

    public CurationDTO_admin getCurationById(int curation_id) {
        return curationMapper.getCurationById(curation_id);
    }

    public List<CurationDTO_admin> getAllCurations() {
        return curationMapper.getAllCurations();
    }

    public void insertCuration(CurationDTO_admin curation) {
        curationMapper.insertCuration(curation);
    }
    
    public void updateCuration(CurationDTO_admin curation) {
        curationMapper.updateCuration(curation);
    }

    public void deleteCuration(int curation_id) {
        curationMapper.deleteCuration(curation_id);
    }

    public List<CurationDTO_admin> getCurationsByFilterAndSort(CurationFilterDTO_admin filter) {
        return curationMapper.getCurationsByFilterAndSort(filter);
    }

    public int getCurationCountByFilter(CurationFilterDTO_admin filter) {
        return curationMapper.getCurationCountByFilter(filter);
    }
}
