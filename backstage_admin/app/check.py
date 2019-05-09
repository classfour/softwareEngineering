from app.__init__ import *
checkBlue=Blueprint('check_blue',__name__)



@checkBlue.route("/check",methods=["POST","GET"])
def check():
    try:
        session['route']+="che"
    except Exception:
        return redirect(url_for('login_blue.log_in'))


    if "username" in list(session.keys()):
        return render_template("check.html",dataUser=session['infoU'],dataSubject=session['infoS']
                               ,dataCourse=session['infoC'],ERROR=session['error'])

    else:
        return redirect(url_for('login_blue.log_in'))

@checkBlue.route("/checkUser",methods=["POST","GET"])
def checkUser():#检查学生，教师和用户表之间冲突


    session['infoU']=[]
    orderU="select * from user;"
    Cur.execute(orderU)
    listU=Cur.fetchall()
    list1=[]
    for each in listU:
        list1.append([each[0],each[2]])

    listU=list1

    orderT="select number from teacher;"
    Cur.execute(orderT)
    listT=Cur.fetchall()

    orderS="select number from student;"
    Cur.execute(orderS)
    listS=Cur.fetchall()
    #print(listU,listT,listS)

    for eachS in listS:
        if not [eachS[0],0] in listU:
            orderS="insert into user values ("+"\""+eachS[0]+"\",\""+eachS[0]+"\",0);"
            message="为学生"+eachS[0]+"创建账户"
            session['infoU'].append(message)
            #print(orderS)

            Cur.execute(orderS)
            db.commit()

    for eachT in listT:
        if not [eachT[0],1] in listU:
            orderT="insert into user values ("+"\""+eachT[0]+"\",\""+eachT[0]+"\",1);"
            message = "为教师" + eachS[0] + "创建账户"
            session['infoU'].append(message)
            #print(orderT)
            Cur.execute(orderT)
            db.commit()

    for eachU in listU:
        if eachU[1]==1 and not (eachU[0],) in listT:
            message="请在数据库中尽快补充编号为"+eachU[0]+"的教师数据"
            session['infoU'].append(message)
            #print(message)

        elif eachU[1]==0 and not (eachU[0],) in listS:
            message ="请在数据库中尽快补充编号为"+eachU[0]+"的学生数据"
            session['infoU'].append(message)
            #print(message)


    #print(session['infoU'])
    return redirect(url_for("check_blue.check"))

@checkBlue.route("/checkSubject",methods=["POST","GET"])
def checkSubject():#检测课题表的冲突
    session['infoS']=[]
    orderC="select course_number from subject_results;"
    Cur.execute(orderC)
    dataC=Cur.fetchall()

    orderS="select serialnumber from graduation_subject;"
    Cur.execute(orderS)
    dataS=Cur.fetchall()

    orderT="select teacher_number from graduation_subject;"
    Cur.execute(orderT)
    dataT=Cur.fetchall()

    orderU="select number from teacher ;"
    Cur.execute(orderU)
    dataU=Cur.fetchall()

    order="select student_number from complaint;"
    Cur.execute(order)
    data1=Cur.fetchall()

    order="select student_number from subject_results;"
    Cur.execute(order)
    data2=Cur.fetchall()

    #print(dataC,dataS,dataT,dataU)
    for each in dataC:
        if not each in dataS:
            message="请尽快在课题表中补充课题号为"+each[0]+"的详细信息"
            session['infoS'].append(message)

    for each in dataT:
        if not each in dataU:
            message="请尽快在教师表中补充工号为"+each[0]+"的详细信息"
            session['infoS'].append(message)

    for each in data1:
        if not each in data2:
            message="对于学生号为"+each[0]+"的学生，申诉表与课题选择表产生冲突"
            session['infoS'].append(message)

    #print(session['infoS'])
    return redirect(url_for("check_blue.check"))

@checkBlue.route("/checkCourse",methods=["POST","GET"])
def checkCourse():
    session['infoC']=[]
    order="select number from course;"
    Cur.execute(order)
    data1=Cur.fetchall()

    order="select course_number from choose_course;"
    Cur.execute(order)
    data2=Cur.fetchall()

    #print(data1,data2)

    for each in data2:
        if not (each[0],) in data1:
            message = "请尽快在课题表中补充课程号为" + each[0] + "的详细信息"
            session['infoC'].append(message)

    return redirect(url_for("check_blue.check"))