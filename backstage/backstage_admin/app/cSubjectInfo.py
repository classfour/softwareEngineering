from app.__init__ import *
#使用蓝图
cSubjectInfoBlue=Blueprint('cSubjectInfo_blue',__name__)
@cSubjectInfoBlue.route('/backstage_admin/cSubjectInfo',methods=['POST','GET'])
def cSubjectInfo():
    return "对选择课题表数据的操作"