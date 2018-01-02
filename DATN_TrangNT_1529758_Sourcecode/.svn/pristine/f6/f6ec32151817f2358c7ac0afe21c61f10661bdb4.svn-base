var activeRowColor = '#ffffb5';
var normalRowColor = '#ffffff';

function TablePropertyObj(tableId, activeRow, numberRowBodyHeader, numberRowBodyFooter) {
    this.tableId = tableId;
    this.activeRow = activeRow;
    this.numberRowBodyHeader = numberRowBodyHeader;
    this.numberRowBodyFooter = numberRowBodyFooter;
}
var tablePropertyArray = new Array();

function addRowTable(tableId) {
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObj;
    var currentRowObj = tablePropObj.activeRow;
    var sampleCells = tablePropObj.activeRow.cells;
    var tdObj;
    var tdChildNotes;
    var tdChildNote;
    var trObj;
    var idx;
    var cellObj;
    var cellAtributes;

    tbodyObj = currentRowObj.parentNode;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }

    trObj = tbodyObj.insertRow(currentRowObj.sectionRowIndex + 1);

    trObj.onclick = activeRowTable;
    trObj.onkeydown = activeRowTableByKey;
    trObj.onkeyup = activeRowTableByKeyTab;
    idx = trObj.rowIndex;

    for (i = 0;i < sampleCells.length;++i) {
        cellObj = sampleCells[i];
        cellAtributes = cellObj.attributes;
        tdChildNotes = cellObj.childNodes;
        tdObj = trObj.insertCell(i);
        tdObj.style.cssText = cellObj.style.cssText;
        tdObj.className = cellObj.className;
        for (j = 0;j < cellAtributes.length;++j) {
            if (cellAtributes[j].nodeName != 'tabIndex') {
                tdObj.setAttribute(cellAtributes[j].nodeName, cellAtributes[j].nodeValue);
            }
        }
        for (k = 0;k < tdChildNotes.length;++k) {
            tdChildNote = tdChildNotes[k].cloneNode(true);
            if (tdChildNote.type != undefined && tdChildNote.type == 'text' && tdChildNote.value != undefined) {
                tdChildNote.value = '';
            }
            tdObj.appendChild(tdChildNote);
        }
    }
    assignId(tableId);
    activeRow(tableId, trObj);
    setTabIndex();
    return idx;
}

function assignId(tableId) {
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObjArray = tableObj.tBodies;
    var tbodyObj;
    var rowCol;
    var rowObj;
    var cellCol;
    var cellObj;
    var cellChildNotes;
    var cellChildNote;
    for (h = 0;h < tbodyObjArray.length;++h) {
        tbodyObj = tableObj.tBodies[h];
        rowCol = tbodyObj.rows;
        for (i = 0;i < rowCol.length;++i) {
            rowObj = rowCol[i];
            cellCol = rowObj.cells;
            for (j = 0;j < cellCol.length;++j) {
                cellObj = cellCol[j];
                cellChildNotes = cellObj.childNodes;
                for (k = 0;k < cellChildNotes.length;++k) {
                    cellChildNote = cellChildNotes[k];
                    if (cellChildNote.name != undefined) {
                        cellChildNote.id = cellChildNote.name + '_' + rowObj.rowIndex;
                        if (cellChildNote.type != undefined && cellChildNote.type == 'text') {
                            cellChildNote.onfocus = selectText;
                        }
                    }
                }
            }
        }
    }
}

function assignIdMutiBody(tableId) {
    var tableObj = document.getElementById(tableId);
    var tbodyObjArray = tableObj.tBodies;
    var tbodyObj;
    var rowCol;
    var rowObj;
    var cellCol;
    var cellObj;
    var cellChildNotes;
    var cellChildNote;
    for (h = 0;h < tbodyObjArray.length;++h) {
        tbodyObj = tableObj.tBodies[h];
        rowCol = tbodyObj.rows;
        for (i = 0;i < rowCol.length;++i) {
            rowObj = rowCol[i];
            cellCol = rowObj.cells;
            for (j = 0;j < cellCol.length;++j) {
                cellObj = cellCol[j];
                cellChildNotes = cellObj.childNodes;
                for (k = 0;k < cellChildNotes.length;++k) {
                    cellChildNote = cellChildNotes[k];
                    if (cellChildNote.name != undefined) {
                        cellChildNote.id = cellChildNote.name + '_' + h + '_' + rowObj.rowIndex;
                        if (cellChildNote.type != undefined && cellChildNote.type == 'text') {
                            cellChildNote.onfocus = selectText;
                        }
                    }
                }
            }
        }
    }
}

function removeTBodyTable(tableId) {
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObj;
    var tbodyObjArray = tableObj.tBodies;
    if (tbodyObjArray.length > 1) {
        tbodyObj = tableObj.tBodies[tbodyObjArray.length - 1];
        document.getElementById(tableId).removeChild(tbodyObj);
    }
    assignSTTBodyTable(tableId);
    assignId(tableId);
    tbodyObj = tableObj.tBodies[0];
    var currentRowObj = tbodyObj.rows[tbodyObj.rows.length - tablePropObj.numberRowBodyHeader];
    activeRow(tableId, currentRowObj);
    setTabIndex();
}

function addTbodyTable(tableId) {
    var tbodyObj;
    var tablePropObj = tablePropertyArray[tableId];
    var currentRowObj = tablePropObj.activeRow;
    var sampleCells;
    var tdObj;
    var tdChildNotes;
    var tdChildNote;
    var trObj;
    var idx;
    var cellObj;
    var cellAtributes;
    tbodyObj = currentRowObj.parentNode;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }
    var rows = tbodyObj.rows;
    var tbo = document.createElement('tbody');
    for (var i = 0;i < 2;i++) {
        var oldRow = rows[i];
        sampleCells = oldRow.cells;
        trObj = tbo.insertRow(tbo.rows.length);
        for (var j = 0;j < sampleCells.length;j++) {
            cellObj = sampleCells[j];
            cellAtributes = cellObj.attributes;
            tdChildNotes = cellObj.childNodes;
            tdObj = trObj.insertCell(j);
            for (k = 0;k < cellAtributes.length;++k) {
                if (cellAtributes[k].nodeName != 'tabIndex') {
                    tdObj.setAttribute(cellAtributes[k].nodeName, cellAtributes[k].nodeValue);
                }
            }
            for (m = 0;m < tdChildNotes.length;++m) {
                tdChildNote = tdChildNotes[m].cloneNode(true);
                if (tdChildNote.type != undefined && tdChildNote.type == 'text' && tdChildNote.value != undefined) {
                    tdChildNote.value = '';
                }
                tdObj.appendChild(tdChildNote);
            }
        }
    }
    document.getElementById(tableId).appendChild(tbo);
    activeRow(tableId, trObj);
    assignSTTBodyTable(tableId);
    assignId(tableId);
    setTabIndex();
    return idx;
}

