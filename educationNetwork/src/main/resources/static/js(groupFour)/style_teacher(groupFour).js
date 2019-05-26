window.onload=function () {
    objs=document.getElementById("course_select").getElementsByTagName("option");
    var len=objs.length;
    var s=objs[len-1].text;
    for(var i=0;i<len-1;i++){
        if(s==objs[i].text){
            objs[i].selected=true;
            break;
        }
    }
}

function get_select_course() {
    objs=document.getElementById("course_select").getElementsByTagName("option");
    var len=objs.length;
    return objs[len-1].text;
}

