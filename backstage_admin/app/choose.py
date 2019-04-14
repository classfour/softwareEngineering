from app.__init__ import *
#使用蓝图
chooseBlue=Blueprint('choose_blue',__name__)
@chooseBlue.route('/choose',methods=['POST','GET'])
def choose():
    return render_template("choose.html")