from app.__init__ import *
#使用蓝图
complaintBlue=Blueprint('complaint_blue',__name__)
@complaintBlue.route('/backstage_admin/complaintInfo',methods=['POST','GET'])
def complaintInfo():
    return "对申诉表进行操作"