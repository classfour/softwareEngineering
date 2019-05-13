from app.__init__ import *
#使用蓝图
from flask import json
from werkzeug.utils import secure_filename
import time,os,xlrd,datetime

def toStatus(status):
    if type(status)==type("status"):
        if status=="是":
            return 1
        else:
            return 0
    else:
        if status==1:
            return "是"
        else:
            return "否"



subjectInfoBlue=Blueprint('subjectInfo_blue',__name__)
@subjectInfoBlue.route('/subject',methods=['POST','GET'])
def subjectInfo():
    try:
        session['route'] += "sub"
    except Exception:
        return redirect(url_for('login_blue.log_in'))
    orderselect = "select * from graduation_subject;"#默认查询全部

    order1 = "select content from operation where type=2 order by time DESC ;"
    Cur.execute(order1)
    try:
        operate = Cur.fetchone()[0]

        if operate == "open":
            operate = "关闭学生课题选择"
        else:
            operate = "打开学生课题选择"
    except Exception:
        operate = "打开学生课题选择"

    if request.method=="POST":
        #print(request.form)
        listForm=list(request.form)
        operation=""
        orderD=""

        if "shifton" in listForm :
            order="update graduation_subject set status=1 where serialnumber in ("
            operation = "piliang"
        elif "shiftoff" in listForm:
            order="update graduation_subject set status=0 where serialnumber in ("
            operation = "piliang"
        elif "delete" in listForm:
            orderD="delete from subject_results where course_number in ("
            order="delete from graduation_subject where serialnumber in ("
            operation = "piliang"

        if operation=="piliang":
            for each in listForm:
                if each[:6]=="select":
                    orderOperation = "insert into operation (people,type,content,time) values (\"" + session[
                        'username'] + "\",0,\"" + "审核课题" + each[6:] + "\"," + "CURRENT_TIMESTAMP" + ");"
                    Cur.execute(orderOperation)
                    db.commit()

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
        else:
            for each in listForm:
                if each[:7]=="teacher":
                    orderselect="select * from graduation_subject where teacher_number=\""+each[7:]+"\";"


    Cur.execute(orderselect)
    dataSubject=Cur.fetchall()
    #print(dataSubject)
    dataS=[]
    for each in dataSubject:

        checkstr = "<input type=\"checkbox\" name=\"select" + each[0]  + "\"  />"

        astr="<a href=\"alters/"+each[0]+"\">"+each[0]+"</a>"

        dataS.append({"批量操作":checkstr,"课题编号":astr,"课题名称":each[1],"课程介绍":each[2],"是否开启":toStatus(each[3]),"教师可选人数":
                      each[4],"教师编号":"<button name=\"teacher"+each[5]+"\">"+each[5]+"</button>","已选该课题人数":each[6],"可选最大人数":each[7]})

    if "username" in list(session.keys()):
        #print(session['route'][-6:-3])
        if not session['route'][-6:-3]=="adS":
            session['error']=None
            #只显示excel传入是的错误

        return render_template("subject.html",dataSubject=json.dumps(dataS,ensure_ascii=False),
                           username=session['username'],ERROR=session['error'],operate=operate)
    else:
        return redirect(url_for('login_blue.log_in'))

basedir =os.path.abspath(os.path.dirname(__file__))
ALLOWED_EXTENSIONS=set(['xlsx','xls'])
def allowed_file(filename):
    return '.' in filename and filename.rsplit('.',1)[1] in ALLOWED_EXTENSIONS

@subjectInfoBlue.route('/insertSubject',methods=['POST','GET'])
def insertSubject():
    try:
        session['route'] += "adS"
    except Exception:
        return redirect(url_for('login_blue.log_in'))

    session['error'] = None
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

        for i in range(sheet.nrows-1):

            if not type(dictfile['教师编号'][i]) == type("example"):
                dictfile['教师编号'][i] = str(int(dictfile['教师编号'][i]))
            if not type(dictfile['课程编号'][i]) == type("example"):
                dictfile['课程编号'][i]= str(int(dictfile['课程编号'][i]))

            try:
                orderInsert = "insert into graduation_subject (serialnumber,name, introduce,status,max,teacher_number,number,max_number) values (\""\
                              +dictfile['课程编号'][i]+"\",\""+ dictfile["课题名称"][i]+"\",\""+dictfile["课题介绍"][i]+"\","+"0,"+str(int(dictfile["教师最多选择人数"][i]))+\
                              ",\""+dictfile['教师编号'][i]+"\","+"0,"+str(int(dictfile["最多选择人数"][i]))+");"
                Cur.execute(orderInsert)
                db.commit()

                orderOperation = "insert into operation (people,type,content,time) values (\"" + session[
                    'username'] + "\",0,\"" + "添加课题" + dictfile['课程编号'] + "\"," + "CURRENT_TIMESTAMP" + ");"
                Cur.execute(orderOperation)
                db.commit()

            except Exception:
                str1="编号为"+dictfile['课程编号'][i]+"的课题与数据库有冲突,"
                if session['error']==None:
                    session['error']=str1
                else:
                    session['error']+=str1

                continue

    return redirect(url_for("subjectInfo_blue.subjectInfo"))

@subjectInfoBlue.route('/choose_subject',methods=['POST','GET'])
def choose_subject():
    try:
        session['route'] += "chS"
    except Exception:
        return redirect(url_for('login_blue.log_in'))
    order="select * from choose_subject ;"
    Cur.execute(order)
    data=Cur.fetchall()
    listData=[]
    for each in data:
        listData.append({"学生学号":each[1],"教师工号":each[2],"课题选择状态":toStatus(each[3])})

    return render_template("choose_subject.html",dataS=json.dumps(listData,ensure_ascii=False))
