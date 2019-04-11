from app.__init__ import *
#使用蓝图
courseInfoBlue=Blueprint('courseInfo_blue',__name__)
@courseInfoBlue.route('/backstage_admin/courseInfo',methods=['POST','GET'])
def courseInfo():
    return "对课程表数据的操作"