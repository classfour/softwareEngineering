from app.__init__ import *
#使用蓝图
subjectInfoBlue=Blueprint('subjectInfo_blue',__name__)
@subjectInfoBlue.route('/subjectInfo',methods=['POST','GET'])
def subjectInfo():
    return "对课题表数据的操作"