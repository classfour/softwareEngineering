from app.__init__ import *
#使用蓝图
from flask import json
from werkzeug.utils import secure_filename
import time,os,xlrd,datetime
from app.subjectInfo import toStatus

def toStr(str1):
    if not type(str1)==type("str1"):
        if str1%1==0:
            str1=str(int(str1))
        else:
            str1=str(str1)
    return str1

def toGrade(str1):
    if str1=="大一":
        return "0"
    elif str1=="大二":
        return "1"
    elif str1=="大三":
        return "2"
    elif str1=="大四":
        return "3"


#使用蓝图
courseInfoBlue=Blueprint('courseInfo_blue',__name__)

@courseInfoBlue.route('/course',methods=['POST','GET'])
def course():
    session['route']+="cou"

    if request.method=="POST":
        #print(request.form)
        listForm=list(request.form)
        orderD=""

        if "shifton" in listForm :
            order="update course set status=1 where number in ("
        elif "shiftoff" in listForm:
            order="update course set status=0 where number in ("
        elif "delete" in listForm:
            orderD="delete from choose_course where course_number in ("
            order="delete from course where number in ("


        for each in listForm:
            if each[:6]=="select":
                order+="\""+each[6:]+"\","
                orderD+="\""+each[6:]+"\","

        order=order[:-2]+"\");"
        orderD=orderD[:-2]+"\");"
        #print(order)

        if len(listForm)>1:
            if "delete" in listForm:
                Cur.execute(orderD)
                db.commit()


            Cur.execute(order)
            db.commit()


    order="select * from course;"

    Cur.execute(order)
    dataCourse=Cur.fetchall()
    dataS=[]
    for each in dataCourse:

        checkstr = "<input type=\"checkbox\" name=\"select" + each[0]  + "\"  />"

        astr="<a href=\"alterc/"+each[0]+"\">"+each[0]+"</a>"

        dataS.append({"批量操作":checkstr,"课程编号":astr,"课程名称":each[1],"开设学院":each[2],"是否开启":toStatus(each[3]),"可选人数":
                      each[5],"已选该课题人数":each[4],"教师编号":"<a href=\"altert/"+each[7]+"\">"+each[7]+"</a>"
                      })

    #print(session['route'][-6:-3])
    if not session['route'][-6:-3]=="adC":
        session['error']=None
    #print(dataS)

    return render_template("course.html",dataCourse=json.dumps(dataS,ensure_ascii=False),
                           username=session['username'],ERROR=session['error'])

basedir =os.path.abspath(os.path.dirname(__file__))
ALLOWED_EXTENSIONS=set(['xlsx','xls'])
def allowed_file(filename):
    return '.' in filename and filename.rsplit('.',1)[1] in ALLOWED_EXTENSIONS

@courseInfoBlue.route('/insertCourse',methods=['POST','GET'])
def insertCourse():
    session['route']+="adS"

    file_dir = os.path.join(basedir, 'upload')
    if not os.path.exists(file_dir):
        os.makedirs(file_dir)
    f = request.files['file1']

    # 从表单的file字段获取文件，file为该表单的name值
    if f and allowed_file(f.filename):  # 判断是否是允许上传的文件类型
        fname = secure_filename(f.filename)
        # print (fname)
        # print(type(f))

        new_filename = fname
        f.save(os.path.join(file_dir, new_filename))  # 保存文件到upload目录

        # print(f)
        path_now = os.path.abspath('.')
        path = path_now + "\\app\\upload\\" + fname
        readbook = xlrd.open_workbook(path)
        sheet = readbook.sheet_by_index(0)  # 读取sheet1
        dictfile = {}
        for j in range(sheet.ncols):
            list1 = []
            for i in range(sheet.nrows):
                rowvalue = sheet.row_values(i)
                list1.append(rowvalue[j])
            #print(list1)
            head = list1.pop(0)
            #print(head)
            dictfile[head] = list1
        #print(dictfile)

        session['error']=None
        try:
            for i in range(sheet.nrows-1):
                #print(i)
                order="insert into course values (\""+toStr(dictfile['课程号'][i])+"\",\""+dictfile['课程名'][i]+"\",\""+\
                            dictfile['开设院系'][i]+"\",0,0,"+toStr(dictfile['可选人数'][i])+",\""+dictfile['上课地点'][i]+"\",\""+toStr(dictfile['教师工号'][i])\
                            +"\",\""+dictfile['课程介绍'][i]+"\","+str(toStatus(dictfile['是否为实验课'][i]))+","+toStr(dictfile['学分'][i])+",\"0000000\","+toStr(dictfile['学时'][i])\
                            +","+toStr(dictfile['起始周'][i])+",\""+dictfile['上课时间'][i]+"\",\""+toGrade(dictfile['年级'][i])+"\",\""\
                            +dictfile['专业'][i]+"\");"

                #print(order)
                Cur.execute(order)
                db.commit()
        except Exception:
            session['error']="请正确填写文件信息"

    return redirect(url_for("courseInfo_blue.course"))