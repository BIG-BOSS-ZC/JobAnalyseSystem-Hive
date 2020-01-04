package com.hive.demo.controller;

import com.hive.demo.dao.ItemMapper;
import com.hive.demo.model.Item;
import com.hive.demo.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
public class WorkController {
    @Autowired
    WorkService workService;

    @RequestMapping("/trend/data1")
    public String[] getData1(){
        String [] data1={};
        return data1;
    }

    @RequestMapping("/trend/sdata")
    public List<Object[]> getSdaat(){
        String[] t1={"product", "0+", "1+", "2年+", "3年+", "5年+", "8年+"};
        Object[] t2={"3K以下", 4319, 907, 447, 467, 220, 31};
        Object[] t3={"3K-5K", 26844, 18170, 7305, 3134, 353, 15};
        Object[] t4={"5K-10K", 98692, 59965, 51046, 41633, 8576, 481};
        Object[] t5={"10K-20K", 30337, 17545, 30596, 62957, 27846, 2138};
        Object[] t6={"20K-50K", 4607, 2972, 2844, 10500, 13414, 2551};
        Object[] t7={"50K+", 304, 251, 53, 179, 373, 120};
        List<Object[]> list=new ArrayList<Object[]>();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t6);
        list.add(t7);
        return list;
    }

    @RequestMapping("/trend/citysa")
    public List<Object> getCitySa(){
        return workService.getCitySa();
    }

    @RequestMapping("/trend/edusa")
    public List<Object> getEduSa(){
        return workService.getEduSa();
    }

    @RequestMapping("/trend/jobnum")
    public List<Object> getJobNum(){
        return workService.getJobNum();
    }


    // url 参数: http://localhost:8080/paramTest/urlParam?id=1&name=zhangsan

    @Autowired
    ItemMapper itemMapper;
    @RequestMapping("getItems")
    public Map<String,Object> getItems(@RequestParam("start") int start,@RequestParam("rows") int rows,@RequestParam("search") String search) {
        Map<String,Object> map=new HashMap<>();
        search="%"+search+"%";
        List<Item> items = workService.getItems(start,rows,search);
        int count = itemMapper.getCount(search);
        int getcount=count/rows;
        map.put("getcount",getcount);
        map.put("items",items);
        return map;
    }

}
/*@RequestMapping(value = { "/static/img/{picname}" }, method = RequestMethod.GET, produces =
            MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] pic(@PathVariable String picname){
        System.out.println(picname);
        File file=new File("D:\\JavaProjects\\hive0925\\src\\main\\resources\\static\\img\\"+picname);
        FileInputStream inputStream = null;
        byte[] bytes=null;
        try {
            inputStream = new FileInputStream(file);
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }*/