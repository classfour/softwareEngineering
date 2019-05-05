from app.__init__ import *
from flask import json
#使用蓝图
from app.subjectInfo import toStatus

complaintBlue=Blueprint('complaint_blue',__name__)
@complaintBlue.route('/complaint',methods=['POST','GET'])
def complaintInfo():
    order="select complaint.student_number,complaint.reason,complaint.status,subject_results.title,subject_results.course_number" \
          " from complaint inner join subject_results on complaint.student_number=subject_results.student_number;"
    Cur.execute(order)
    data=Cur.fetchall()
    dataC=[]
    for each in data:
        order="select teacher_number from graduation_subject where serialnumber=\""+ each[4]+"\";"
        Cur.execute(order)

        try:
            dataT=Cur.fetchone()[0]
            dataC.append(
                {"学生学号": each[0], "申诉原因": each[1], "申诉状态": each[2], "课题编号": each[4], "课题标题": each[3], "教师编号": dataT})
        except Exception:#查询不到则删除这条申诉
            order="delete from complaint where student_number=\""+each[0]+"\";"
            Cur.execute(order)
            db.commit()

    #print(dataC)
    return render_template("complaint.html",dataC=json.dumps(dataC, ensure_ascii=False))