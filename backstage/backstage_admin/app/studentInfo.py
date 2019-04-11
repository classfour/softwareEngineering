from app.__init__ import *
#使用蓝图
studentInfoBlue=Blueprint('studentInfo_blue',__name__)
@studentInfoBlue.route('/backstage_admin/studentInfo',methods=['POST','GET'])
def studentInfo():
    return "对学生表数据的操作"