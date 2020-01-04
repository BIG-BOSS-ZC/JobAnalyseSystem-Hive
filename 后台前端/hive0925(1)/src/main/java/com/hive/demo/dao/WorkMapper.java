package com.hive.demo.dao;

import com.hive.demo.model.CitySa;
import com.hive.demo.model.JobNum;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WorkMapper {
    List<CitySa> getCitySa();
    List<CitySa> getEduSa();
}
