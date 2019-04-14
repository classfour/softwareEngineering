from app.__init__ import *
#使用蓝图
noticeInfoBlue=Blueprint('noticeInfo_blue',__name__)
@noticeInfoBlue.route('/noticeInfo',methods=['POST','GET'])
def noticeInfo():
    return "对通知表数据的操作"