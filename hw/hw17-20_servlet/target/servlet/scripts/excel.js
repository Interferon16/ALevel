function openInsert(element_id) {
    var cell = document.getElementById(element_id);
    var tempStyle = cell.getAttribute("style");
    var tempClass = cell.getAttribute("class");
    var tempId = cell.getAttribute("id");
    var tempValue = cell.getAttribute("data-formula");
    if (!tempValue || tempValue == 0) {
        tempValue = "";
    }
    var newInput = document.createElement("input");
    newInput.setAttribute("style", tempStyle);
    newInput.setAttribute("class", tempClass);
    newInput.setAttribute("id", tempId);
    newInput.setAttribute("value", tempValue);
    newInput.setAttribute("onblur", "closeInsert(this.id)");
    cell.parentNode.insertBefore(newInput, cell);
    cell.parentNode.removeChild(cell);
    newInput.focus();
}

function closeInsert(element_id) {
    var cell = document.getElementById(element_id);
    var tempStyle = cell.getAttribute("style");
    var tempClass = cell.getAttribute("class");
    var tempId = cell.getAttribute("id");
    var tempValue = cell.value;
    var newCell = document.createElement("td");
    newCell.setAttribute("style", tempStyle);
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
    newCell.setAttribute("onclick", "openInsert(this.id)");
    cell.parentNode.insertBefore(newCell, cell);
    cell.parentNode.removeChild(cell);
    setDependentCell(tempValue, cell.id);

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
    var a = document.getElementById(match).innerText;
    a = a == "" ? 0 : a;
    return a;
}

function setDependentCell(equation, parrent_id) {
    var cellname = /([a-zа-яё]+\d+)/gi;
    var dependencies = equation.match(cellname);
        for (var i = 0; i < dependencies.length; i++) {
            element = document.getElementById(dependencies[i]);
            var temp_dep=[];
            if(element.getAttribute("data-references")==null){
                temp_dep=[];
            }else{
                temp_dep.push(element.getAttribute("data-references"))
            }
            temp_dep.push(parrent_id);
            alert(parrent_id);
            temp_dep=unique(temp_dep);
            element.setAttribute("data-references", temp_dep);
        }

}

function unique(arr) {
    var result = [];

    

    return result;
}