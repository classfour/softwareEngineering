from app.__init__ import *
#使用蓝图
labelInfoBlue=Blueprint('labelInfo_blue',__name__)
@labelInfoBlue.route('/backstage_admin/labelInfo',methods=['POST','GET'])
def labelInfo():
    return "对标签表数据的操作"