function addRowTableTBody(tableId, tbodyId) {
    var tbodyObj;
    var tablePropObj = tablePropertyArray[tableId];
    tbodyObj = document.getElementById(tbodyId);
    var currentRowObj = tbodyObj.rows[tbodyObj.rows.length - tablePropObj.numberRowBodyHeader];
    var sampleCells = currentRowObj.cells;
    var tdObj;
    var tdChildNotes;
    var tdChildNote;
    var trObj;
    var idx;
    var cellObj;
    var cellAtributes;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }
    trObj = tbodyObj.insertRow(tbodyObj.rows.length);
    trObj.onclick = activeRowTable;
    trObj.onkeydown = activeRowTableByKey;
    trObj.onkeyup = activeRowTableByKeyTab;
    idx = trObj.rowIndex;

    for (i = 0;i < sampleCells.length;++i) {
        cellObj = sampleCells[i];
        cellAtributes = cellObj.attributes;
        tdChildNotes = cellObj.childNodes;
        tdObj = trObj.insertCell(i);

        tdObj.style.cssText = cellObj.style.cssText;
        for (j = 0;j < cellAtributes.length;++j) {
            if (cellAtributes[j].nodeName != 'tabIndex') {
                tdObj.setAttribute(cellAtributes[j].nodeName, cellAtributes[j].nodeValue);
            }
        }
        for (k = 0;k < tdChildNotes.length;++k) {
            tdChildNote = tdChildNotes[k].cloneNode(true);
            if (tdChildNote.type != undefined && tdChildNote.type == 'text' && tdChildNote.value != undefined) {
                tdChildNote.value = '';
            }
            tdObj.appendChild(tdChildNote);
        }
    }
    assignIdTBody(tableId, tbodyId);
    activeRow(tableId, trObj);
    setTabIndex();
    return idx;
}

function assignIdTBody(tableId, tbodyId) {
    var tbodyObj;
    var rowCol;
    var rowObj;
    var cellCol;
    var cellObj;
    var cellChildNotes;
    var cellChildNote;
    tbodyObj = document.getElementById(tbodyId);
    rowCol = tbodyObj.rows;
    for (i = 0;i < rowCol.length;++i) {
        rowObj = rowCol[i];
        cellCol = rowObj.cells;
        for (j = 0;j < cellCol.length;++j) {
            cellObj = cellCol[j];
            cellChildNotes = cellObj.childNodes;
            for (k = 0;k < cellChildNotes.length;++k) {
                cellChildNote = cellChildNotes[k];
                if (cellChildNote.name != undefined) {
                    cellChildNote.id = cellChildNote.name + '_' + rowObj.rowIndex;
                    if (cellChildNote.type != undefined && cellChildNote.type == 'text') {
                        cellChildNote.onfocus = selectText;
                    }
                }
            }
        }
    }
}

function attachEvent(tableId) {
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    if (tableObj != null) {
        var tbodyObjArray = tableObj.tBodies;
        var tbodyObj;
        var rowCol;
        var rowObj;
        var cellCol;
        var cellChildNotes;
        var cellChildNote;

        for (h = 0;h < tbodyObjArray.length;++h) {
            tbodyObj = tableObj.tBodies[h];
            rowCol = tbodyObj.rows;
            for (i = tablePropObj.numberRowBodyHeader;i < rowCol.length - tablePropObj.numberRowBodyFooter;++i) {
                rowObj = rowCol[i];
                rowObj.onclick = activeRowTable;
                rowObj.onkeydown = activeRowTableByKey;
                rowObj.onkeyup = activeRowTableByKeyTab;
            }
        }
    }

}

function deleteRowTable(tableId) {
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObj;
    var currentRowObj = tablePropObj.activeRow;
    var rowCol;
    var rowIdx;

    tbodyObj = currentRowObj.parentNode;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }
    rowCol = tbodyObj.rows;
    if (rowCol.length > 1 + tablePropObj.numberRowBodyHeader + tablePropObj.numberRowBodyFooter && currentRowObj != null) {
        rowIdx = currentRowObj.sectionRowIndex;
        tbodyObj.deleteRow(rowIdx);
        assignId(tableId);
        if (rowIdx == rowCol.length - tablePropObj.numberRowBodyFooter) {
            rowIdx = rowIdx - 1;
        }
        activeRow(tableId, rowCol[rowIdx]);
    }
    setTabIndex();
}
/*
 * Cuonghd
 * Xoa mot row trong bang tu co index = rowIdx.
 */
function deleteRowTableByIndex(tableId, index) {
    var delindex = index;
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObj;
    var currentRowObj = tablePropObj.activeRow;
    var rowCol;
    tbodyObj = currentRowObj.parentNode;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }
    rowCol = tbodyObj.rows;
    if (rowCol.length > 1 + tablePropObj.numberRowBodyHeader + tablePropObj.numberRowBodyFooter && currentRowObj != null) {
        tbodyObj.deleteRow(index);
        assignId(tableId);
        if (index == rowCol.length - tablePropObj.numberRowBodyFooter) {
            index = index - 1;
        }
        if (null != rowCol[index] && undefined != rowCol[index]) {
            activeRow(tableId, rowCol[index]);
        }
    }
    else 
    delindex =  - 1;
    setTabIndex();
    return delindex;
}

function deleteRowTableTBody(tableId, tbodyId) {
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObj = document.getElementById(tbodyId);
    var rowId = tbodyObj.rows.length - tablePropObj.numberRowBodyFooter - 1;
    var currentRowObj = tbodyObj.rows[rowId];
    var rowCol;
    var rowIdx;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }
    rowCol = tbodyObj.rows;
    if (rowCol.length > 1 + tablePropObj.numberRowBodyHeader + tablePropObj.numberRowBodyFooter && currentRowObj != null) {
        rowIdx = currentRowObj.sectionRowIndex;
        tbodyObj.deleteRow(rowIdx);
        assignIdTBody(tableId, tbodyId);
        //activeRow(tableId, rowCol[tbodyObj.rows.length - tablePropObj.numberRowBodyHeader]);
    }
    setTabIndex();
}

