from app.__init__ import *
#使用蓝图
roomInfoBlue=Blueprint('roomInfo_blue',__name__)
@roomInfoBlue.route('/backstage_admin/roomInfo',methods=['POST','GET'])
def roomInfo():
    return "对教室表数据的操作"