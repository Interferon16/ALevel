function openInsert(element_id) {
    var cell = document.getElementById(element_id);
    var temp_width = cell.offsetWidth - 4;
    var temp_height = cell.offsetHeight - 6;
    var tempClass = cell.getAttribute("class");
    var tempId = cell.getAttribute("id");
    var tempValue = cell.getAttribute("data-formula");
    if (!tempValue || tempValue == 0) {
        tempValue = "";
    }
    var newInput = document.createElement("input");
    newInput.setAttribute("class", tempClass);
    newInput.setAttribute("id", tempId);
    newInput.setAttribute("value", tempValue);
    if(tempClass==="cell"||tempClass==="activecell"){
        newInput.setAttribute("onblur", "getBlur(this.id,true)");
        newInput.setAttribute("onkeyup", "tapEnter(event,this.id,true)");
    }else{
        newInput.setAttribute("onblur", "getBlur(this.id)");
        newInput.setAttribute("onkeyup", "tapEnter(event,this.id)");
    }
    newInput.setAttribute("style", "width: " + temp_width + "px; height: " + temp_height + "px");
    cell.parentNode.insertBefore(newInput, cell);
    cell.parentNode.removeChild(cell);
    newInput.focus();
}

function closeInsert(element_id) {
    var cell = document.getElementById(element_id);
    var tempClass = cell.getAttribute("class");
    var tempId = cell.getAttribute("id");
    var tempValue = cell.value;
    var newCell = document.createElement("td");
    newCell.setAttribute("class", tempClass);
    newCell.setAttribute("id", tempId);
    if (tempValue.charAt(0) == "+") {
        tempValue = tempValue.replace("+", "=");
    }
    if (!tempValue || tempValue == "0") {
        newCell.innerText = "";
    } else {
        newCell.innerText = parceEquation(tempValue);
    }
    newCell.setAttribute("data-formula", tempValue);
    newCell.setAttribute("ondblclick", "openInsert(this.id)");
    if(tempClass==="cell"||tempClass==="activecell") {
        newCell.setAttribute("onclick", "focusAndGetFormula(this.id,true)");
    }
    cell.parentNode.insertBefore(newCell, cell);
    cell.parentNode.removeChild(cell);
}

function parceEquation(equatin) {
    if (equatin.charAt(0) === "=") {
        var temp_equation = equatin.slice(1);
        var cellname = /([a-zа-яё]+\d+)/gi;

        temp_equation = temp_equation.replace(cellname, replaceByCellId);
        //eval error -------------------------
        try {
            eval(temp_equation);
        } catch (e) {
            return temp_equation;
        }
        //eval error -------------------------
        return eval(temp_equation);
    }

    else {
        return equatin;
    }
}

function replaceByCellId(match) {
    try {
        var a = document.getElementById(match).innerText;
    } catch (e) {
        a = "#ССЫЛКА";
    }
    a = a == "" ? 0 : a;
    return a;
}

function addDownRow(cellid) {
    var cell = document.getElementById(cellid);
    var parent_row = cell.parentNode;
    var parent_table = parent_row.parentNode.parentNode;
    var new_row = document.createElement("tr");
    var new_row_num = Number((parent_row.id.split("-"))[1]) + 1;
    var cell_numbers = parent_row.cells.length;
    for (var i = 0; i < cell_numbers; i++) {
        var old_cell = parent_row.cells[i];
        var new_cell = document.createElement("td");
        new_row.appendChild(new_cell);
    }
    insertAfter(new_row, parent_row);
    refreshRowId(parent_table, parent_row);
}

function insertAfter(newNode, referenceNode) {
    referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);
}

function createButton(inner_text, title) {
    var button = document.createElement("button");
    button.setAttribute("type", "button");
    button.setAttribute("class", "clickbutton");
    button.innerText = inner_text;
    button.setAttribute("title", title);
    return button;
}

