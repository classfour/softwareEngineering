from app.__init__ import *

indexBlue=Blueprint('index_blue',__name__)
@indexBlue.route('/backstage_admin/')
def hello_world():
    return 'Hello World!'