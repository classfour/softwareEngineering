from app.__init__ import *
#使用蓝图
from app.userInfo import userInfo


username=None

alterBlue=Blueprint('alter_blue',__name__)



@alterBlue.route('/alter/<string:username>',methods=['POST','GET'])
def alter(username):

    if request.method=="POST":
        print(request.form)
        error=list(request.form)[-1]
        #print(error)
        return redirect(url_for('userInfo_blue.userInfo',username=session['username']))


    #print("there")
    if "username" in list(session.keys()):
        return render_template("alter.html",username=username)
    else:
        return redirect(url_for('login_blue.log_in'))