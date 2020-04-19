package com.upraised.springmvc.service;

import com.upraised.springmvc.dao.OpeningsDao;
import com.upraised.springmvc.model.Openings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.List;

import static com.upraised.springmvc.commons.OpeningsConstants.OPENINGS_SERVICE;

/*
 * This class is responsible for fetching data for Openings.
 */
@Component
@Service(OPENINGS_SERVICE)
@Transactional
public class OpeningsServiceImpl implements OpeningsService{

    @Autowired
    private OpeningsDao dao;

    /*
     * Finds openings by id.
     */
    public Openings findById(int id) {
        return dao.findById(id);
    }

    /*
     * Saves openings instance.
     */
    public void saveOpenings(Openings openings) {
        dao.saveOpenings(openings);
    }

    /*
     * Updates openings object.
     */
    public void updateOpenings(Openings openings) {
        Openings entity = dao.findById(openings.getId());
        if(entity!=null){
            entity.setJob_id(openings.getJob_id());
            entity.setJob_description(openings.getJob_description());
            entity.setJob_link(openings.getJob_link());
            entity.setPostingDate(openings.getPostingDate());
            entity.setJob_title(openings.getJob_title());
            entity.setSkills_required(openings.getSkills_required());
            entity.setSalary(openings.getSalary());
            entity.setLocation(openings.getLocation());
            entity.setSeniority_level(openings.getSeniority_level());
        }
    }

    /*
     * Deletes openings by job id.
     */
    public void deleteOpeningsByJobId(Integer job_id) { dao.deleteOpeningsByJobId(job_id); }

    /*
     * Finds all the openings.
     */
    public List<Openings> findAllOpenings() {
        return dao.findAllOpenings();
    }

    /*
     * Finds openings by job id
     */
    public Openings findOpeningsByJobId(Integer job_id) { return dao.findOpeningsByJobId(job_id); }

    /*
     * This method checks for the openings by company.
     */
    public List<Openings> findOpeningsByCompany(String company) { return dao.findOpeningsByCompany(company); }

    /*
     * This method filters the openings for company.
     */
    public List<Openings> filterOpenings(Map<String, String> map) { return dao.filterOpenings(map); }

    /*
     * Checks if openings is unique by company_name.
     */
    public boolean isOpeningsByJobIdUnique(Integer id, Integer job_id) {
        Openings openings = findOpeningsByJobId(job_id);
        return ( openings == null || ((id != null) && (openings.getId() == id)));
    }
}
