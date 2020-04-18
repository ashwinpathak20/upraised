package com.upraised.springmvc.service;

import com.upraised.springmvc.dao.OpeningsDao;
import com.upraised.springmvc.model.Openings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.List;

@Component
@Service("openingsService")
@Transactional
public class OpeningsServiceImpl implements OpeningsService{

    @Autowired
    private OpeningsDao dao;

    public Openings findById(int id) {
        return dao.findById(id);
    }

    public void saveOpenings(Openings openings) {
        dao.saveOpenings(openings);
    }

    public void updateOpenings(Openings openings) {
        Openings entity = dao.findById(openings.getId());
        if(entity!=null){
            entity.setJob_id(openings.getJob_id());
            entity.setCompany(openings.getCompany());
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

    public void deleteOpeningsByJobId(Integer job_id) { dao.deleteOpeningsByJobId(job_id); }

    public List<Openings> findAllOpenings() {
        return dao.findAllOpenings();
    }

    public Openings findOpeningsByJobId(Integer job_id) { return dao.findOpeningsByJobId(job_id); }

    /*
     * This method checks for the openings by company.
     */
    public List<Openings> findOpeningsByCompany(String company) { return dao.findOpeningsByCompany(company); }

    /*
     * This method filters the openings for company.
     */
    public List<Openings> filterOpenings(Map<String, String> map) { return dao.filterOpenings(map); }

    public boolean isOpeningsByJobIdUnique(Integer id, Integer job_id) {
        Openings openings = findOpeningsByJobId(job_id);
        return ( openings == null || ((id != null) && (openings.getId() == id)));
    }
}