function assignSTTBodyTable(tableId) {
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObjArray = tableObj.tBodies;
    var tbodyObj;
    var rowCol;
    var rowObj;
    var cellCol;
    for (h = 0;h < tbodyObjArray.length;++h) {
        tbodyObj = tableObj.tBodies[h];
        rowCol = tbodyObj.rows;
        /*
         * Gan STT cho dong dau tien
         */
        rowObj = rowCol[tablePropObj.numberRowBodyHeader - 1];
        cellCol = rowObj.cells;
        cellCol[0].innerText = h + 1;
        for (i = tablePropObj.numberRowBodyHeader;i < rowCol.length - tablePropObj.numberRowBodyFooter;++i) {
            rowObj = rowCol[i];
            cellCol = rowObj.cells;
            cellCol[0].innerText = h + 1 + "." + i - tablePropObj.numberRowBodyHeader + 1;
            rowObj.onclick = activeRowTable;
            rowObj.onkeydown = activeRowTableByKey;
            rowObj.onkeyup = activeRowTableByKeyTab;
        }
    }
}

function assignSTTTBody(tableId) {
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObjArray = tableObj.tBodies;
    var tbodyObj;
    var rowCol;
    var rowObj;
    var cellCol;
    for (h = 0;h < tbodyObjArray.length;++h) {
        tbodyObj = tableObj.tBodies[h];
        rowCol = tbodyObj.rows;
        for (i = tablePropObj.numberRowBodyHeader;i < rowCol.length - tablePropObj.numberRowBodyFooter;++i) {
            rowObj = rowCol[i];
            cellCol = rowObj.cells;
            cellCol[0].innerText = h + 1 + "." + i - tablePropObj.numberRowBodyHeader + 1;
        }
    }
}

function activeRow(tableId, rowObj) {
    var tablePropObj = tablePropertyArray[tableId];
    var currentRowObj = tablePropObj.activeRow;
    var tbodyObj;
    var cellCol = rowObj.cells;
    if (currentRowObj != null) {
        deactiveRowTable(currentRowObj);
    }

    rowObj.style.backgroundColor = activeRowColor;
    for (i = 0;i < cellCol.length;++i) {
        if (cellCol[i].style != undefined) {
            cellCol[i].style.backgroundColor = activeRowColor;
        }
        tdChildNotes = cellCol[i].childNodes;
        for (j = 0;j < tdChildNotes.length;++j) {
            if (tdChildNotes[j].style != undefined) {
                tdChildNotes[j].style.backgroundColor = activeRowColor;
            }
        }
    }
    tablePropObj.activeRow = rowObj;
    tbodyObj = rowObj.parentNode;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }
}

function deactiveRowTable(rowObj) {
    var cellCol = rowObj.cells;
    rowObj.style.backgroundColor = normalRowColor;
    for (i = 0;i < cellCol.length;++i) {
        if (cellCol[i].style != undefined) {
            cellCol[i].style.backgroundColor = normalRowColor;
        }
        tdChildNotes = cellCol[i].childNodes;
        for (j = 0;j < tdChildNotes.length;++j) {
            if (tdChildNotes[j].style != undefined) {
                tdChildNotes[j].style.backgroundColor = normalRowColor;
            }
        }
    }
}

function activeRowTableByKey() {
    var keyCode = window.event.keyCode;
    var tablePropObj;
    var obj;
    var objIdPrefix;
    var tabObj;
    var rowObj;
    var tbodyObj;
    var rowCol;
    var rowIdx;
    var colObj;
    var cellChildNotes;
    var cellChildNote;
    var colIdx;

    if (keyCode == 13 || keyCode == 9) {
        obj = window.event.srcElement;
        objIdPrefix = obj.id;
        rowObj = obj.parentNode;
        while (rowObj.tagName != 'TR') {
            rowObj = rowObj.parentNode;
        }
        tabObj = rowObj;
        while (tabObj.tagName != 'TABLE') {
            tabObj = tabObj.parentNode;
        }
        tablePropObj = tablePropertyArray[tabObj.id];
        tbodyObj = rowObj.parentNode;
        while (tbodyObj.tagName != 'TBODY') {
            tbodyObj = tbodyObj.parentNode;
        }
        activeRow(tabObj.id, rowObj);
    }
    else if (keyCode == 38 || keyCode == 40) {
        obj = window.event.srcElement;
        objIdPrefix = obj.id;
        rowObj = obj.parentNode;
        while (rowObj.tagName != 'TR') {
            rowObj = rowObj.parentNode;
        }
        tabObj = rowObj;
        while (tabObj.tagName != 'TABLE') {
            tabObj = tabObj.parentNode;
        }
        tablePropObj = tablePropertyArray[tabObj.id];
        rowIdx = rowObj.sectionRowIndex;
        tbodyObj = rowObj.parentNode;
        while (tbodyObj.tagName != 'TBODY') {
            tbodyObj = tbodyObj.parentNode;
        }
        rowCol = tbodyObj.rows;
        if (keyCode == 38) {
            if (rowIdx > tablePropObj.numberRowBodyHeader) {
                rowIdx = rowIdx - 1;
            }
            else {
                rowIdx = rowCol.length - tablePropObj.numberRowBodyFooter - 1;
            }
        }
        else if (keyCode == 40) {
            if (rowIdx < rowCol.length - tablePropObj.numberRowBodyFooter - 1) {
                rowIdx = rowIdx + 1;
            }
            else {
                rowIdx = tablePropObj.numberRowBodyHeader;
            }
        }
        rowObj = rowCol[rowIdx]
        activeRow(tabObj.id, rowObj);
        objIdPrefix = objIdPrefix.substring(0, objIdPrefix.lastIndexOf('_'));
        document.getElementById(objIdPrefix + '_' + rowObj.rowIndex).focus();
    }
}

