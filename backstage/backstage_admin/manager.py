from flask import Flask,render_template,request
import pymysql
from flask_script import Manager
from app.logIn import loginBlue
from app.index import indexBlue
from app.choose import chooseBlue
from app.userInfo import userInfoBlue
from app.studentInfo import studentInfoBlue
from app.teacherInfo import teacherInfoBlue
from app.courseInfo import courseInfoBlue
from app.cCourseInfo import cCourseInfoBlue
from app.noticeInfo import noticeInfoBlue
from app.roomInfo import roomInfoBlue
from app.subjectInfo import subjectInfoBlue
from app.cSubjectInfo import cSubjectInfoBlue
from app.labelInfo import labelInfoBlue
from app.complaintInfo import complaintBlue
from app.scoreInfo import scoreInfoBlue



app = Flask(__name__)
#注册蓝图
app.register_blueprint(blueprint=loginBlue)
app.register_blueprint(blueprint=indexBlue)
app.register_blueprint(blueprint=chooseBlue)
#注册修改数据页面的蓝图
app.register_blueprint(blueprint=userInfoBlue)
app.register_blueprint(blueprint=studentInfoBlue)
app.register_blueprint(blueprint=teacherInfoBlue)
app.register_blueprint(blueprint=cCourseInfoBlue)
app.register_blueprint(blueprint=courseInfoBlue)

app.register_blueprint(blueprint=noticeInfoBlue)
app.register_blueprint(blueprint=roomInfoBlue)
app.register_blueprint(blueprint=subjectInfoBlue)
app.register_blueprint(blueprint=cSubjectInfoBlue)
app.register_blueprint(blueprint=labelInfoBlue)
app.register_blueprint(blueprint=complaintBlue)
app.register_blueprint(blueprint=scoreInfoBlue)


manager=Manager(app=app)



if __name__ == '__main__':
    manager.run()
