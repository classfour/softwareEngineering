
window.onload=function(){
    var objs=document.getElementsByTagName("span");
    for(var i=0;i<objs.length;i++){
        objs[i].id="p"+i;
        var grade=document.getElementById("p"+i).innerHTML;
        if(grade>=95){
            document.getElementById("p"+i).className="round A";
            document.getElementById("p"+i).innerHTML="A+";
        }
        else if(grade>=90&&grade<=94){
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
}
