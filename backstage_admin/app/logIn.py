from app.__init__ import *
#使用蓝图
from flask import current_app#当前app
from datetime import timedelta
loginBlue=Blueprint('login_blue',__name__)
@loginBlue.route('/log_in',methods=['POST','GET'])
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
			session.permanent=True
			current_app.permanent_session_lifetime=timedelta(minutes=15)
			session['username']=username
			session['password']=password
			error="跳转页面"
			adminName=username
			print(adminName)
			return redirect(url_for("index_blue.hello_world",username=username))



	#在数据库中查找用户的密码匹配

	return render_template('log_in.html', error=error)

