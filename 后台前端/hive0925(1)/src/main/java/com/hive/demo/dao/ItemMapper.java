package com.hive.demo.dao;

import com.hive.demo.model.Item;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMapper {
    @Select("select jobname,minsalary,maxsalary,exprience,edu,company,trade from bigdata where jobname like #{param3} limit #{param1},#{param2}")
    List<Item> getItems(int param1,int param2,String param3);
    @Select("select count(*) from bigdata where jobname like #{param1}")
    Integer getCount(String param1);
}
