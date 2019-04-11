from app.__init__ import *
#使用蓝图
chooseBlue=Blueprint('choose_blue',__name__)
@chooseBlue.route('/backstage_admin/choose',methods=['POST','GET'])
def choose():
    return "这里可以选择对什么数据进行操作"