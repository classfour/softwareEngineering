from app.__init__ import *
from flask import json
from app.subjectInfo import toStatus

def judgeSex(sex):
    if type(sex)==type("sex"):
        #传入的是男或女
        if sex=="男":
            return 1
        else:
            return 0

    else:
        if sex==1:
            return "男"
        else:
            return "女"

def judgeGrade(grade):
    if type(grade)==type("grade"):
        if grade=="大一":
            return 0
        elif grade=='大二':
            return 1
        elif grade=='大三':
            return 2
        else :
            return 3
    else :
        if grade==0:
            return "大一"
        elif grade==1:
            return "大二"
        elif grade==2:
            return "大三"
        else :
            return "大四"

def getJson(tuple1):
    listUser=[]
    listName=[]
    listSex=[]
    listAge=[]
    listGrade=[]
    listClass=[]
    listDepartment=[]
    listMajor=[]
    listFinal=[]

    listTitle=[]
    listWorkage=[]
    listEnable=[]

    #判断处理的是学生还是教师
    if len(tuple1[0]) >11:
        #print("student")
        for each in tuple1:
            #print(len(each))
            listUser.append(each[0])
            listName.append(each[1])
            listGrade.append(judgeGrade(each[5]))
            listSex.append(judgeSex(each[2]))
            listClass.append(each[3])
            listDepartment.append(each[11])
            listMajor.append(each[10])
            listAge.append(each[9])

        for i in range(len(listUser)):
            listFinal.append({"姓名":listName[i],"用户名":listUser[i],"性别":listSex[i],"年级":listGrade[i]
                                 ,"年龄":listAge[i],"班级":listClass[i],"学院":listDepartment[i],"专业":listMajor[i]})




    else :
        for each in tuple1:
            listUser.append(each[0])
            listName.append(each[1])
            listSex.append(judgeSex(each[6]))
            listDepartment.append(each[2])
            listTitle.append(each[4])
            listAge.append(each[3])
            listWorkage.append(each[5])
            listEnable.append(each[8])

        for i in range(len(listUser)):
            listFinal.append({"姓名":listName[i],"用户名":listUser[i],"性别":listSex[i],"年龄":listAge[i],"教龄":listWorkage[i]
                              ,"职称":listTitle[i],"学院":listDepartment[i],"可教授课程":listEnable[i]})


    return listFinal

#使用蓝图
alterBlue=Blueprint('alter_blue',__name__)

#修改教师信息
@alterBlue.route('/altert/<string:username>',methods=['POST','GET'])
def altert(username):
    try:
        session['route'] += "alT"
    except Exception:
        return redirect(url_for('login_blue.log_in'))

    if request.method=="POST":
        #print(request.form)
        #print(list(request.form))
        if "upload" in list(request.form):
            #先删除再插入
            orderD="delete from teacher where number =\""+username+"\";"
            Cur.execute(orderD)
            db.commit()


            orderI = "insert into teacher values (\"" + username + "\",\"" + request.form.get("name") \
                     + "\",\"" + request.form.get("department") + "\"," + request.form.get("age") + ",\"" \
                     + request.form.get("title") + "\"," + request.form.get("workage") + "," + request.form.get("sex") \
                     + "," + request.form.get("status") + ",\" "+request.form.get("major")+"\",\"" \
                     + request.form.get("image") + "\");"
            #print(orderI)
            Cur.execute(orderI)
            db.commit()

        return redirect(url_for('userInfo_blue.userInfo',username=session['username']))


    #print("there")
    if "username" in list(session.keys()):

        order="select *from teacher where number=\""+username+"\";"
        Cur.execute(order)
        list1=list(Cur.fetchone())
        #print(list1)
        return render_template("altert.html",username=username,name=list1[1],department=list1[2],age=list1[3],title=list1[4],
                               workage=list1[5],sex=list1[6],status=list1[7],major=list1[8],image=list1[9])
    else:
        return redirect(url_for('login_blue.log_in'))


