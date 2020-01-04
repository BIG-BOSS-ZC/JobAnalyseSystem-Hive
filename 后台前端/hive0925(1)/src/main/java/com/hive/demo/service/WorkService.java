package com.hive.demo.service;

import com.hive.demo.dao.ItemMapper;
import com.hive.demo.dao.JobNumMapper;
import com.hive.demo.dao.WorkMapper;
import com.hive.demo.model.CitySa;
import com.hive.demo.model.Item;
import com.hive.demo.model.JobNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class WorkService {
    @Autowired
    WorkMapper workMapper;

    public List<Object> getCitySa(){
        List<CitySa> cs = workMapper.getCitySa();
        Map<List<String>,List<Double>> map=new HashMap<>();
        List<String> city=new ArrayList<>();
        List<Double> salary=new ArrayList<>();
        for(CitySa c:cs){
            city.add(c.getCityname());
            salary.add((double) Math.round(c.getSalary()*100) / 100);
        }
        List<Object> list=new ArrayList<>();
        list.add(city);
        list.add(salary);
        return list;
    }

    public List<Object> getEduSa(){
        List<CitySa> cs = workMapper.getEduSa();
        Map<List<String>,List<Double>> map=new HashMap<>();
        List<String> city=new ArrayList<>();
        List<Double> salary=new ArrayList<>();
        for(CitySa c:cs){
            city.add(c.getCityname());
            salary.add((double) Math.round(c.getSalary()*100) / 100);
        }
        List<Object> list=new ArrayList<>();
        list.add(city);
        list.add(salary);
        return list;
    }
    @Autowired
    JobNumMapper jobNumMapper;
    public List<Object> getJobNum(){
        List<JobNum> jobNums = jobNumMapper.getJobNum();
        List<Object> l=new ArrayList<>();
        for (JobNum j:jobNums){
            String jbn=j.getJobname();
            int [] num={j.getSz(),j.getBj(),j.getSh(),j.getGz(),j.getWh()};
            l.add(jbn);
            l.add(num);
        }
        return l;
    }
    @Autowired
    ItemMapper itemMapper;
    public List<Item> getItems(int start,int rows,String search){
        if(rows<=10){
            rows=10;
        }
        int end=10;
        if(start>=0){
            end=(start+1)*rows;
            start=end-rows;
        }else {
            return null;
        }
        return itemMapper.getItems(start,end,search);
    }



}