function activeRowTableByKeyTab() {
    var keyCode = window.event.keyCode;
    var tablePropObj;
    var obj;
    var objIdPrefix;
    var tabObj;
    var rowObj;
    var tbodyObj;
    var rowCol;
    var rowIdx;
    var colObj;
    var cellChildNotes;
    var cellChildNote;
    var colIdx;

    if (keyCode == 13 || keyCode == 9) {
        obj = window.event.srcElement;
        objIdPrefix = obj.id;
        rowObj = obj.parentNode;
        while (rowObj.tagName != 'TR') {
            rowObj = rowObj.parentNode;
        }
        tabObj = rowObj;
        while (tabObj.tagName != 'TABLE') {
            tabObj = tabObj.parentNode;
        }
        tablePropObj = tablePropertyArray[tabObj.id];
        tbodyObj = rowObj.parentNode;
        while (tbodyObj.tagName != 'TBODY') {
            tbodyObj = tbodyObj.parentNode;
        }
        activeRow(tabObj.id, rowObj);
    }
}

function activeRowTable() {
    var obj = window.event.srcElement;
    var rowObj = obj.parentNode;
    var tablePropObj;
    var tabObj;
    var currentRowObj;
    while (rowObj.tagName != 'TR') {
        rowObj = rowObj.parentNode;
    }
    tabObj = rowObj;
    while (tabObj.tagName != 'TABLE') {
        tabObj = tabObj.parentNode;
    }
    tablePropObj = tablePropertyArray[tabObj.id];
    currentRowObj = tablePropObj.activeRow;
    if (rowObj != currentRowObj) {
        activeRow(tabObj.id, rowObj);
    }
}

function initTable(tableId) {
    initMultiBodyTable(tableId, 0, 0);
}

function initMultiBodyTable(tableId, numberRowBodyHeader, numberRowBodyFooter) {
    var tableObj;
    var tbodyObj;
    var currentRowObj;
    var tbPropObj;

    tableObj = document.getElementById(tableId);
    tbodyObj = tableObj.tBodies[0];
    currentRowObj = tbodyObj.rows[numberRowBodyHeader];
    tbPropObj = new TablePropertyObj(tableId, currentRowObj, numberRowBodyHeader, numberRowBodyFooter);
    tablePropertyArray[tableId] = tbPropObj;

    assignId(tableId);
    attachEvent(tableId);

    activeRow(tableId, currentRowObj);
}

function selectText() {
    var obj = window.event.srcElement;
    obj.select();
}

function assignSTT(tableId, idx) {
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObjArray = tableObj.tBodies;
    var tbodyObj;
    var rowCol;
    var rowObj;
    var cellCol;
    var celIdx = 0;
    for (h = 0;h < tbodyObjArray.length;++h) {
        tbodyObj = tableObj.tBodies[h];
        rowCol = tbodyObj.rows;
        for (i = tablePropObj.numberRowBodyHeader;i < rowCol.length - tablePropObj.numberRowBodyFooter;++i) {
            rowObj = rowCol[i];
            cellCol = rowObj.cells;
            if (idx != null && idx != 'undefined')
                celIdx = idx;
            if (cellCol[celIdx] != null) {
                cellCol[celIdx].style.cssText = 'text-align:center';
                cellCol[celIdx].setAttribute('style', 'text-align:center');
                cellCol[celIdx].innerText = i - tablePropObj.numberRowBodyHeader + 1;
            }
        }
    }
}

function calculateVerticalSum(item_name, total_id) {
    var items = document.getElementsByName(item_name);
    var totalObj = document.getElementById(total_id);
    var total = 0;
    var itemValue;
    if (null == totalObj) {
        totalObj = document.getElementsByName(total_id)[0];
    }
    for (i = 0;i < items.length;++i) {
        itemValue = items[i].value;
        //if(itemValue == '-') {
        //  itemValue = 0;
        //}
        var gtrNum = toNumber(itemValue);
        if (isNaN(gtrNum)) {
            gtrNum = 0;
        }
        total = total + gtrNum;
    }
    totalObj.value = formatNumber(total.toString());
}

/**
 * Cuonghd
 * calculateVerticalSum
 * return: tong gia tri item_name cung hang voi item_name_match va gia tri item_name_match bang valueMatch
 **/
function calculateVerticalSum1(operator, valueMatch, item_name_match, item_name, total_id) {
    var items = document.getElementsByName(item_name);
    var totalObj = document.getElementById(total_id);
    var itemMatch = document.getElementsByName(item_name_match);
    var total = 0;
    var itemValue;
    var itemMatchValue;
    if (null == totalObj) {
        totalObj = document.getElementsByName(total_id)[0];
    }
    for (i = 0;i < items.length;++i) {
        itemMatchValue = itemMatch[i].value;
        reg = /^[\w]+$/;
        var flag;
        if (reg.test(itemMatchValue)) {
            flag = eval("'" + itemMatchValue + "'" + operator + "'" + valueMatch + "'");
        }
        else {
            itemMatchValue = toNumber(itemMatchValue);
            flag = eval(itemMatchValue + operator + valueMatch);
        }
        if (flag) {
            itemValue = items[i].value;
            var gtrNum = toNumber(itemValue);
            if (isNaN(gtrNum)) {
                gtrNum = 0;
            }
            total = total + gtrNum;
        }
    }
    totalObj.value = formatNumber(total.toString());
}

function calculateVerticalSum2(andor, operator1, valueMatch1, item_name_match1, operator2, valueMatch2, item_name_match2, item_name, total_id) {
    var items = document.getElementsByName(item_name);
    var totalObj = document.getElementById(total_id);
    var itemMatch1 = document.getElementsByName(item_name_match1);
    var itemMatch2 = document.getElementsByName(item_name_match2);
    var total = 0;
    var itemValue;
    var itemMatchValue1;
    var itemMatchValue2;
    if (null == totalObj) {
        totalObj = document.getElementsByName(total_id)[0];
    }
    for (i = 0;i < items.length;++i) {
        itemMatchValue1 = itemMatch1[i].value;
        itemMatchValue2 = itemMatch2[i].value;
        var flag1;
        var flag2;
        var flag3;
        reg = /^[\w|^\.]+$/;
        if (reg.test(itemMatchValue1)) {
            flag1 = eval("'" + itemMatchValue1 + "'" + operator1 + "'" + valueMatch1 + "'");
        }
        else {
            itemMatchValue1 = toNumber(itemMatchValue1);
            flag1 = eval(itemMatchValue1 + operator1 + valueMatch1);
        }
        if (reg.test(itemMatchValue2)) {
            flag2 = eval("'" + itemMatchValue2 + "'" + operator2 + "'" + valueMatch2 + "'");
        }
        else {
            itemMatchValue2 = toNumber(itemMatchValue2);
            flag2 = eval(itemMatchValue2 + operator2 + valueMatch2);
        }
        flag3 = eval(flag1 + andor + flag2);
        if (flag3) {
            itemValue = items[i].value;
            var gtrNum = toNumber(itemValue);
            if (isNaN(gtrNum)) {
                gtrNum = 0;
            }
            total = total + gtrNum;
        }
    }
    totalObj.value = formatNumber(total.toString());
}

