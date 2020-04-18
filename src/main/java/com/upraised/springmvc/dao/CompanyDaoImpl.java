package com.upraised.springmvc.dao;

import com.upraised.springmvc.model.Company;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository("companyDao")
public class CompanyDaoImpl extends AbstractDao<Integer, Company> implements CompanyDao{

    public Company findById(int id) {
        return getByKey(id);
    }

    public void saveCompany(Company company) {
        persist(company);
    }

    public void deleteCompanyByName(String name) {
        Query query = getSession().createSQLQuery("delete from Company where name = :name");
        query.setString("name", name);
        query.executeUpdate();
    }

    /*
     * This method finds all the existing companies.
     */
    @SuppressWarnings("unchecked")
    public List<Company> findAllCompanies() {
        Criteria criteria = createEntityCriteria();
        return (List<Company>) criteria.list();
    }

    /*
     * This method finds a company by name.
     */
    public Company findCompanyByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (Company) criteria.uniqueResult();
    }
}
