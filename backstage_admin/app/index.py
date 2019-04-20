from app.__init__ import *
from app.userInfo import data,getdict
from flask import json,send_from_directory
import os
adminName=None
indexBlue=Blueprint('index_blue',__name__)

@indexBlue.route('/<string:username>')
def hello_world(username):

    #print(getdict(data))
    #return redirect(url_for("userInfo_blue.userInfo"))
    #print(username)

    #若session过期则自动跳转至登陆界面
    if "username" in list(session.keys()):
        return render_template("index.html",error=None,data_1=json.dumps(getdict(data),ensure_ascii=False),username=username)
    else:
        return redirect(url_for('login_blue.log_in'))

@indexBlue.route("/download/<path:filename>")
def download(filename):
    dirpath = os.path.join(indexBlue.root_path, 'upload')
    #print(filename,dirpath)
    return send_from_directory(dirpath, filename, as_attachment=True)