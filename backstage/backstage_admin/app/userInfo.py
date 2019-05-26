from app.__init__ import *
#使用蓝图
userInfoBlue=Blueprint('userInfo_blue',__name__)
@userInfoBlue.route('/backstage_admin/userInfo',methods=['POST','GET'])
def userInfo():
    return "对用户表数据的操作"