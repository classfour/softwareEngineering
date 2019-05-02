window.onload=function(){
    var objs=document.getElementById("p").getElementsByTagName("span");
    for(var i=0;i<objs.length;i++){
        objs[i].id="p"+i;
        var grade=document.getElementById("p"+i).innerHTML;
        grade=parseInt(grade+0.5);
        if(grade>=95){
            document.getElementById("p"+i).className="round A";
            document.getElementById("p"+i).innerHTML="A+";
        }
        else if(grade>=89&&grade<=94){
            document.getElementById("p"+i).className="round A";
            document.getElementById("p"+i).innerHTML="A";
        }
        else if(grade>=85&&grade<=89){
            document.getElementById("p"+i).className="round A";
            document.getElementById("p"+i).innerHTML="A-";
        }
        else if(grade>=82&&grade<=84){
            document.getElementById("p"+i).className="round B";
            document.getElementById("p"+i).innerHTML="B+";
        }
        else if(grade>=78&&grade<=81){
            document.getElementById("p"+i).className="round B";
            document.getElementById("p"+i).innerHTML="B";
        }
        else if(grade>=75&&grade<=77){
            document.getElementById("p"+i).className="round B";
            document.getElementById("p"+i).innerHTML="B-";
        }
        else if(grade>=72&&grade<=74){
            document.getElementById("p"+i).className="round C";
            document.getElementById("p"+i).innerHTML="C+";
        }
        else if(grade>=68&&grade<=71){
            document.getElementById("p"+i).className="round C";
            document.getElementById("p"+i).innerHTML="C";
        }
        else if(grade>=64&&grade<=67){
            document.getElementById("p"+i).className="round C";
            document.getElementById("p"+i).innerHTML="C-";
        }
        else if(grade>=61&&grade<=63){
            document.getElementById("p"+i).className="round D";
            document.getElementById("p"+i).innerHTML="D+";
        }
        else if(grade==60){
            document.getElementById("p"+i).className="round D";
            document.getElementById("p"+i).innerHTML="D";
        }
        else{
            document.getElementById("p"+i).className="round D";
            document.getElementById("p"+i).innerHTML="F";
        }
    }


    objs=document.getElementById("select");
    var s=document.getElementById("select")[9].text;
    for(var i=0;i<8;i++){
        if(s==objs[i].value){
            objs[i].selected=true;
            break;
        }
    }

    //蔡秉岐的js样式
    //为所有成绩明细编写动态ID
    var score_detail=document.getElementsByTagName("p");
    for(var i=0;i<score_detail.length;i++) {
        score_detail[i].id = "detail" + i;
    }
    //为查询button编写动态ID
    var button_id=document.getElementById("p").getElementsByTagName("button");
    for(var i=0;i<button_id.length;i++)
    {
        button_id[i].id="button"+i;
    }
}
//动态获取成绩放到模态框中
function show_score_detail(id) {
    var num=id[id.length-1];
    num=parseInt(num);
    var num0=num*7;
    var num1=num*7+1;
    var num2=num*7+2;
    var num3=num*7+3;
    var num4=num*7+4;
    var num5=num*7+5;
    var num6=num*7+6;
    var start_flag="detail"+num0;
    var start_flag1="detail"+num1;
    var start_flag2="detail"+num2;
    var start_flag3="detail"+num3;
    var start_flag4="detail"+num4;
    var start_flag5="detail"+num5;
    var start_flag6="detail"+num6;
    document.getElementById("normal_score").innerHTML=document.getElementById(start_flag).innerHTML;
    document.getElementById("normal_score_rate").innerHTML=document.getElementById(start_flag1).innerHTML;
    document.getElementById("mid_score").innerHTML=document.getElementById(start_flag2).innerHTML;
    document.getElementById("mid_score_rate").innerHTML=document.getElementById(start_flag3).innerHTML;
    document.getElementById("final_score").innerHTML=document.getElementById(start_flag4).innerHTML;
    document.getElementById("final_score_rate").innerHTML=document.getElementById(start_flag5).innerHTML;
    document.getElementById("total_score").innerHTML=document.getElementById(start_flag6).innerHTML;
    document.getElementById("myModal").style.display="block";
}

function GetSelect() {
    var myselect=document.getElementById("select");
    var value=myselect.options[9].text;
    return value;
}
function Gets(path) {
    var s;
    if(path=="all"){
        s="全部成绩";
    }
    else if(path=="2016-2017-1"){
        s="2016-2017学年第1学期成绩";
    }
    else if(path=="2016-2017-2"){
        s="2016-2017学年第2学期成绩";
    }
    else if(path=="2017-2018-1"){
        s="2017-2018学年第1学期成绩";
    }
    else if(path=="2017-2018-2"){
        s="2017-2018学年第2学期成绩";
    }
    else if(path=="2018-2019-1"){
        s="2018-2019学年第1学期成绩";
    }
    else if(path=="2018-2019-2"){
        s="2018-2019学年第2学期成绩";
    }
    else if(path=="2019-2020-1"){
        s="2019-2020学年第1学期成绩";
    }
    else if(path="2019-2020-2"){
        s="2019-2020学年第2学期成绩";
    }
    return s;
}