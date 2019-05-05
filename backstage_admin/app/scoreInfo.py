from app.__init__ import *
from flask import json
#使用蓝图
scoreInfoBlue=Blueprint('scoreInfo_blue',__name__)
@scoreInfoBlue.route('/score',methods=['POST','GET'])
def scoreInfo():
    order="select distinct student_number from choose_course;"
    Cur.execute(order)
    studentData=Cur.fetchall()
    print(studentData)
    for each in studentData:
        print(each[0])
        sumcredits=0
        SUM=0
        order="select distinct course_number,gpa from choose_course where student_number=\""+each[0]+"\";"
        #print(order)
        Cur.execute(order)
        courseData=Cur.fetchall()
        #print(courseData)
        for eachCourse in courseData:
            order="select credits from course where number=\""+eachCourse[0]+"\";"
            Cur.execute(order)
            credit=Cur.fetchone()[0]
            #print(Cur.fetchone())
            SUM+=credit*eachCourse[1]
            sumcredits+=credit

        score=round(SUM/sumcredits, 2)
        order="update student set gpa ="+str(score)+" where number=\""+each[0]+"\";"
        Cur.execute(order)
        db.commit()

        order="select number,name,gpa from student;"
        Cur.execute(order)
        data=Cur.fetchall()
        dataS=[]
        for each in data:
            dataS.append({"学生学号":each[0],"学生姓名":each[1],"学生绩点":str(each[2])})
        #print(dataS)
    return render_template("score.html",dataS=json.dumps(dataS, ensure_ascii=False))

@scoreInfoBlue.route('/sSubject',methods=['POST','GET'])
def sSubject():
    order="select student.number,student.name,subject_results.title from student inner join subject_results on " \
          "student.number=subject_results.student_number;"
    Cur.execute(order)
    data=Cur.fetchall()
    dataS=[]
    for each in data:
        dataS.append({"学生学号":each[0],"学生姓名":each[1],"课题标题":each[2]})
    return render_template("sSubject.html",dataS=json.dumps(dataS, ensure_ascii=False))