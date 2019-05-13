from app.__init__ import *
#使用蓝图
from flask import json

def toJson(tuple1):
    listFinal=[]
    for each in tuple1:
        checkstr="<input type=\"checkbox\" name=\""+str(each[0])+"\"  />"
        titlestr = "<a href=\"/shownotice/" + str(each[0]) + " \""  + ">"+each[1]+"</a>"

        if each[4]==0:
            upstr="<button class=\"btn btn-default\" name=\"shiftu"+str(each[0])+"\">置顶</button>"
        else:
            upstr="<button class=\"btn btn-default\" name=\"shiftd"+str(each[0])+"\">取消置顶</button>"
        listFinal.append({"<button class=\"btn btn-default\" name=\"delete\">批量删除</button>":checkstr,"标题":
            titlestr,"发布时间":each[3].strftime("%Y-%m-%d")
,"是否置顶":upstr})
        #print(listFinal)
    return listFinal
noticeInfoBlue=Blueprint('noticeInfo_blue',__name__)
@noticeInfoBlue.route('/notice',methods=['POST','GET'])
def notice():
    if not "username" in list(session.keys()) :
        return redirect(url_for('login_blue.log_in'))
    else:




        if request.method=="POST":
            #print(request.form)
            listForm=list(request.form)
            #判断是否有选择
            if "delete" in listForm and len(listForm)>1:
                listDelete=[]
                #批量删除操作

                orderDelete="delete from notice where id in ("
                for each in listForm:
                    if not each=="delete":

                        orderOperation = "insert into operation (people,type,content,time) values (\"" + session[
                            'username'] + "\",0,\"" + "删除通知" + each + "\"," + "CURRENT_TIMESTAMP" + ");"
                        Cur.execute(orderOperation)
                        db.commit()

                        orderDelete+="\""+each+"\","
                orderDelete=orderDelete[:-1]+");"
                #print(orderDelete)
                Cur.execute(orderDelete)
                db.commit()

            for each in listForm:
                #print(each[:5])
                if each[:5]=="shift":
                    id=each[6:]
                    #print(id)
                    operation=each[5]#第五位代表操作，u为置顶，d为取消置顶
                    if operation=='u':
                        #先将所有置0，再将其置为1
                        orderShift1 = "update notice set status=0 where status=1;"
                        orderShift = "update notice set status=1 where id=" + id + ";"
                        Cur.execute(orderShift1)
                        db.commit()
                        orderOperation = "insert into operation (people,type,content,time) values (\"" + session[
                            'username'] + "\",0,\"" + "置顶通知" + id + "\"," + "CURRENT_TIMESTAMP" + ");"
                        Cur.execute(orderOperation)
                        db.commit()

                    elif operation=='d':
                        #将其置为0
                        orderShift="update notice set status=0 where id="+id+";"
                        orderOperation = "insert into operation (people,type,content,time) values (\"" + session[
                            'username'] + "\",0,\"" + "取消置顶通知" + id + "\"," + "CURRENT_TIMESTAMP" + ");"
                        Cur.execute(orderOperation)
                        db.commit()

                    Cur.execute(orderShift)
                    db.commit()
                    break

        order = "select * from notice where status=1;"
        Cur.execute(order)
        data = Cur.fetchall()
        #判断是否有置顶通知
        if data==():
            #没有
            order1="select * from notice order by time DESC;"
        else:
            order1="select * from notice where status=0 order by time DESC;"
        Cur.execute(order1)
        data1=Cur.fetchall()
        data+=data1


        return render_template("notice.html",data_1=json.dumps(toJson(data), ensure_ascii=False),username=session['username'])

@noticeInfoBlue.route('/add_notice',methods=['POST','GET'])
def noticeInsert():
    if not "username" in list(session.keys()):
        return redirect(url_for('login_blue.log_in'))
    else:
        #发布通知
        if request.method=="POST":
            #print(request.form)
            title=request.form.get("title")
            date=request.form.get("date")
            content=request.form.get("content")
            orderInsert="insert into notice (title,content,time,status)values (\""+title+"\",\""+content+"\",\""+date+"\",0);"
            #print(orderInsert)
            Cur.execute(orderInsert)
            db.commit()

            orderOperation = "insert into operation (people,type,content,time) values (\"" + session[
                'username'] + "\",0,\"" + "发布新通知" + title + "\"," + "CURRENT_TIMESTAMP" + ");"
            Cur.execute(orderOperation)
            db.commit()

            return redirect(url_for("noticeInfo_blue.notice", username=session['username']))
        return render_template("add_notice.html")

@noticeInfoBlue.route('/shownotice/<string:number>',methods=['POST','GET'])
def shownotice(number):
    order="select * from notice where id="+number+";"
    Cur.execute(order)
    data=Cur.fetchone()
    return render_template("shownotice.html",title=data[1],content=data[2],time=data[3])
