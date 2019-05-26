package com.example.demo.service.impl;
import com.example.demo.domain.GraduationSubject;
import com.example.demo.domain.Label;
import com.example.demo.mapper.GraduationSubjectMapper;
import com.example.demo.mapper.LabelMapper;
import com.example.demo.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {
    @Autowired
    LabelMapper labelMapper;
    @Autowired
    GraduationSubjectMapper graduationSubjectMapper;

    public String[] getSubjectNumber(String label, String[] number) {
//        String[] labelArray = label.split(",");
//        String[] nameArray;
//        String number = labelMapper.select(labelArray[0]).getCourseNumber();
//        nameArray = (String[])(number.split(",")).clone();
//        for(int i = 1; i < labelArray.length; i++) {
//            number = labelMapper.select(labelArray[i]).getCourseNumber();
//            String[] temp = intersection(nameArray, number.split(","));
//            nameArray = null;
//            nameArray = (String[])temp.clone();
//        }
        String courseNumber = labelMapper.select(label).getCourseNumber();
        String[] numberArray = courseNumber.split(",");
        String[] temp = intersection(numberArray, number);
        return temp;
    }

    @Override
    public Label[] getAllLabel() {
        return labelMapper.getAllLabel();
    }

    @Override
    public boolean updateLabel(String courseNumber, int id) {
        return labelMapper.update(courseNumber, id);
    }

    @Override
    public Label selectById(int id) {
        return labelMapper.selectById(id);
    }

    @Override
    public Label selectBySubject(String serialnumber) {
        Label[] labels = labelMapper.getAllLabel();
        for(Label val:labels) {
            if((val.getCourseNumber()).contains(serialnumber)) {
                return val;
            }
        }
        return null;
    }

    @Override
    public String[] selectByDepartment(String department, String[] number) {
        GraduationSubject[] graduationSubjects = graduationSubjectMapper.selectByDepartment(department);
        List<String> num = new ArrayList<String>();
        for(GraduationSubject subject:graduationSubjects) {
            num.add(subject.getSerialnumber());
        }
        String[] numberArray = num.toArray(new String[num.size()]);
        return intersection(numberArray, number);
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
