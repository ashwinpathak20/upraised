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

@Component
@Repository("openingsDao")
public class OpeningsDaoImpl extends AbstractDao<Integer, Openings> implements OpeningsDao {

    public Openings findById(int id) {
        return getByKey(id);
    }

    public void saveOpenings(Openings openings) {
        persist(openings);
    }

    public void deleteOpeningsByJobId(Integer job_id) {
        Query query = getSession().createSQLQuery("delete from Openings where job_id = :job_id");
        query.setInteger("job_id", job_id);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<Openings> findAllOpenings() {
        Criteria criteria = createEntityCriteria();
        return (List<Openings>) criteria.list();
    }

    public Openings findOpeningsByJobId(Integer job_id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("job_id", job_id));
        return (Openings) criteria.uniqueResult();
    }

    /*
     * This method checks for the openings by company.
     */
    @SuppressWarnings("unchecked")
    public List<Openings> findOpeningsByCompany(String company) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("company", company));
        return (List<Openings>) criteria.list();
    }

    /*
     * This method filters the openings by company.
     */
    @SuppressWarnings("unchecked")
    public List<Openings> filterOpenings(Map<String, String> map) {
        Criteria criteria = createEntityCriteria();
        for (Map.Entry<String,String> entry : map.entrySet())  {
            if(entry.getKey()=="salary"){
                continue;
            }
            if(entry.getValue() != null && !entry.getValue().isEmpty()) {
                criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
            }
        }
        List<Openings> openings = (List<Openings>) criteria.list();
        if(!map.containsKey("salary")){
            return openings;
        }
        List<Openings> filteredOpenings = new ArrayList<>();
        BigDecimal salary = new BigDecimal(map.get("salary"));
        System.out.println("Ashwink");
        System.out.println(salary);
        for(Openings opening : openings) {
            if(salary.compareTo(opening.getSalary())!=1){
                filteredOpenings.add(opening);
            }
        }
        return filteredOpenings;
    }
}
