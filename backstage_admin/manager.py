from flask import Flask,render_template,request,url_for,redirect,session
from flask_script import Manager
from app.logIn import loginBlue
from app.index import indexBlue
from app.alter import alterBlue
from app.userInfo import userInfoBlue
from app.courseInfo import courseInfoBlue
from app.cCourseInfo import cCourseInfoBlue
from app.noticeInfo import noticeInfoBlue
from app.roomInfo import roomInfoBlue
from app.subjectInfo import subjectInfoBlue
from app.cSubjectInfo import cSubjectInfoBlue
from app.labelInfo import labelInfoBlue
from app.complaintInfo import complaintBlue
from app.scoreInfo import scoreInfoBlue
from app.check import checkBlue
from app.operation import operationInfoBlue
import os
from flask import send_from_directory,json
from datetime import timedelta

app = Flask(__name__)
#设置12位的密钥
app.config['SECRET_KEY'] = os.urandom(12)

#注册蓝图
dirpath = os.path.join(app.root_path, 'upload')
app.register_blueprint(blueprint=loginBlue)
app.register_blueprint(blueprint=indexBlue)
app.register_blueprint(blueprint=alterBlue)
#注册修改数据页面的蓝图
app.register_blueprint(blueprint=userInfoBlue)
app.register_blueprint(blueprint=cCourseInfoBlue)
app.register_blueprint(blueprint=courseInfoBlue)
app.register_blueprint(blueprint=noticeInfoBlue)
app.register_blueprint(blueprint=roomInfoBlue)
app.register_blueprint(blueprint=subjectInfoBlue)
app.register_blueprint(blueprint=cSubjectInfoBlue)
app.register_blueprint(blueprint=labelInfoBlue)
app.register_blueprint(blueprint=complaintBlue)
app.register_blueprint(blueprint=scoreInfoBlue)
app.register_blueprint(blueprint=checkBlue)
app.register_blueprint(blueprint=operationInfoBlue)

#创建代理manage
manager=Manager(app=app)

#用app提供文件下载
@app.route("/download/<path:filename>")
def downloader(filename):
    return send_from_directory(dirpath, filename, as_attachment=True)

if __name__ == '__main__':
    manager.run()

    debug = True
