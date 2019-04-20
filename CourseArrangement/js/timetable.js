window.onload = function() {
    const initTable = () => {
        let table = document.getElementById('timetable');
        let tabletext = '';

        tabletext += '<thead><tr><th>节次</th><th>星期一</th><th>星期二</th><th>星期三</th><th>星期四</th><th>星期五</th><th>星期六</th><th>星期日</th></tr></thead>'
        tabletext += '<tbody>';
        for(var i=1; i<=13; i++){
            tabletext += '<tr>';
            tabletext += '<th>' + i + '</th>';
            for(var j=1; j<=7; j++){
                tabletext += '<th></th>'
            }
            tabletext += '</tr>';
        }
        tabletext += '</tbody>';
        table.innerHTML += tabletext;
    }
    initTable();
}