function refreshRowId(table, parent_row) {
    var row_number = parent_row.sectionRowIndex;
    for (var i = row_number; i < table.rows.length; i++) {
        var current_row = table.rows[i];
        current_row.setAttribute("class", "row");
        current_row.setAttribute("id", ("row-" + current_row.rowIndex));
        for (var j = 0; j < current_row.cells.length; j++) {
            var current_row_number = current_row.sectionRowIndex;
            var current_cell = current_row.cells[j];
            if (j == 0) {
                current_cell.setAttribute("class", "headcell");
                current_cell.innerText = current_row_number;
                var plus_button = createButton("+", "Добавить строку визу");
                plus_button.setAttribute("onclick", "addDownRow(this.parentNode.id)");
                var minus_button = createButton("-", "Удалить текущую строку");
                minus_button.setAttribute("onclick", "delThisRow(this.parentNode.id)");
                current_cell.appendChild(plus_button);
                current_cell.appendChild(minus_button);
                current_cell.setAttribute("id", current_row_number);
            } else {

                var current_cell_id = String.fromCharCode(96 + j) + current_row_number;
                current_cell.setAttribute("class", "cell");
                current_cell.setAttribute("id", current_cell_id);
                current_cell.setAttribute("ondblclick", "openInsert(this.id)");
                current_cell.setAttribute("onclick", "focusAndGetFormula(this.id)");
            }
        }
    }
}

function getBlur(element_id, withFormula) {
    closeInsert(element_id);
    if(!!withFormula){
        getFormula(element_id);
    }
}

function tapEnter(key, element_id, withFormula) {
    if (key.keyCode === 13) {
        document.getElementById(element_id).removeAttribute("onblur");
        closeInsert(element_id);
        if(!!withFormula){
            getFormula(element_id);
        }
    }
}

function focusAndGetFormula(element_id) {
    try {
        document.getElementsByClassName("activecell")[0].setAttribute("class", "cell");
    } catch (e) {
    }
    document.getElementById(element_id).setAttribute("class", "activecell");
    getFormula(element_id)
}

function getFormula(element_id) {
    var formula = document.getElementById(element_id).getAttribute("data-formula");
    formula = formula == null ? "" : formula;
    document.getElementsByClassName("formulafield")[0].innerText = formula;
}

function delThisRow(cellid) {
    var row = document.getElementById(cellid).parentNode;
    var parent_table = row.parentNode.parentNode;
    if (parent_table.rows.length > 2) {
        var parent_row = row.nextSibling;
        row.parentNode.removeChild(row);
        refreshRowId(parent_table, parent_row);
    }
}

function addColumn(cell_id) {
    var cell = document.getElementById(cell_id);
    var parent_table = cell.parentNode.parentNode.parentNode;
    var parent_column_number = cell.cellIndex;
    for (var i = 0; i < parent_table.rows.length; i++) {
        var curent_row = parent_table.rows[i];
        var new_cell = document.createElement("td");
        if (i == 0) {
            new_cell.setAttribute("class", "headcell");
            var temp_id = "head-" + (parent_column_number + 1);
            new_cell.setAttribute("id", temp_id);
        }
        var parent_cell = curent_row.cells[parent_column_number];
        insertAfter(new_cell, parent_cell);
    }
    var head_row = parent_table.rows[0];
    var first_row = parent_table.rows[1];
    refreshRowId(parent_table, first_row);
    refreshColumnHead(head_row);
}

function delThisColumn(cell_id) {
    var cell = document.getElementById(cell_id);

    var parent_table = cell.parentNode.parentNode.parentNode;
    if (parent_table.rows[0].cells.length > 2) {
        var parent_column_number = cell.cellIndex;
        for (var i = 0; i < parent_table.rows.length; i++) {
            var curent_row = parent_table.rows[i];
            var curent_cell = curent_row.cells[parent_column_number];
            curent_row.removeChild(curent_cell);
        }
        var head_row = parent_table.rows[0];
        var first_row = parent_table.rows[1];
        refreshRowId(parent_table, first_row);
        refreshColumnHead(head_row);
    }
}

function refreshColumnHead(head_row) {
    for (var i = 1; i < head_row.cells.length; i++) {
        var column_liter = String.fromCharCode(64 + i);
        head_row.cells[i].innerText = column_liter;
        var plus_button = createButton("+", "Добавить столбец справа");
        var minus_button = createButton("-", "Удалить текущий столбец");
        plus_button.setAttribute("onclick", "addColumn(this.parentNode.id)");
        minus_button.setAttribute("onclick", "delThisColumn(this.parentNode.id)");
        var current_row = head_row.cells[i];
        current_row.appendChild(plus_button);
        current_row.appendChild(minus_button);
        current_row.setAttribute("id", "head-" + current_row.cellIndex);
    }
}