#修改学生信息
@alterBlue.route('/alter/<string:username>',methods=['POST','GET'])
def alter(username):
    try:
        session['route'] += "alt"
    except Exception:
        return redirect(url_for('login_blue.log_in'))

    if request.method=="POST":

        #print(list(request.form))
        if "upload" in list(request.form):
            order = "select gpa from student where number=\"" + username + "\";"
            Cur.execute(order)
            gpa = str(Cur.fetchone()[0])
            orderD = "delete from student where number =\"" + username + "\";"
            #print(orderD)
            Cur.execute(orderD)
            db.commit()


            orderI = "insert into student values (\"" + username + "\",\"" + request.form.get(
                "name") + "\"," + request.form.get("sex") \
                     + ",\"" + request.form.get("Class") + "\",\"" + request.form.get("date") + "\"," \
                     + request.form.get("grade") + "," + request.form.get("status") + "," + gpa\
                     +",\"" + request.form.get("image") + "\"," \
                     + request.form.get("age") + ",\"" + request.form.get("major") + "\",\"" +\
                     request.form.get("department") + "\");"
            #print(orderI)
            Cur.execute(orderI)
            db.commit()


        #print(error)
        return redirect(url_for('userInfo_blue.userInfo',username=session['username']))


    #print("there")
    if "username" in list(session.keys()):
        #print(username)

        order="select *from student where number=\""+username+"\";"
        Cur.execute(order)
        #print(order)
        list1=list(Cur.fetchone())
        #print(list1)

        #list1=list(Cur.fetchone())

        return render_template("alter.html",username=username,name=list1[1],sex=list1[2],Class=list1[3],date=list1[4],
                               grade=list1[5],status=list1[6],gpa=list1[7],image=list1[8],age=list1[9],major=list1[10],
                               department=list1[11])
    else:
        return redirect(url_for('login_blue.log_in'))


@alterBlue.route('/operate',methods=['POST','GET'])
def operate():
    try:
        session['route'] += "ope"
    except Exception:
        return redirect(url_for('login_blue.log_in'))

    error=None
    listMysqlS = ""
    for eachS in session['listS']:
        listMysqlS += "\"" + eachS + "\","
    # 去掉最后的一个逗号
    listMysqlS = listMysqlS[:-1] + ");"

    listMysqlT=""
    for eachT in session['listT']:
        listMysqlT+="\""+eachT+"\","
    listMysqlT=listMysqlT[:-1]+");"

    orderS="select *from student where number in("
    orderS+=listMysqlS
    #print(orderS)


    orderT = "select *from teacher where number in("
    orderT+=listMysqlT
    #print(orderT)

    #print(tupleS,tupleT)
    if not session["listS"]==[]:
        Cur.execute(orderS)
        tupleS = Cur.fetchall()
        dataS=getJson(tupleS)
    else:
        dataS="未选择学生"

    if not session["listT"]==[]:
        Cur.execute(orderT)
        tupleT = Cur.fetchall()
        dataT=getJson(tupleT)
    else:
        dataT="未选择教师"

    if request.method == 'POST':

        #print(request.form)
        listForm=list(request.form)#获得request键的列表
        password=request.form.get("password")
        #print(listForm)
        if password==session["password"]:
            if "ok" in listForm:
                #点击确认操作键
                alterMajor=request.form.get("alterMajor")
                alterTitle=request.form.get("alterTitle")
                if alterMajor=="no":#转专业操作
                    pass
                else:
                    orderAM="update student set major=\""+alterMajor+"\" where number in ("
                    for eachS in session['listS']:
                        orderAM += "\"" + eachS + "\","
                    # 去掉最后的一个逗号
                    orderAM = orderAM[:-1] + ");"
                    #print(orderAM)
                    Cur.execute(orderAM)
                    db.commit()


                if alterTitle=="no":#评选职称操作
                    pass
                else:
                    orderAT="update teacher set title=\""+alterTitle+"\" where number in ("
                    for eachT in session['listT']:
                        orderAT += "\"" + eachT + "\","
                    # 去掉最后的一个逗号
                    orderAT = orderAT[:-1] + ");"
                    #print(orderAT)
                    Cur.execute(orderAT)
                    db.commit()

            elif "deleteStudent" in listForm:


                orderDS="delete from student where number in ("
                orderDS+=listMysqlS
                #print(orderDS)
                Cur.execute(orderDS)
                db.commit()


                orderDU="delete from user where username in ("
                orderDU+=listMysqlS
                #print(orderDU)
                Cur.execute(orderDU)
                db.commit()

            elif "deleteTeacher" in listForm:
                orderDT = "delete from teacher where number in ("
                orderDT += listMysqlT
                #print(orderDT)
                Cur.execute(orderDT)
                db.commit()

                orderDU = "delete from user where username in ("
                orderDU += listMysqlT
                #print(orderDU)
                Cur.execute(orderDU)
                db.commit()


            else:
                pass



            return redirect(url_for("userInfo_blue.userInfo",username=session["username"]))
        else:
            error="密码输入不正确,无法操作"
        #print(dataT,dataS)

    if "username" in list(session.keys()):
        return render_template("operate.html", dataS=json.dumps(dataS, ensure_ascii=False),major=session['major'],
                               dataT=json.dumps(dataT,ensure_ascii=False),error=error)
    else:
        return redirect(url_for('login_blue.log_in'))

