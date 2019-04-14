from app.__init__ import *

indexBlue=Blueprint('index_blue',__name__)
@indexBlue.route('/')
def hello_world():
    return render_template("index.html")