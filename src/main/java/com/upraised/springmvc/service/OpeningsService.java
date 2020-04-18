package com.upraised.springmvc.service;

import com.upraised.springmvc.model.Openings;

import java.util.Map;
import java.util.List;

public interface OpeningsService {

    Openings findById(int id);

    void saveOpenings(Openings openings);

    void updateOpenings(Openings openings);

    void deleteOpeningsByJobId(Integer job_id);

    List<Openings> findAllOpenings();

    Openings findOpeningsByJobId(Integer job_id);

    boolean isOpeningsByJobIdUnique(Integer id, Integer job_id);

    List<Openings> findOpeningsByCompany(String company);

    List<Openings> filterOpenings(Map<String, String> map);
}
