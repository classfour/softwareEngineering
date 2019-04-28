from app.__init__ import *
#使用蓝图
from flask import json
roomInfoBlue=Blueprint('roomInfo_blue',__name__)
@roomInfoBlue.route('/<string:username>/room',methods=['POST','GET'])
def roomInfo(username):
    if not username==session['username']:
        return redirect(url_for("index_blue.hello_world", username=username))
    else:
        order="select * from classroom ;"
        Cur.execute(order)
        roomData=Cur.fetchall()
        dataR=[]
        for each in roomData:
            dataR.append({"教室编号":each[0],"教室容量":each[1],"教室名称":each[3],"教室容量":
                "<a href=\"/room/"+each[2]+"\">点击查看占用情况</a>"})
        print(dataR)
        return render_template("room.html",username=username,roomData=json.dumps(dataR,ensure_ascii=False))


@roomInfoBlue.route('/room/<string:used>',methods=['POST','GET'])
def roomUsed(used):
    pass