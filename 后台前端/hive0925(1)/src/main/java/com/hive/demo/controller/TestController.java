package com.hive.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = { "img/{picname}" }, method = RequestMethod.GET, produces =
            MediaType.IMAGE_JPEG_VALUE)
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
    }
}
