package com.upraised.springmvc.service;

import com.upraised.springmvc.dao.OpeningsDao;
import com.upraised.springmvc.model.Openings;
import com.upraised.springmvc.service.OpeningsServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

public class OpeningsServiceImplTest {

    @Mock
    OpeningsDao dao;

    @InjectMocks
    OpeningsServiceImpl openingsService;

    @Spy
    List<Openings> openings = new ArrayList<Openings>();

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        openings = getOpeningsList();
    }

    @Test
    public void findById(){
        Openings opening = openings.get(0);
        when(dao.findById(anyInt())).thenReturn(opening);
        Assert.assertEquals(openingsService.findById(opening.getId()), opening);
    }

    @Test
    public void saveOpenings(){
        doNothing().when(dao).saveOpenings(any(Openings.class));
        openingsService.saveOpenings(any(Openings.class));
        verify(dao, atLeastOnce()).saveOpenings(any(Openings.class));
    }

    @Test
    public void updateOpenings(){
        Openings opening = openings.get(0);
        when(dao.findById(anyInt())).thenReturn(opening);
        openingsService.updateOpenings(opening);
        verify(dao, atLeastOnce()).findById(anyInt());
    }

    @Test
    public void deleteOpeningsByJobId(){
        doNothing().when(dao).deleteOpeningsByJobId(anyInt());
        openingsService.deleteOpeningsByJobId(anyInt());
        verify(dao, atLeastOnce()).deleteOpeningsByJobId(anyInt());
    }

    @Test
    public void findAllOpenings(){
        when(dao.findAllOpenings()).thenReturn(openings);
        Assert.assertEquals(openingsService.findAllOpenings(), openings);
    }

    @Test
    public void findOpeningsByJobId(){
        Openings opening = openings.get(0);
        when(dao.findOpeningsByJobId(anyInt())).thenReturn(opening);
        Assert.assertEquals(openingsService.findOpeningsByJobId(anyInt()), opening);
    }

    @Test
    public void findOpeningsByCompany(){
        when(dao.findOpeningsByCompany(anyString())).thenReturn(openings);
        Assert.assertEquals(openingsService.findOpeningsByCompany(anyString()), openings);
    }

    @Test
    public void filterOpenings(){
        when(dao.filterOpenings(anyMap())).thenReturn(openings);
        Assert.assertEquals(openingsService.filterOpenings(anyMap()), openings);
    }

    @Test
    public void isOpeningsByJobIdUnique(){
        Openings opening = openings.get(0);
        when(openingsService.findOpeningsByJobId(anyInt())).thenReturn(opening);
        Assert.assertEquals(openingsService.isOpeningsByJobIdUnique(opening.getId(), opening.getJob_id()), true);
    }

    public List<Openings> getOpeningsList(){
        Openings openings1 = new Openings();
        openings1.setJob_id(1);
        openings1.setSeniority_level("test_sl_1");
        openings1.setLocation("test_location1");
        openings1.setSalary(BigDecimal.valueOf(100.0));
        openings1.setSkills_required("test_skills1");
        openings1.setJob_title("test_title1");
        openings1.setCompany("test_company1");
        openings1.setJob_link("https://www.test_company1.com");

        Openings openings2 = new Openings();
        openings2.setJob_id(2);
        openings2.setSeniority_level("test_sl_2");
        openings2.setLocation("test_location2");
        openings2.setSalary(BigDecimal.valueOf(101.0));
        openings2.setSkills_required("test_skills2");
        openings2.setJob_title("test_title2");
        openings2.setCompany("test_company2");
        openings2.setJob_link("https://www.test_company2.com");

        openings.add(openings1);
        openings.add(openings2);
        return openings;
    }
}
