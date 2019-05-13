function update(){
    $.post(
        "update",
        {"userId":30,"username":"sunpeng.guo"},
        function(response){
            if(response.success){
                alert(response.message);
            }
        },
        "json"
    )
}