function calculateVerticalSumBigInt(item_name, total_id) {
    var items = document.getElementsByName(item_name);
    var totalObj = document.getElementById(total_id);
    var total = 0;
    var itemValue;
    if (null == totalObj) {
        totalObj = document.getElementsByName(total_id)[0];
    }
    for (i = 0;i < items.length;++i) {
        itemValue = items[i].value;
        itemValue = ReplaceAll(itemValue, '.', '');
        itemValue = itemValue.replace(',', '.')
        total = new BigNumber(total).add(itemValue);
    }
    totalObj.value = formatNumber(total.toString());
}

function calculateVerticalFloatSum(item_name, total_id, param) {
    var items = document.getElementsByName(item_name);
    var totalObj = document.getElementById(total_id);
    var total = 0;
    var itemValue;
    if (null == totalObj) {
        totalObj = document.getElementsByName(total_id)[0];
    }
    for (i = 0;i < items.length;++i) {
        itemValue = items[i].value;
        total = total + toNumber(itemValue);
    }

    //    var resultDe = toFormatNumberDe(total, 2);    
    //    var pos = resultDe.indexOf(',');
    //    var intPart = resultDe.substring(0,pos);
    //    var decPart = resultDe.substring(pos+1);
    //
    //    if(intPart == ''){
    //        intPart = '0';
    //    }
    //    if(decPart.length == 1){
    //        decPart = decPart + '0';
    //    }
    //
    //    totalObj.value = intPart + ',' + decPart;
    totalObj.value = toFormatNumberDe(total, param);
}

function calculateTBodyVerticalSum(tableId, item_name, total_id) {
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObj;
    var beginIdx;
    var endIdx;
    var itemObj;
    var totalObj = document.getElementById(total_id);
    var totalTBodyObj;
    var total = 0;
    var itemValue;

    if (null == totalObj) {
        totalObj = document.getElementsByName(total_id)[0];
    }

    tbodyObj = tablePropObj.activeRow.parentNode;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }

    totalTBodyObj = totalObj.parentNode;
    while (totalTBodyObj.tagName != 'TBODY') {
        totalTBodyObj = totalTBodyObj.parentNode;
    }
    if (totalTBodyObj == tbodyObj) {
        rowObj = tbodyObj.rows[0];
        beginIdx = rowObj.rowIndex + tablePropObj.numberRowBodyHeader;
        endIdx = rowObj.rowIndex + tbodyObj.rows.length - tablePropObj.numberRowBodyFooter;
        for (i = beginIdx;i < endIdx;++i) {
            itemObj = document.getElementById(item_name + '_' + i);
            if (null == itemObj) {
                itemObj = document.getElementsByName(item_name + '_' + i);
            }
            itemValue = itemObj.value;
            //if(itemValue == '-') {
            //  itemValue = 0;
            //}
            var gtrNum = toNumber(itemValue);
            if (isNaN(gtrNum)) {
                gtrNum = 0;
            }
            total = total + gtrNum;
        }
        totalObj.value = formatNumber(total.toString());
    }
}

/**
 * cuonghd
 * doi so dau vao la mot mang
 */
function calculateSumBigInt() {
    var argv = calculateSumBigInt.arguments[0].toString().split(',');
    var argc = argv.length;
    var itemName;
    var itemObj;
    var itemValue;
    var total = 0;
    var totalObj;
    var i;
    var sign;
    for (i = 0;i < argc - 1;i++) {
        itemName = argv[i];
        sign = itemName.substr(0, 1);
        if (sign == '-' || sign == '+') {
            itemName = itemName.substring(1);
        }
        itemObj = document.getElementById(itemName);
        itemValue = itemObj.value;
        itemValue = ReplaceAll(itemValue, '.', '');
        itemValue = itemValue.replace(',', '.')
        if (sign == '-') {
            total = new BigNumber(total).subtract(itemValue);
        }
        else {
            total = new BigNumber(total).add(itemValue);
        }
    }
    totalObj = document.getElementById(argv[i]);
    totalObj.value = formatNumber(total.toString());
}

function calculateSumBigFloat() {
    var argv = calculateSumBigFloat.arguments[0].toString().split(',');
    var argc = argv.length;
    var itemName;
    var itemObj;
    var itemValue;
    var total = 0;
    var totalObj;
    var i;
    var sign;
    var intPart;
    var decPart;
    for (i = 0;i < argc - 1;i++) {
        itemName = argv[i];
        sign = itemName.substr(0, 1);
        if (sign == '-' || sign == '+') {
            itemName = itemName.substring(1);
        }
        itemObj = document.getElementById(itemName);
        itemValue = itemObj.value;
        itemValue = ReplaceAll(itemValue, '.', '');
        itemValue = itemValue.replace(',', '.')
        if (sign == '-') {
            total = new BigNumber(total).subtract(itemValue).round();
        }
        else {
            total = new BigNumber(total).add(itemValue).round();
        }
    }
    totalObj = document.getElementById(argv[i]);
    intPart = total.intPart();
    decPart = new Number(total.subtract(intPart));
    decPart = eval(decPart).toFixed(2);
    reg = /^0\.+|^0,+/;
    decPart = decPart.toString().replace(reg, ",");
    totalObj.value = formatNumber(intPart.toString()) + decPart;
}

