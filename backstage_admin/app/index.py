from app.__init__ import *
from app.userInfo import data,getdict
from flask import json,send_from_directory
import os
adminName=None
indexBlue=Blueprint('index_blue',__name__)



@indexBlue.route('/')
def index():
    if "username" in list(session.keys()):
        return redirect(url_for("index_blue.hello_world",username=session['username']))
    else:
        return redirect(url_for('login_blue.log_in'))

@indexBlue.route('/<string:username>')
def hello_world(username):
    session['route']+="ind"
    #print(getdict(data))
    #return redirect(url_for("userInfo_blue.userInfo"))
    #print(username)

    try :
        #若session过期则自动跳转至登陆界面
        if "username" in list(session.keys()):
            order = "select *from user;"
            Cur.execute(order)
            data = Cur.fetchall()

            if not session['route'][-6:-3]=="uI2":
                session['error']=None

            return render_template("index.html",error=None,data_1=json.dumps(getdict(data),ensure_ascii=False),
                                   username=session['username'],ERROR=session['error'])
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