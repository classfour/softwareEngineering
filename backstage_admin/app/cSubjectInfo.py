from app.__init__ import *
from flask import json
from app.subjectInfo import toStatus

#使用蓝图
cSubjectInfoBlue=Blueprint('cSubjectInfo_blue',__name__)
@cSubjectInfoBlue.route('/cSubject',methods=['POST','GET'])
def cSubjectInfo():
    order = "select * from subject_results;"

    if request.method=="POST":
        listForm=list(request.form)
        print(listForm)
        if "delete" in listForm and len(listForm)>1:
            orderD="delete from subject_results where student_number in("

            #删除所选内容
            for each in listForm:
                if each[:6]=="select":
                    orderD+="\""+each[6:]+"\","
            orderD=orderD[:-1]+");"
            Cur.execute()
            db.commit()
        else:
            for each in listForm:
                if each[:6] =="choose":
                    order1="select serialnumber from graduation_subject where teacher_number=\""+each[6:]+"\";"
                    Cur.execute(order1)
                    serialnumber=Cur.fetchall()
                    print(serialnumber)
                    order="select * from subject_results where course_number in ("
                    for each in serialnumber:
                        order+="\""+each[0]+"\","
                    order=order[:-1]+");"



    #print(order)
    Cur.execute(order)
    dataR=Cur.fetchall()
    listR=[]

    for each in dataR:
        checkstr = "<input type=\"checkbox\" name=\"select" + str(each[0]) + "\"  />"
        orderS = "select teacher_number from graduation_subject where serialnumber=\"" + each[1] + "\";"
        Cur.execute(orderS)

        teacher_number=Cur.fetchall()[0][0]

        listR.append({"<button class=\"btn btn-default\" name=\"delete\">批量删除</button>":checkstr,
                      "学生学号":"<a href=\"alter/"+each[0]+"\">"+each[0]+"</a>","课题号":each[1],"教师编号":"<button name=\"choose"+teacher_number+"\">"+teacher_number+"</button>",
                      "提交标题":each[2],"提交内容":each[3],"成绩":each[4],"是否被申诉":toStatus(each[5])})
        print(listR)

    return render_template("select_subject.html",dataR=json.dumps(listR,ensure_ascii=False))