function calculateTBodyVerticalSumFloatNumber(tableId, item_name, total_id) {
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObj;
    var beginIdx;
    var endIdx;
    var itemObj;
    var totalObj = document.getElementById(total_id);
    var totalTBodyObj;
    var total = 0;
    var itemValue;

    if (null == totalObj) {
        totalObj = document.getElementsByName(total_id)[0];
    }

    tbodyObj = tablePropObj.activeRow.parentNode;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }
    totalTBodyObj = totalObj.parentNode;
    while (totalTBodyObj.tagName != 'TBODY') {
        totalTBodyObj = totalTBodyObj.parentNode;
    }
    if (totalTBodyObj == tbodyObj) {
        rowObj = tbodyObj.rows[0];
        beginIdx = rowObj.rowIndex + tablePropObj.numberRowBodyHeader;
        endIdx = rowObj.rowIndex + tbodyObj.rows.length - tablePropObj.numberRowBodyFooter;
        for (i = beginIdx;i < endIdx;++i) {
            itemObj = document.getElementById(item_name + '_' + i);
            itemValue = itemObj.value;
            total = total + toNumber(itemValue);
        }
        total = total.toFixed(2);
        totalObj.value = toFormatNumberDeFloat(total.toString(), 2);
    }
}

function calculateHorizontalSum() {
    var srcObj = window.event.srcElement;
    var srcObjId = srcObj.id;
    var idSuffix = srcObjId.substring(srcObjId.lastIndexOf('_'));
    var argv = calculateHorizontalSum.arguments;
    var argc = argv.length;
    var itemName;
    var itemObj;
    var itemValue;
    var total = 0;
    var totalObj;
    var i;
    var sign;
    for (i = 0;i < argc - 1;i++) {
        itemName = argv[i];
        sign = itemName.substr(0, 1);
        if (sign == '-' || sign == '+') {
            itemName = itemName.substring(1);
        }
        itemObj = document.getElementById(itemName + idSuffix);

        if (null == itemObj) {
            itemObj = document.getElementsByName(srcObjId)[0];
        }

        itemValue = itemObj.value;

        if (sign == '-') {
            total = total - toNumber(itemValue);
        }
        else {
            total = total + toNumber(itemValue);
        }
    }
    total = total.toFixed(0);
    totalObj = document.getElementById(argv[i] + idSuffix);

    if (null == totalObj) {
        totalObj = document.getElementsByName(srcObjId)[0];
    }
    totalObj.value = formatNumber(total.toString());
}

function calculateHorizontalSumBigInt() {
    var srcObj = window.event.srcElement;
    var srcObjId = srcObj.id;
    var idSuffix = srcObjId.substring(srcObjId.lastIndexOf('_'));
    var argv = calculateHorizontalSumBigInt.arguments;
    var argc = argv.length;
    var itemName;
    var itemObj;
    var itemValue;
    var total = 0;
    var totalObj;
    var i;
    var sign;
    for (i = 0;i < argc - 1;i++) {
        itemName = argv[i];
        sign = itemName.substr(0, 1);
        if (sign == '-' || sign == '+') {
            itemName = itemName.substring(1);
        }
        itemObj = document.getElementById(itemName + idSuffix);
        itemValue = itemObj.value;
        itemValue = ReplaceAll(itemValue, '.', '');
        itemValue = itemValue.replace(',', '.')
        if (sign == '-') {
            total = new BigNumber(total).subtract(itemValue).round();
        }
        else {
            total = new BigNumber(total).add(itemValue).round();
        }
    }
    totalObj = document.getElementById(argv[i] + idSuffix);
    intPart = total.intPart();
    decPart = new Number(total.subtract(intPart));
    decPart = eval(decPart + 1).toFixed(0) - 1;
    totalObj.value = formatNumber(new BigNumber(intPart).add(decPart).toString());
}

function calculateVaticalSumBigInt(tableId, item_name, total_id) {
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObj;
    var beginIdx;
    var endIdx;
    var itemObj;
    var totalObj = document.getElementById(total_id);
    var totalTBodyObj;
    var total = 0;
    var itemValue;

    if (null == totalObj) {
        totalObj = document.getElementsByName(total_id)[0];
    }

    tbodyObj = tablePropObj.activeRow.parentNode;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }

    totalTBodyObj = totalObj.parentNode;
    while (totalTBodyObj.tagName != 'TBODY') {
        totalTBodyObj = totalTBodyObj.parentNode;
    }
    if (totalTBodyObj == tbodyObj) {
        rowObj = tbodyObj.rows[0];
        beginIdx = rowObj.rowIndex + tablePropObj.numberRowBodyHeader;
        endIdx = rowObj.rowIndex + tbodyObj.rows.length - tablePropObj.numberRowBodyFooter;
        for (i = beginIdx;i < endIdx;++i) {
            itemObj = document.getElementById(item_name + '_' + i);
            if (null == itemObj) {
                itemObj = document.getElementsByName(item_name + '_' + i)[0];
            }
            itemValue = itemObj.value;
            itemValue = ReplaceAll(itemValue, '.', '');
            itemValue = itemValue.replace(',', '.');
            total = new BigNumber(total).add(itemValue);
        }
        totalObj.value = formatNumber(total.toString());
    }
}

function calculateSum() {
    var argv = calculateSum.arguments;
    var argc = argv.length;
    var itemName;
    var itemObjs;
    var itemObj;
    var itemValue;
    var total = 0;
    var totalObjs;
    var totalObj;
    var i;
    var sign;
    for (i = 0;i < argc - 1;i++) {
        itemName = argv[i];
        sign = itemName.substr(0, 1);
        if (sign == '-' || sign == '+') {
            itemName = itemName.substring(1);
        }
        itemObjs = document.getElementsByName(itemName);
        itemObj = itemObjs[0];
        itemValue = itemObj.value;

        if (sign == '-') {
            total = total - toNumber(itemValue);
        }
        else {
            total = total + toNumber(itemValue);
        }
    }
    total = total.toFixed(0);
    totalObjs = document.getElementsByName(argv[i]);
    totalObj = totalObjs[0];
    totalObj.value = formatNumber(total.toString());
}

