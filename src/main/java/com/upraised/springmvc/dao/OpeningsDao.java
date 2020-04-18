package com.upraised.springmvc.dao;

import com.upraised.springmvc.model.Openings;

import java.util.Map;
import java.util.List;

public interface OpeningsDao {

    Openings findById(int id);

    void saveOpenings(Openings openings);

    void deleteOpeningsByJobId(Integer job_id);

    List<Openings> findAllOpenings();

    Openings findOpeningsByJobId(Integer job_id);

    List<Openings> findOpeningsByCompany(String company);

    List<Openings> filterOpenings(Map<String, String> map);
}
