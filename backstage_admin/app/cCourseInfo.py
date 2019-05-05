from app.__init__ import *
from flask import json
#使用蓝图
cCourseInfoBlue=Blueprint('cCourseInfo_blue',__name__)
@cCourseInfoBlue.route('/cCourse',methods=['POST','GET'])
def cCourseInfo():
    if request.method == "POST":
        listForm = list(request.form)
        # print(listForm)
        if "delete" in listForm and len(listForm) > 1:
            orderD = "delete from choose_course where id in("

            # 删除所选内容
            for each in listForm:
                if each[:6] == "select":
                    orderD += "" + each[6:] + ","
            orderD = orderD[:-1] + ");"
            Cur.execute(orderD)
            db.commit()
            #print(orderD)
            return redirect(url_for("index_blue.hello_world", username=session['username']))

    order="select * from choose_course;"
    Cur.execute(order)
    data=Cur.fetchall()
    dataC=[]


    for each in data:
        checkstr = "<input type=\"checkbox\" name=\"select" + str(each[0]) + "\"  />"
        dataC.append({"<button class=\"btn btn-default\" name=\"delete\">批量删除</button>":checkstr,
                      "学生编号":each[1],"课程编号":each[2],"成绩":each[3],"选课时间":each[4],"gpa":str(each[5])})
    #print(dataC)
    return render_template("select_course.html",dataC=json.dumps(dataC,ensure_ascii=False))