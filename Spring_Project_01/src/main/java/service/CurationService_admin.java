package service;

import dao.CurationDAO_admin;
import dto.CurationDTO_admin;
import filter.CurationFilterDTO_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurationService_admin {
    @Autowired
    private CurationDAO_admin curationDAO;

    public CurationDTO_admin getCurationById(int curation_id) {
        return curationDAO.getCurationById(curation_id);
    }

    public List<CurationDTO_admin> getAllCurations() {
        return curationDAO.getAllCurations();
    }

    public void insertCuration(CurationDTO_admin curation) {
        curationDAO.insertCuration(curation);
    }
    
    public void updateCuration(CurationDTO_admin curation) {
        curationDAO.updateCuration(curation);
    }

    public void deleteCuration(int curation_id) {
        curationDAO.deleteCuration(curation_id);
    }

    public List<CurationDTO_admin> getCurationsByFilterAndSort(CurationFilterDTO_admin filter) {
        return curationDAO.getCurationsByFilterAndSort(filter);
    }

    public int getCurationCountByFilter(CurationFilterDTO_admin filter) {
        return curationDAO.getCurationCountByFilter(filter);
    }
}
