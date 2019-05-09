from app.__init__ import *
from flask import json
#使用蓝图
from app.subjectInfo import toStatus

complaintBlue=Blueprint('complaint_blue',__name__)
@complaintBlue.route('/complaint',methods=['POST','GET'])
def complaintInfo():
    try:
        session['route'] += "com"
    except Exception:
        return redirect(url_for('login_blue.log_in'))
    order="select complaint.student_number,complaint.reason,complaint.status,subject_results.title,subject_results.course_number" \
          " from complaint left join subject_results on complaint.student_number=subject_results.student_number;"
    Cur.execute(order)
    data=Cur.fetchall()
    dataC=[]
    for each in data:
        try:
            order="select teacher_number from graduation_subject where serialnumber=\""+ each[4]+"\";"
            Cur.execute(order)


            dataT=Cur.fetchone()[0]
            dataC.append(
                {"学生学号": each[0], "申诉原因": each[1], "申诉状态": each[2], "课题编号": each[4], "课题标题": each[3], "教师编号": dataT})

        except Exception:
            session['error']="申诉表和课题选择表信息出现冲突，请查询并尽快在数据库中修复"
            return redirect(url_for("check_blue.check"))

    #print(dataC)
    return render_template("complaint.html",dataC=json.dumps(dataC, ensure_ascii=False))