from app.__init__ import *
from flask import json
#使用蓝图
labelInfoBlue=Blueprint('labelInfo_blue',__name__)
@labelInfoBlue.route('/label',methods=['POST','GET'])
def labelInfo():
    order = "select * from label;"
    Cur.execute(order)
    data = Cur.fetchall()
    dataL = []

    if request.method=="POST":
        #print(request.form)
        listForm=list(request.form)

        if "delete" in listForm :
            # 批量删除操作
            try:#跳过未选择而触发的异常
                orderDelete = "delete from label where id in ("
                for each in listForm:
                    if not each == "delete" and not each[:3]=="add":
                        orderDelete += "" + each + ","
                orderDelete = orderDelete[:-1] + ");"
                #print(orderDelete)
                Cur.execute(orderDelete)
                db.commit()
                #重新查询
                order = "select * from label;"
                Cur.execute(order)
                data = Cur.fetchall()
            except Exception:
                pass


        else:
            for each in listForm:
                #添加新课程到标签
                if each[:5]=="addto":
                    addCourse=request.form.get('add'+each[5:])
                    order="select course_number from label where id="+each[5:]+";"
                    Cur.execute(order)
                    dataAdd=Cur.fetchone()[0]+"+"+addCourse
                    if not addCourse=="no":
                        order="update label set course_number=\""+dataAdd+"\" where id="+each[5:]+";"
                        #print(order)
                        Cur.execute(order)
                        db.commit()

                    order = "select * from label;"
                    Cur.execute(order)
                    data = Cur.fetchall()
                    break

                #选择查询操作
                if each[:6]=="select":
                    button=each[6:]
                    data1=[]
                    for each in data:
                        course=each[2].split("+")
                        if button in course:
                            data1.append(each)
                    data=data1
                    break



    for each in data:
        course=each[2].split("+")
        strCourse=""
        checkstr = "<input type=\"checkbox\" name=\"" + str(each[0]) + "\"  />"
        strC = "\"theonefortest\","
        #print(course)
        for eachCourse in course:
            if not eachCourse=="":
                order="select name from course where number =\""+eachCourse+"\";"
                Cur.execute(order)

                strCourse+="<button class=\"btn btn-default\" name=\"select"+eachCourse+"\">"+Cur.fetchone()[0]+"</button>  "

                strC+="\""+eachCourse+"\","

        order="select number,name from course where number not in ("
        order+=strC[:-1]+");"
        #print(order)
        Cur.execute(order)
        dataC=Cur.fetchall()
        addStr="<select name=\"add"+str(each[0])+"\"><option value=\"no\">选择添加课程</option>"
        for eachC in dataC:
            addStr+="<option value=\""+str(eachC[0])+"\">"+eachC[1]+"</option>"

        addStr+="</select><button name=\"addto"+str(each[0])+"\">确认添加</button>"

        strCourse+=addStr

        dataL.append({"<button class=\"btn btn-default\" name=\"delete\">批量删除</button>":checkstr,"标签名":each[1],"课程":strCourse})
    #print(dataL)
    return render_template("label.html",dataL=json.dumps(dataL, ensure_ascii=False))

@labelInfoBlue.route('/newlabel',methods=['POST','GET'])
def newlabel():
    if request.method=="POST":
        order="insert into label (name,course_number) values(\""+request.form.get("labelname")+"\",\"\");"
        Cur.execute(order)
        db.commit()
    return redirect(url_for("labelInfo_blue.labelInfo"))