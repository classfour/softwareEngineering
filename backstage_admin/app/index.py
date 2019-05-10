from app.__init__ import *
from app.userInfo import data,getdict
from flask import json,send_from_directory
import os
adminName=None
indexBlue=Blueprint('index_blue',__name__)

@indexBlue.route('/<string:str1>/<string:str2>')
def back(str1,str2):
    if "username" in list(session.keys()):
        return redirect(url_for("index_blue.hello_world", username=session['username']))
    else:
        return redirect(url_for('login_blue.log_in'))

@indexBlue.route('/new/<string:str1>')
def new(str1):
    try:
        index = str1.index("的")
    except Exception:
        return redirect(url_for("index_blue.hello_world", username=session['username']))

    if str1[0]=="学":

        return redirect(url_for('alter_blue.alter',username=str1[3:index]))
    elif str1[0]=="工":

        return redirect(url_for('alter_blue.altert',username=str1[3:index]))

    elif str1[:2]=="课程":

        return redirect(url_for('alter_blue.alterc',coursenumber=str1[4:index]))
    elif str1[:2]=="课题":

        return redirect(url_for('alter_blue.alters',subjectname=str1[4:index]))

    return redirect(url_for("index_blue.hello_world", username=session['username']))






@indexBlue.route('/search',methods=['POST','GET'])
def search():
    if "username" in list(session.keys()):
        if request.method=="POST":
            text=request.form.get('text')


            order1="select number,name from student where number like \'%"+text+"%\' or name like \'%"+text+"%\';"
            Cur.execute(order1)
            data1=Cur.fetchall()
            dataFinal=[]
            for each in data1:
                dataFinal.append("学号为"+each[0]+"的同学"+each[1])

            order2="select number,name from teacher where number like \'%"+text+"%\' or name like \'%"+text+"%\';"
            Cur.execute(order2)
            data2=Cur.fetchall()
            for each in data2:
                dataFinal.append("工号为"+each[0]+"的教师"+each[1])

            order3 = "select number,name from course where number like \'%" + text + "%\' or name like \'%" + text + "%\';"
            Cur.execute(order3)
            data3=Cur.fetchall()
            for each in data3:
                dataFinal.append("课程号为"+each[0]+"的课程"+each[1])

            order4="select serialnumber,name from graduation_subject where number like \'%" + text + "%\' or name like \'%" + text + "%\';"
            Cur.execute(order4)
            data4=Cur.fetchall()
            for each in data4:
                dataFinal.append("课题号为"+each[0]+"的课题"+each[1])


            if dataFinal==[]:
                dataFinal.append("很遗憾,未查询到"+text+"的相关信息,点击返回")
        return render_template("search.html",dataSearch=dataFinal)


    else:
        return redirect(url_for('login_blue.log_in'))


@indexBlue.route('/')
def index():
    if "username" in list(session.keys()):
        return redirect(url_for("index_blue.hello_world",username=session['username']))
    else:
        return redirect(url_for('login_blue.log_in'))

@indexBlue.route('/<string:username>')
def hello_world(username):
    try:
        session['route']+="ind"
    except Exception:
        return redirect(url_for('login_blue.log_in'))
    #print(getdict(data))
    #return redirect(url_for("userInfo_blue.userInfo"))
    #print(username)
    order = "select distinct departments from student;"
    Cur.execute(order)
    dataOpt = Cur.fetchall()
    #print(dataOpt)
    optstr = []
    for each in dataOpt:
        optstr.append(each[0])

    order="select distinct departments from teacher;"
    Cur.execute(order)
    dataOpt=Cur.fetchall()
    for each in dataOpt:
        if not each[0] in optstr:
            optstr.append(each[0])
    session['department']=optstr

    order="select distinct major from student;"
    Cur.execute(order)
    dataOpt=Cur.fetchall()
    optstr=[]
    for each in dataOpt:
        optstr.append(each[0])
    session['major']=optstr

    try :

        #若session过期则自动跳转至登陆界面
        if "username" in list(session.keys()) :
            if not username==session['username']:
                return redirect(url_for("index_blue.hello_world",username=session['username']))
            order = "select * from user;"
            Cur.execute(order)
            data = Cur.fetchall()

            if not session['route'][-6:-3]=="uI2":
                session['error']=None
            #print(optstr)


            return render_template("index.html",error=None,data_1=json.dumps(getdict(data,"all","all"),ensure_ascii=False),
                                   username=session['username'],ERROR=session['error'],department=session['department'],major=session['major'])
        else:
            return redirect(url_for('login_blue.log_in'))
    except BaseException:
        session['error']="数据库出现问题，请查看并尽快修复"
        return redirect(url_for('check_blue.check'))


@indexBlue.route("/download/<path:filename>")
def download(filename):
    dirpath = os.path.join(indexBlue.root_path, 'download')
    #print(filename,dirpath)
    return send_from_directory(dirpath, filename, as_attachment=True)