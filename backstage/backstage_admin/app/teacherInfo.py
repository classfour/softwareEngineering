from app.__init__ import *
#使用蓝图
teacherInfoBlue=Blueprint('teacherInfo_blue',__name__)
@teacherInfoBlue.route('/backstage_admin/teacherInfo',methods=['POST','GET'])
def teacherInfo():
    return "对教师表数据的操作"