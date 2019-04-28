package com.example.demo.controller.graduationDesign;

import com.example.demo.domain.Label;
import com.example.demo.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api")
public class apiController {
    @Autowired
    LabelService labelService;

    /**
    * @Description: 根据查询标签返回对应的课题
    * @Param: [name],多个标签名用逗号分开
    * @return: java.lang.String ,多个课题编号用,分开
    * @Author: klx
    * @Date: 2019-04-28
    */
    @GetMapping("getSubject")
    public String[] getSubject(String label) {
        String[] labelArray = label.split(",");
        String[] nameArray;
        String number = labelService.getSubjectNumber((labelArray[0]));
        nameArray = (String[])(number.split(",")).clone();
        for(int i = 1; i < labelArray.length; i++) {
            number = labelService.getSubjectNumber(labelArray[i]);
            String[] temp = intersection(nameArray, number.split(","));
            nameArray = null;
            nameArray = (String[])temp.clone();
        }
        return nameArray;
    }

    /**
    * @Description: 求两个数组的交集，修改在第一数组中
    * @Param: [arr1, arr2]
    * @return: void
    * @Author: klx
    * @Date: 2019-04-28
    */
    private String[] intersection(String[] arr1, String[] arr2) {
        ArrayList<String> list = new ArrayList<String>();
        for(String val:arr1) {
//            System.out.println(val );
            if(contain(arr2, val)) {
//                System.out.println(val);
                list.add(val);
            }
        }
//        System.out.println("end");
        String[] newArray=list.toArray(new String[list.size()]);
        return newArray;
    }

    /**
    * @Description: 判断数组中是否包含某个字符串
    * @Param: [arr, value]
    * @return: void
    * @Author: klx
    * @Date: 2019-04-28
    */
    private boolean contain(String[] arr,String value) {
        for(String val:arr) {
            if(val.equals(value)) {
                return true;
            }
        }

        return false;
    }
}
