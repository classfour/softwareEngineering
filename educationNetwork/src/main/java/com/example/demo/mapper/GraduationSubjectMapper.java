package com.example.demo.mapper;


import com.example.demo.domain.GraduationSubject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* @Description: 毕设课题表接口
* @Author: klx
* @Date: 2019/4/17
*/
public interface GraduationSubjectMapper {
    @Select("select * from graduation_subject")
    public GraduationSubject[] selectAll();
    @Select("select * from graduation_subject where serialnumber = #{serialnumber}")
    public GraduationSubject select(String serialnumber);
    @Update("update graduation_subject set number=#{number} where serialnumber=#{serialnumber}")
    public boolean updateNumber(int number, String serialnumber);
    @Insert("insert into graduation_subject values(#{serialnumber}, #{name}, #{introduce}, #{status}, #{max}, #{teacherNumber}, #{number}, #{maxNumber}, 0)")
    public boolean insert(GraduationSubject graduationSubject);
    @Update("update graduation_subject set name=#{name}, status=0, introduce = #{introduce} where serialnumber = #{serialnumber}")
    public boolean updateContent(String name, String introduce, String serialnumber);
    @Select("select * from graduation_subject where teacher_number=#{teacherNumber}")
    public GraduationSubject selectByNumber(String teacherNumbner);
    @Select("select graduation_subject.* from graduation_subject left join teacher on departments=#{department} and graduation_subject.teacher_number=teacher.number")
    public GraduationSubject[] selectByDepartment(String department);
    @Update("update graduation_subject set now_number=#{nowNumber} where serialnumber=#{serialnumber}")
    public boolean updateNowNumber(int nowNumber, String serialnumber);
}
