#引入manager内容
from manager import *
from flask import Blueprint

#连接本地数据库并创建数据库指针Cur
import pymysql
db=pymysql.connect("localhost", "root", "yang1998", "edu", charset='utf8' )
Cur=db.cursor()
