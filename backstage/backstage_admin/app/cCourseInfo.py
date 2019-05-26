from app.__init__ import *
#使用蓝图
cCourseInfoBlue=Blueprint('cCourseInfo_blue',__name__)
@cCourseInfoBlue.route('/backstage_admin/cCourseInfo',methods=['POST','GET'])
def cCourseInfo():
    return "对选课表数据的操作"