from app.__init__ import *
#使用蓝图
loginBlue=Blueprint('login_blue',__name__)
@loginBlue.route('/backstage_admin/log_in',methods=['POST','GET'])
def log_in():
	error=None
	#print(request.method)

	if request.method == 'POST':

		username = request.form.get('username')
		password = request.form.get('password')

		# 查找相应的密码
		if not username == None:
			order = "select password from user where username=\'" + username + "\' and level=2;"
			Cur.execute(order)
			result = Cur.fetchone()
		# print(result)
		else:
			result = None
		#print(result,request.form)
		if result == None or not result[0]==password:
			error="用户名或密码错误"
		else:
			error="跳转页面"



	#在数据库中查找用户的密码匹配

	return render_template('log_in.html', error=error)

