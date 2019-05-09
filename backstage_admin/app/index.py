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