function calculateSumById() {
    var argv = calculateSumById.arguments;
    var argc = argv.length;
    var itemId;
    var itemObj;
    var itemValue;
    var total = 0;
    var totalObjs;
    var totalObj;
    var i;
    var sign;
    for (i = 0;i < argc - 1;i++) {
        itemId = argv[i];
        sign = itemId.substr(0, 1);
        if (sign == '-' || sign == '+') {
            itemId = itemId.substring(1);
        }
        itemObj = document.getElementById(itemId);
        itemValue = itemObj.value;

        if (sign == '-') {
            total = total - toNumber(itemValue);
        }
        else {
            total = total + toNumber(itemValue);
        }
    }
    total = total.toFixed(0);
    totalObj = document.getElementById(argv[i]);
    if (null == totalObj) {
        totalObj = document.getElementsByName(argv[0]);
    }
    totalObj.value = formatNumber(total.toString());
}
//tinh tich so lon
function calculateProductById() {
    var argv = calculateProductById.arguments;
    var argc = argv.length;
    var itemName;
    var itemObj;
    var itemValue;
    var product = 1;
    var productObj;
    var i;
    for (i = 0;i < argc - 1;i++) {
        itemName = argv[i];
        if (itemName.indexOf('%') == 0) {
            itemObj = document.getElementById(itemName.substring(1));
        }
        else {
            itemObj = document.getElementById(itemName);
        }
        if (null == itemObj) {
            itemObj = document.getElementsByName(srcObjId)[0];
        }
        itemValue = itemObj.value;
        itemValue = toNumber(itemValue);
        if (itemName.indexOf('%') == 0) {
            itemValue = itemValue / 100;
        }
        product = product * itemValue;
    }
    product = product.toFixed(0);
    productObj = document.getElementById(argv[i]);
    if (null == productObj) {
        productObj = document.getElementsByName(srcObjId)[0];
    }
    productObj.value = formatNumber(product.toString());
}

function calculateSumFloatNumber() {
    var argv = calculateSumFloatNumber.arguments;
    var argc = argv.length;
    var itemName;
    var itemObjs;
    var itemObj;
    var itemValue;
    var total = 0;
    var totalObjs;
    var totalObj;
    var i;
    var sign;
    for (i = 0;i < argc - 1;i++) {
        itemName = argv[i];
        sign = itemName.substr(0, 1);
        if (sign == '-' || sign == '+') {
            itemName = itemName.substring(1);
        }
        itemObjs = document.getElementsByName(itemName);
        itemObj = itemObjs[0];
        itemValue = itemObj.value;

        if (sign == '-') {
            total = total - toNumber(itemValue);
        }
        else {
            total = total + toNumber(itemValue);
        }
    }
    total = total.toFixed(2);
    totalObjs = document.getElementsByName(argv[i]);
    totalObj = totalObjs[0];
    totalObj.value = toFormatNumberDeFloat(total.toString(), 2);
}

function calculateHorizontalProduct() {
    var srcObj = window.event.srcElement;
    var srcObjId = srcObj.id;
    var idSuffix = srcObjId.substring(srcObjId.lastIndexOf('_'));
    var argv = calculateHorizontalProduct.arguments;
    var argc = argv.length;
    var itemName;
    var itemObj;
    var itemValue;
    var product = 1;
    var productObj;
    var i;
    for (i = 0;i < argc - 1;i++) {
        itemName = argv[i];
        if (itemName.indexOf('%') == 0) {
            itemObj = document.getElementById(itemName.substring(1) + idSuffix);
        }
        else {
            itemObj = document.getElementById(itemName + idSuffix);
        }
        if (null == itemObj) {
            itemObj = document.getElementsByName(srcObjId)[0];
        }
        itemValue = itemObj.value;
        itemValue = toNumber(itemValue);
        if (itemName.indexOf('%') == 0) {
            itemValue = itemValue / 100;
        }
        product = product * itemValue;
    }
    product = product.toFixed(0);
    productObj = document.getElementById(argv[i] + idSuffix);
    if (null == productObj) {
        productObj = document.getElementsByName(srcObjId)[0];
    }
    productObj.value = formatNumber(product.toString());
}

function ReplaceAll(Source, stringToFind, stringToReplace) {
    var temp = Source;
    var index = temp.indexOf(stringToFind);
    while (index !=  - 1) {
        temp = temp.replace(stringToFind, stringToReplace);
        index = temp.indexOf(stringToFind);
    }
    return temp;
}

function calculateHorizontalProductBigInt() {
    var srcObj = window.event.srcElement;
    var srcObjId = srcObj.id;
    var idSuffix = srcObjId.substring(srcObjId.lastIndexOf('_'));
    var argv = calculateHorizontalProductBigInt.arguments;
    var argc = argv.length;
    var itemName;
    var itemObj;
    var itemValue;
    var product = 1;
    var productObj;
    var i;
    for (i = 0;i < argc - 1;i++) {
        itemName = argv[i];
        if (itemName.indexOf('%') == 0) {
            itemObj = document.getElementById(itemName.substring(1) + idSuffix);
        }
        else {
            itemObj = document.getElementById(itemName + idSuffix);
        }
        itemValue = itemObj.value;
        itemValue = ReplaceAll(itemValue, '.', '');
        itemValue = itemValue.replace(',', '.')
        itemValue = new BigNumber(itemValue);
        if (itemName.indexOf('%') == 0) {
            itemValue = itemValue / 100;
        }
        product = new BigNumber(product).multiply(itemValue).round();
    }
    productObj = document.getElementById(argv[i] + idSuffix);
    productObj.value = formatNumber(product.toString());
}

/**
 * Cuonghd
 * lam tron chinh xac tich cac so thap phan nho va rat lon
 */
function calculateHorizontalProductBigFloat() {
    var srcObj = window.event.srcElement;
    var srcObjId = srcObj.id;
    var idSuffix = srcObjId.substring(srcObjId.lastIndexOf('_'));
    var argv = calculateHorizontalProductBigFloat.arguments;
    var argc = argv.length;
    var itemName;
    var itemObj;
    var itemValue;
    var product = 1;
    var productObj;
    var intPart;
    var decPart;
    var i;
    for (i = 0;i < argc - 1;i++) {
        itemName = argv[i];
        if (itemName.indexOf('%') == 0) {
            itemObj = document.getElementById(itemName.substring(1) + idSuffix);
        }
        else {
            itemObj = document.getElementById(itemName + idSuffix);
        }
        itemValue = itemObj.value;
        itemValue = ReplaceAll(itemValue, '.', '');
        itemValue = itemValue.replace(',', '.')
        itemValue = new BigNumber(itemValue);
        if (itemName.indexOf('%') == 0) {
            itemValue = itemValue / 100;
        }
        product = new BigNumber(product).multiply(itemValue).round();
    }
    productObj = document.getElementById(argv[i] + idSuffix);
    intPart = product.intPart();
    decPart = new Number(product.subtract(intPart));
    decPart = eval(decPart + 1).toFixed(0) - 1;

    productObj.value = formatNumber(new BigNumber(intPart).add(decPart).toString());
}

