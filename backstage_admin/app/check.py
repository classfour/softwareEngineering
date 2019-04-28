from app.__init__ import *
checkBlue=Blueprint('check_blue',__name__)

@checkBlue.route("/check",methods=["POST","GET"])
def cheack():
    pass
@checkBlue.route("/checkuser",methods=["POST","GET"])
def checkuser():#检查学生，教师和用户表之间冲突
    pass
