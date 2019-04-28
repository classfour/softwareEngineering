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

    if request.method=="POST":
        #print(request.form)
        listForm=list(request.form)
        orderD=""

        if "shifton" in listForm :
            order="update graduation_subject set status=1 where serialnumber in ("
        elif "shiftoff" in listForm:
            order="update graduation_subject set status=0 where serialnumber in ("
        elif "delete" in listForm:
            orderD="delete from subject_results where course_number in ("
            order="delete from graduation_subject where serialnumber in ("


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


    order="select * from graduation_subject;"

    Cur.execute(order)
    dataSubject=Cur.fetchall()
    #print(dataSubject)
    dataS=[]
    for each in dataSubject:

        checkstr = "<input type=\"checkbox\" name=\"select" + each[0]  + "\"  />"

        dataS.append({"批量操作":checkstr,"课题编号":each[0],"课题名称":each[1],"课程介绍":each[2],"是否开启":toStatus(each[3]),"教师可选人数":
                      each[4],"教师编号":"<a href=\"altert/"+each[5]+"\">"+each[5]+"</a>","已选该课题人数":each[6],"可选最大人数":each[7]})



    return render_template("subject.html",dataSubject=json.dumps(dataS,ensure_ascii=False),username=session['username'])

basedir =os.path.abspath(os.path.dirname(__file__))
ALLOWED_EXTENSIONS=set(['xlsx','xls'])
def allowed_file(filename):
    return '.' in filename and filename.rsplit('.',1)[1] in ALLOWED_EXTENSIONS

@subjectInfoBlue.route('/insertSubject',methods=['POST','GET'])
def insertSubject():
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
        print(dictfile)

        for i in range(sheet.nrows-1):

            if not type(dictfile['教师编号'][i]) == type("example"):
                dictfile['教师编号'][i] = str(int(dictfile['教师编号'][i]))
            if not type(dictfile['课程编号'][i]) == type("example"):
                dictfile['课程编号'][i]= str(int(dictfile['课程编号'][i]))



            orderInsert = "insert into graduation_subject (serialnumber,name, introduce,status,max,teacher_number,number,max_number) values (\""\
                          +dictfile['课程编号'][i]+"\",\""+ dictfile["课题名称"][i]+"\",\""+dictfile["课题介绍"][i]+"\","+"0,"+str(int(dictfile["教师最多选择人数"][i]))+\
                          ",\""+dictfile['教师编号'][i]+"\","+"0,"+str(int(dictfile["最多选择人数"][i]))+");"
            Cur.execute(orderInsert)
            db.commit()

    return redirect(url_for("subjectInfo_blue.subjectInfo"))