/*
 * Cuonghd
 * tinh tich va lam tron cac so nho va rat lon
 * argv[0]-argv[n-3] so nhan, argv[n-2] tich, argv[n-1] lam tron den argv[n-1] so
 */
function calculateProductBigFloat() {
    var argv = calculateProductBigFloat.arguments;
    var argc = argv.length;
    var itemName;
    var itemObj;
    var itemValue;
    var product = 1;
    var productObj;
    var intPart;
    var decPart;
    var i;
    for (i = 0;i < argc - 2;i++) {
        itemName = argv[i];
        if (itemName.indexOf('%') == 0) {
            itemObj = document.getElementById(itemName.substring(1));
        }
        else {
            itemObj = document.getElementById(itemName);
        }
        itemValue = itemObj.value;
        itemValue = ReplaceAll(itemValue, '.', '');
        itemValue = itemValue.replace(',', '.')
        itemValue = new BigNumber(itemValue);
        if (itemName.indexOf('%') == 0) {
            itemValue = itemValue / 100;
        }
        product = new BigNumber(product).multiply(itemValue).round();
    }
    productObj = document.getElementById(argv[i]);
    intPart = product.intPart();
    decPart = new Number(product.subtract(intPart));
    decPart = eval(decPart + 1).toFixed(argv[++i]) - 1;
    productObj.value = formatNumber(new BigNumber(intPart).add(decPart).toString());
}

function calculateVaticalProductBigInt() {
    var argv = calculateVaticalProductBigInt.arguments;
    var argc = argv.length;
    var itemName;
    var itemObj;
    var itemValue;
    var product = 1;
    var productObj;
    var i;
    for (i = 0;i < argc - 1;i++) {
        itemName = argv[i];
        if (itemName.indexOf('%') == 0) {
            itemObj = document.getElementById(itemName.substring(1));
        }
        else {
            itemObj = document.getElementById(itemName);
        }
        itemValue = itemObj.value;
        itemValue = ReplaceAll(itemValue, '.', '');
        itemValue = itemValue.replace(',', '.')
        itemValue = new BigNumber(itemValue);
        if (itemName.indexOf('%') == 0) {
            itemValue = itemValue / 100;
        }
        product = new BigNumber(product).multiply(itemValue).round();
    }
    productObj = document.getElementById(argv[i]);
    productObj.value = formatNumber(product.toString());
}

function checkRequire(tableId, objName, fieldName) {
    var objArr = document.getElementsByName(objName);
    var len = objArr.length;

    var tableObj;
    var tbodyObj;
    var invalidRow;
    var objVal;
    for (i = 0;i < len;++i) {
        objVal = trim(objArr[i].value);
        if (objVal == null || objVal == '') {
            tableObj = document.getElementById(tableId);
            tbodyObj = tableObj.tBodies[0];
            invalidRow = tbodyObj.rows[i];
            objArr[i].focus();
            activeRow(tableId, invalidRow);
            alert('Phải nhập giá trị cho trư�?ng [' + fieldName + ']');
            return false;
        }
    }
    return true;
}
// Ham bo cac ky tu trang dau va cuoi xau
// Tham so: s: Xau can cat cac ky tu
function trim(s) {
    var i;
    if (isNull(s))
        return "";
    i = s.length - 1;
    while (i >= 0 && s.charAt(i) == ' ')
        i--;
    s = s.substring(0, i + 1);
    i = 0;
    while (i < s.length && s.charAt(i) == ' ')
        i++;
    return s.substring(i);
}

function focusFirstTextElements(tableId) {
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObj;
    var currentRowObj = tablePropObj.activeRow;
    var sampleCells = tablePropObj.activeRow.cells;
    var cellCol;
    var cellObj;
    var cellChildNotes;
    var cellChildNote;
    var foundOK = false;

    tbodyObj = currentRowObj.parentNode;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }
    cellCol = currentRowObj.cells;
    for (j = 0;j < cellCol.length;++j) {
        cellObj = cellCol[j];
        cellChildNotes = cellObj.childNodes;
        for (k = 0;k < cellChildNotes.length;++k) {
            cellChildNote = cellChildNotes[k];
            if (cellChildNote.name != undefined) {
                if (cellChildNote.type != undefined && cellChildNote.type == 'text') {
                    var objFocus = document.getElementById(cellChildNote.id);
                    objFocus.focus();
                    foundOK = true;
                    break;

                }
            }
        }
        if (foundOK) {
            break;
        }
    }
}

function focusFirstTextTD(tdId) {
    var tdObj = document.getElementById(tdId);
    if (tdObj == null) {
        tdObj = document.getElementsByName(tdId)[0];
    }
    tdObj.focus();
}

function focusFirstCheckbox(nameObj) {
    document.getElementsById(nameObj).focus();
}

function formatNumberForNewRow() {
    var argv = formatNumberForNewRow.arguments;
    var argc = argv.length;
    var tableId = argv[0];
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObj;
    var currentRowObj = tablePropObj.activeRow;
    var itemName;
    var itemObj;
    var itemValue;
    var product = 1;
    var productObj;
    var i;
    var data_value;
    var idSuffix = currentRowObj.rowIndex;
    for (i = 1;i < argc;i++) {
        data_value = argv[i].split(':');
        itemObj = document.getElementById(data_value[0] + '_' + idSuffix);
        if (itemObj != null) {
            itemObj.value = data_value[1];
        }
    }
}

function formatNumberForNewRowTBody() {
    var argv = formatNumberForNewRowTBody.arguments;
    var argc = argv.length;
    var tbodyId = argv[1];
    var tableId = argv[0];
    var tbodyObj = document.getElementById(tbodyId);
    var tablePropObj = tablePropertyArray[tableId];
    var currentRowObj = tbodyObj.rows[tbodyObj.rows.length - tablePropObj.numberRowBodyHeader];
    var itemObj;
    var i;
    var data_value;
    var idSuffix = currentRowObj.rowIndex;
    for (i = 2;i < argc;i++) {
        data_value = argv[i].split(':');
        itemObj = document.getElementById(data_value[0] + '_' + idSuffix);
        if (itemObj != null) {
            itemObj.value = data_value[1];
        }
    }
}