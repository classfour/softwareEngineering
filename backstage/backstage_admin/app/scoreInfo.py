from app.__init__ import *
#使用蓝图
scoreInfoBlue=Blueprint('scoreInfo_blue',__name__)
@scoreInfoBlue.route('/backstage_admin/scoreInfo',methods=['POST','GET'])
def scoreInfo():
    return "对成绩表数据的操作"