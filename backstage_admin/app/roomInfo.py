from app.__init__ import *
#使用蓝图
from flask import json
from werkzeug.utils import secure_filename
import time,os,xlrd,datetime

roomInfoBlue=Blueprint('roomInfo_blue',__name__)
@roomInfoBlue.route('/room',methods=['POST','GET'])

def room():
    try:
        session['route'] += "rom"
    except Exception:
        return redirect(url_for('login_blue.log_in'))

    if request.method=="POST":
        #print(request.form)
        listForm=list(request.form)


        if "delete" in listForm:
            order="delete from classroom where class_number in (\"-1\","
            for each in listForm:
                if each[:6]=="select":
                    order+="\""+each[6:]+"\","

            order=order[:-1]+");"
            #print(order)
            Cur.execute(order)
            db.commit()
        else:
            order="select class_number,capacity from classroom;"
            Cur.execute(order)
            dataTest=Cur.fetchall()
            for each in dataTest:
                if not str(each[1])==request.form.get(each[0]):
                    order="update classroom set capacity="+request.form.get(each[0])+" where class_number=\""+each[0]+"\";"
                    #print(order)
                    Cur.execute(order)
                    db.commit()

    order="select * from classroom ;"
    Cur.execute(order)
    roomData=Cur.fetchall()
    dataR=[]
    for each in roomData:
        checkstr = "<input type=\"checkbox\" name=\"select" + str(each[0]) + "\"  />"
        roomstr="<input type=\"text\" name=\""+str(each[0])+"\" value=\""+str(each[1])+"\">"
        dataR.append({"<button class=\"btn btn-default\" name=\"delete\">批量删除</button>":checkstr,
                      "教室编号":each[0],"教室容量":roomstr,"教室名称":each[3],"教室占用情况":
                "<a href=\"/room/"+each[2]+"\">点击查看占用情况</a>"})
    #print(dataR)

    return render_template("room.html",username=session['username'],roomData=json.dumps(dataR,ensure_ascii=False))



basedir =os.path.abspath(os.path.dirname(__file__))
ALLOWED_EXTENSIONS=set(['xlsx','xls'])
def allowed_file(filename):
    return '.' in filename and filename.rsplit('.',1)[1] in ALLOWED_EXTENSIONS

@roomInfoBlue.route('/addroom',methods=['POST','GET'])
def addroom():

    try:
        session['route'] += "adR"
    except Exception:
        return redirect(url_for('login_blue.log_in'))

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

        for i in range(sheet.nrows-1):
            if not type(dictfile['教室编号'][i])==type("classroom"):
                dictfile['教室编号'][i]=str(int(dictfile['教室编号'][i]))

            capacity=int(dictfile['教室容量'][i])
            order="insert into classroom values(\""+dictfile['教室编号'][i]+"\","+str(capacity)+",\"00000\",\""\
                  +dictfile['教室名称'][i]+"\");"
            Cur.execute(order)
            db.commit()
    return redirect(url_for("roomInfo_blue.room"))


@roomInfoBlue.route('/room/<string:used>',methods=['POST','GET'])
def roomUsed(used):
    pass