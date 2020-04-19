package com.upraised.springmvc.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.upraised.springmvc.model.Openings;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import static com.upraised.springmvc.commons.OpeningsConstants.*;

@Component
@Repository(OPENINGS_DAO)
public class OpeningsDaoImpl extends AbstractDao<Integer, Openings> implements OpeningsDao {

    /*
     * Finds Openings by Id.
     */
    public Openings findById(int id) {
        return getByKey(id);
    }

    /*
     * Saves openings.
     */
    public void saveOpenings(Openings openings) {
        persist(openings);
    }

    /*
     * Deletes openings.
     */
    public void deleteOpeningsByJobId(Integer job_id) {
        Query query = getSession().createSQLQuery(DELETE_SQL_QUERY);
        query.setInteger(JOB_ID, job_id);
        query.executeUpdate();
    }

    /*
     * Finds all openings.
     */
    @SuppressWarnings("unchecked")
    public List<Openings> findAllOpenings() {
        Criteria criteria = createEntityCriteria();
        return (List<Openings>) criteria.list();
    }

    /*
     * Finds openings by job id.
     */
    public Openings findOpeningsByJobId(Integer job_id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(JOB_ID, job_id));
        return (Openings) criteria.uniqueResult();
    }

    /*
     * This method checks for the openings by company.
     */
    @SuppressWarnings("unchecked")
    public List<Openings> findOpeningsByCompany(String company) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(COMPANY, company));
        return (List<Openings>) criteria.list();
    }

    /*
     * This method filters the openings by company.
     */
    @SuppressWarnings("unchecked")
    public List<Openings> filterOpenings(Map<String, String> map) {
        Criteria criteria = createEntityCriteria();
        for (Map.Entry<String,String> entry : map.entrySet())  {
            if(entry.getKey()== SALARY){
                continue;
            }
            if(entry.getValue() != null && !entry.getValue().isEmpty()) {
                criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
            }
        }
        List<Openings> openings = (List<Openings>) criteria.list();
        if(map.get(SALARY) == null || map.get(SALARY).isEmpty()){
            return openings;
        }
        List<Openings> filteredOpenings = new ArrayList<>();
        BigDecimal salary = new BigDecimal(map.get(SALARY));
        for(Openings opening : openings) {
            if(salary.compareTo(opening.getSalary())!=1){
                filteredOpenings.add(opening);
            }
        }
        return filteredOpenings;
    }
}
