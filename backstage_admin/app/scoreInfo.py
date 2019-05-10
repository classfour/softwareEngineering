from app.__init__ import *
from flask import json
#使用蓝图
scoreInfoBlue=Blueprint('scoreInfo_blue',__name__)
@scoreInfoBlue.route('/score',methods=['POST','GET'])
def scoreInfo():
    try:
        session['route'] += "sco"
    except Exception:
        return redirect(url_for('login_blue.log_in'))


    major="all"
    if request.method=="POST":
        major=request.form.get('major')

    order="select distinct student_number from choose_course;"


    Cur.execute(order)
    studentData=Cur.fetchall()
    #print(studentData)
    for each in studentData:
        #print(each[0])
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

        order="select number,name,gpa,major from student;"
        Cur.execute(order)
        data=Cur.fetchall()
        dataS=[]
        for each in data:
            if major=="all" or each[3]==major:
                dataS.append({"学生学号":each[0],"学生姓名":each[1],"学生绩点":str(each[2]),"学生专业":each[3]})
        #print(dataS)

    order="select distinct major from student;"
    Cur.execute(order)
    data=Cur.fetchall()
    Major=[]
    for each in data:
        Major.append(each[0])
    return render_template("score.html",dataS=json.dumps(dataS, ensure_ascii=False),username=session['username'],major=Major)

#实现查询课题信息
@scoreInfoBlue.route('/sSubject',methods=['POST','GET'])
def sSubject():
    try:
        session['route'] += "scS"
    except Exception:
        return redirect(url_for('login_blue.log_in'))

    major="all"
    if request.method=="POST":
        major=request.form.get('major')

    order="select student.number,student.name,subject_results.title,subject_results.result,student.major from student inner join subject_results on " \
          "student.number=subject_results.student_number;"
    Cur.execute(order)
    data=Cur.fetchall()
    dataS=[]
    for each in data:
        if major=="all" or major==each[4]:
            dataS.append({"学生学号":each[0],"学生姓名":each[1],"课题标题":each[2],"课题成绩":each[3],"学生专业":each[4]})

    order = "select distinct major from student;"
    Cur.execute(order)
    data = Cur.fetchall()
    Major = []
    for each in data:
        Major.append(each[0])
    return render_template("sSubject.html",dataS=json.dumps(dataS, ensure_ascii=False),username=session['username'],major=Major)