from app.__init__ import *
#使用蓝图
from flask import json
operationInfoBlue=Blueprint('operationInfo_blue',__name__)
@operationInfoBlue.route('/operation',methods=['POST','GET'])
def notice():
    if not "username" in list(session.keys()) :
        return redirect(url_for('login_blue.log_in'))
    else:
        order="select * from operation order by time DESC;"
        Cur.execute(order)
        dataOperation=Cur.fetchall()
        list1=[]
        for each in dataOperation:
            if each[3]==1:
                if each[4]=="open":
                    str1="开放选课"
                else:
                    str1="关闭选课"
            elif each[3]==2:
                if each[4]=="open":
                    str1="开放学生课题选择"
                else:
                    str1="关闭学生课题选择"
            else:
                str1=each[4]
            list1.append({"操作人员":each[2],"操作时间":each[1],"操作信息":str1})
        return render_template("operation.html",dataOperation=json.dumps(list1, ensure_ascii=False))

@operationInfoBlue.route('/<string:type>学生课程选择',methods=['POST','GET'])
def turnCourse(type):
    if type=="打开":
        order="insert into operation (time,people,type,content) values ("+"CURRENT_TIMESTAMP"+",\""+session['username']+"\",1,\""+"open\");"

    else:
        order="insert into operation (time,people,type,content) values ("+"CURRENT_TIMESTAMP"+",\""+session['username']+"\",1,\""+"close\");"

    # print(order)
    Cur.execute(order)
    db.commit()
    return redirect(url_for("courseInfo_blue.course"))

@operationInfoBlue.route('/<string:type>学生课题选择',methods=['POST','GET'])
def turnSubject(type):
    if type=="打开":
        order="insert into operation (time,people,type,content) values ("+"CURRENT_TIMESTAMP"+",\""+session['username']+"\",2,\""+"open\");"

    else:
        order="insert into operation (time,people,type,content) values ("+"CURRENT_TIMESTAMP"+",\""+session['username']+"\",2,\""+"close\");"

    # print(order)
    Cur.execute(order)
    db.commit()
    return redirect(url_for("subjectInfo_blue.subjectInfo"))
