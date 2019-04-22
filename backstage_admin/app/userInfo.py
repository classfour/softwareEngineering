from app.__init__ import *
from werkzeug.utils import secure_filename
import time,os,xlrd,datetime
from flask import json



basedir =os.path.abspath(os.path.dirname(__file__))
ALLOWED_EXTENSIONS=set(['xlsx','xls'])

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.',1)[1] in ALLOWED_EXTENSIONS

#根据查询选择生成相关数据库查询指令
def getOrder(chosenType):
    if chosenType=='all':
        order="select * from user;"
    elif chosenType=='student':
        order="select * from user where level=0;"
    elif chosenType=='teacher':
        order="select * from user where level=1;"
    else :
        order="select * from user where level=2;"

    return order


def updateSql(dict1):
    for i in range(len(dict1['身份'])):
        if not type(dict1['用户名'][i])==type("example"):
            number=str(int(dict1['用户名'][i]))

        name=str(dict1['姓名'][i])
        sex=str(int(dict1['性别'][i]))
        Class=str(dict1['班级'][i])
        #获取当前年份的9月1日作为学生的入学时间
        date=str(datetime.datetime.now().year)+"0901000000"
        grade="0"#为新生设置初值为-1
        status="0"#为新生和新教师设置初值为0
        gpa="0"#为新生设置初值为0
        image=str(dict1['照片地址'][i])
        age=str(int(dict1['年龄'][i]))
        major=str(dict1['专业/职称'][i])

        if not dict1['教龄'][i]=='':
            workage=str(int(dict1['教龄'][i]))
        department=str(dict1['院系'][i])



        if dict1['身份'][i]=="学生":
            level="0"
            order2="insert into student values (\""+number+"\",\""+name+"\","+sex+",\""+Class+"\",\""+date+"\","\
                   +grade+","+status+","+gpa+",\""+image+"\","+age+",\""+major+"\",\""+department+"\");"

        elif dict1['身份'][i] == "教师":
            level="1"
            order2="insert into teacher values (\""+number+"\",\""+name+"\",\""+department+"\","+age+",\""+major+"\","\
                   +workage+","+sex+","+status+",\" \",\""+image+"\");"
            #可以教授的课程初始值为空

        Cur.execute(order2)
        #print(order2)

        db.commit()

        order1="insert into user values(\""+number+"\",\""+number+"\","+level+");"
        Cur.execute(order1)
        #print(order1)
        db.commit()




def getdict(tuple1):
    #print(tuple1)

    listFinal=[]

    listUserName = []
    listIdentity = []
    listName=[]


    for each in tuple1:
        listUserName.append(each[0])
        if each[2] == 0:
            sf = '学生'
            order="select name from student where number ="+"\""+each[0]+"\";"
            Cur.execute(order)
            name = Cur.fetchone()[0]


        elif each[2] == 1:
            sf = '教师'
            order = "select name from teacher where number =" + "\"" + each[0] + "\";"
            Cur.execute(order)
            name = Cur.fetchone()[0]

        else:
            sf = '管理'
            name="某管理"

        listIdentity.append(sf)
        listName.append(name)

    for i in range(len(listUserName)):

        btnstr = None
        checkstr = None
        #管理员没有修改管理员信息的权限
        if not listIdentity[i]=='管理':
            if listIdentity[i]=='教师':
                btnstr="<a href=\"/altert/"+listUserName[i]+" \" name=\""+listUserName[i]+"\">更该教师信息</a>"
            else:
                btnstr="<a href=\"/alter/"+listUserName[i]+" \" name=\""+listUserName[i]+"\">更该学生信息</a>"
                #print(btnstr)
            checkstr="<input type=\"checkbox\" name=\""+listIdentity[i]+listUserName[i]+"\"  />"



        listFinal.append({"姓名": listName[i],"用户名":listUserName[i],"身份":listIdentity[i],"选择":btnstr,
                          "<button>批量操作</button>":checkstr})



    return listFinal

#默认查询全部
chosenType="all"
order=getOrder(chosenType)
Cur.execute(order)
data=Cur.fetchall()


#使用蓝图
userInfoBlue=Blueprint('userInfo_blue',__name__)
@userInfoBlue.route('/userInfo1',methods=['POST'])
#处理查询表单
def userInfo1():
    #print(request.form)


    #若前台提交数据，则更改查询内容
    if request.method=='POST':
        chosenType=request.form['identity']

    order = getOrder(chosenType)
    Cur.execute(order)
    data=Cur.fetchall()
    #print(data)
   # print(getdict(data))
    #print(session.get['username'])
    if "username" in list(session.keys()):
        return render_template('index.html',data_1=json.dumps(getdict(data), ensure_ascii=False))
    else:
        return redirect(url_for('login_blue.log_in'))


@userInfoBlue.route('/userInfo2',methods=['POST','GET'])
#处理文件提交表单
def userInfo2():
    file_dir = os.path.join(basedir, 'upload')
    if not os.path.exists(file_dir):
        os.makedirs(file_dir)
    f = request.files['file']
    # 从表单的file字段获取文件，file为该表单的name值
    if f and allowed_file(f.filename): # 判断是否是允许上传的文件类型
        fname = secure_filename(f.filename)
       # print (fname)
       # print(type(f))
        ext = fname.rsplit('.',1)[1] # 获取文件后缀
        unix_time = int(time.time())
        new_filename = fname
        f.save(os.path.join(file_dir,new_filename)) #保存文件到upload目录

        #print(f)
        path_now=os.path.abspath('.')
        path=path_now+"\\app\\upload\\"+fname
        readbook = xlrd.open_workbook(path)
        sheet = readbook.sheet_by_index(0)  # 读取sheet1

        #处理从前端接受的数据
        dictfile = {}
        for j in range(sheet.ncols):
            list1 = []
            for i in range(sheet.nrows):
                rowvalue = sheet.row_values(i)
                list1.append(rowvalue[j])
            #print(list1)
            head = list1.pop(0)
            # print(head)
            dictfile[head] = list1
       # print(dictfile)
        updateSql(dictfile)

        error ="文件上传"
    else:
        error="请选择.xlsx或.xls文件"

    if "username" in list(session.keys()):
        return render_template('index.html',data_1=json.dumps(getdict(data), ensure_ascii=False))
    else:
        return redirect(url_for('login_blue.log_in'))


@userInfoBlue.route('/userInfo3',methods=['POST','GET'])
def userInfo3():
#处理选择操作表单
    username=None
    if request.method=="POST":
      #  print(list(request.form))

        list1=list(request.form)
        #print(list1)
        if list1==[]:
            return redirect(url_for("index_blue.hello_world",username=session['username']))
        else:
            #建立学生和教师列表来存储数据
            listS=[]
            listT=[]
            #判断所选是否均为学生
            for each in list1:
                if each[:2]=="学生":
                    listS.append(each[2:])
                else:
                    listT.append(each[2:])
    #print(listS,listT)
    session["listS"]=listS
    session["listT"]=listT




    return redirect(url_for("alter_blue.operate"))

#退出登陆
@userInfoBlue.route('/log_out')
def log_out():
    session.pop("username")
    session.pop("password")
    return redirect(url_for('login_blue.log_in'))


@userInfoBlue.route('/userInfo/<string:username>',methods=['POST','GET'])
def userInfo(username):
    error=None
    return redirect(url_for("index_blue.hello_world",username=username))
    #return render_template('index.html',data_1=json.dumps(getdict(data), ensure_ascii=False),error=error)