#修改课题信息
@alterBlue.route('/alters/<string:subjectname>',methods=['POST','GET'])
def alters(subjectname):
    try:
        session['route'] += "alS"
    except Exception:
        return redirect(url_for('login_blue.log_in'))
    order="select * from graduation_subject where serialnumber=\""+subjectname+"\";"
    Cur.execute(order)
    data=Cur.fetchall()[0]
    if request.method=="POST":
        print(request.form)
        listForm=list(request.form)
        if "upload" in listForm:
            orderD="delete from graduation_subject where serialnumber=\""+subjectname+"\";"
            Cur.execute(orderD)
            db.commit()
            orderI="insert into graduation_subject values(\""+subjectname+"\",\""+request.form.get('name')+"\",\""+\
                   request.form.get('introduce')+"\",\""+request.form.get('status')+"\",\""+request.form.get('max')+\
                   "\",\""+request.form.get('teacher_number')+"\",\""+request.form.get('number')+\
                   "\",\""+request.form.get('max_number')+"\");"
            Cur.execute(orderI)
            db.commit()
        return redirect(url_for('subjectInfo_blue.subjectInfo'))


    return render_template("alters.html",serialnumber=data[0],name=data[1],introduce=data[2],status=data[3],
                           max=data[4],teacher_number=data[5],number=data[6],max_number=data[7],username=data[0])

#修改课程信息
@alterBlue.route('/alterc/<string:coursenumber>',methods=['POST','GET'])
def alterc(coursenumber):
    try:
        session['route'] += "alC"
    except Exception:
        return redirect(url_for('login_blue.log_in'))

    order="select * from course where number=\""+coursenumber+"\";"
    Cur.execute(order)
    data=Cur.fetchall()[0]
    if request.method=="POST":
        print(request.form)
        listForm=list(request.form)
        if "upload" in listForm:
            orderD="delete from course where number=\""+coursenumber+"\";"
            Cur.execute(orderD)
            db.commit()
            orderI="insert into course values(\""+coursenumber+"\",\""+request.form.get('name')+"\",\""+\
                   request.form.get('department')+"\",\""+request.form.get('status')+"\",0,\""+request.form.get('max_number')+\
                   "\",\""+request.form.get('location')+"\",\""+request.form.get('teacher_number')+\
                   "\",\""+request.form.get('introduce')+"\","+request.form.get('type')+","+request.form.get('credits')+",\""+data[11]+"\","\
                   +request.form.get('time')+","+request.form.get('begin')+",\""+request.form.get('day')+"\","+request.form.get('grade')+\
                   ",\""+request.form.get('specialty')+"\");"
            #print(orderI)
            Cur.execute(orderI)
            db.commit()
        return redirect(url_for('courseInfo_blue.course'))

    if "username" in list(session.keys()):

        return render_template("alterc.html",number=data[0],name=data[1],department=data[2],status=data[3],
                           max_number=data[5],location=data[6],teacher_number=data[7],introduce=data[8],type=data[9],credits=data[10],
                           time=data[12],begin=data[13],day=data[14],grade=data[15],specialty=data[16])
    else:
        return redirect(url_for('login_blue.log_in'))