function sendJSonToDB() {
    var curent_table = document.getElementById("maintable");
    var array_of_cells = {};
    var table_width = curent_table.rows[0].cells.length - 1;
    var table_height = curent_table.rows.length - 1;
    var count = 0;
    var head = {};
    var table_name = document.getElementById("tablename").innerText;
    if(table_name===""){
        table_name="default_table";
        document.getElementById("tablename").innerText="default_table";
        document.getElementById("tablename").setAttribute("data-formula","default_table");
    }
    head["id"]="head";
    head["data_formula"]=table_name;
    head["value"]=table_width+"x"+table_height;
    array_of_cells[++count]=head;
    for (var i = 1; i < table_height + 1; i++) {
        var current_row = curent_table.rows[i];
        for (var j = 1; j < table_width + 1; j++) {
            var curent_cell = current_row.cells[j];
            if (curent_cell.getAttribute("data-formula") != null) {
                var cell = {};
                cell["id"] = curent_cell.id;
                cell["data_formula"] = curent_cell.getAttribute("data-formula");
                cell["value"] = curent_cell.innerText;
                array_of_cells[++count] = cell;
            }
        }
    }
    var json = JSON.stringify(array_of_cells);
    var xhr = new XMLHttpRequest();
    xhr.open('post', "/exceladd", true);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
    //console.log(json);
}

function createTable(table_width,table_height) {
    document.getElementById("createtable").setAttribute("style","display: none");
    if(table_width===0){
        table_width=document.getElementById("tablesizefieldwidth").innerText;
    }
    if(table_height===0){
        table_height=document.getElementById("tablesizefieldheight").innerText;
    }
    document.getElementById("tablesizefieldwidth").setAttribute("style","display: none");
    document.getElementById("tablesizefieldheight").setAttribute("style","display: none");
    document.getElementById("tablesizefielddelimiter").setAttribute("style","display: none");
    document.getElementById("savetable").setAttribute("style","display: inline");
    document.getElementById("tablebody").setAttribute("style","display: inline");
    var table_name = document.getElementById("tablename").innerText;
    if(table_name===""){
        table_name="default_table";
        document.getElementById("tablename").innerText="default_table";
        document.getElementById("tablename").setAttribute("data-formula","default_table");
    }
    console.log(table_width+""+table_height);
    for(var j=1;j<table_height;j++){
        addDownRow(j);
    }
    for(var i=1;i<table_width;i++){
        addColumn("head-"+(i));
    }
}

function deleteTable() {
    var main_table=document.getElementById("maintable");
    var row_num=main_table.rows.length-1;
    var col_num=main_table.rows[0].cells.length-1;
    console.log(row_num+" rncn "+col_num);
    for(var i=row_num;i>1;i--){
        delThisRow(i-1);
    }
    for(var j=col_num;j>1;j--){
        delThisColumn("head-"+j);
    }
}

function getTableFromDB() {
    var table_width;
    var table_height;
    var table_name = document.getElementById("tablename").innerText;
    ajax_get("/exceladd?table_name="+table_name,function (json) {
        for(var i in json){
            var cell=json[i];
            if(i==="1"){
                table_width=cell["value"].split("x")[0];
                table_height=cell["value"].split("x")[1];
                deleteTable();
                createTable(table_width,table_height);
            }else{
                var tocell = document.getElementById(cell["id"]);
                tocell.setAttribute("data-formula",cell["data_formula"]);
                tocell.innerText=cell["value"];
            }

        }

    });
}

function ajax_get(url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            console.log('responseText:' + xhr.responseText);
            try {
                var json = JSON.parse(xhr.responseText);
            } catch(err) {
                console.log(err.message + " in " + xhr.responseText);
                return;
            }
            callback(json);
        }
    };

    xhr.open("GET", url, true);
    xhr.send();
}

