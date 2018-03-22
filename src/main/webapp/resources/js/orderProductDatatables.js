var ajaxUrl = "ajax/op/";
var datatableApi;


// $(document).ready(function () {
$(function () {
    datatableApi = $("#orderProductsDatatable").DataTable({
        "ajax": {
            "url": ajaxUrl + $("#orderId").val(),
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "aoColumns": [
            {
                "mData": "product.article"
            },
            {
                "mData": "product.description"
            },
            {
                "mData": "product.price"
            },
            {
                "mData": "volume",
                "render": renderEditVolume
            },
            {
                "mData": null,
                "render": function (data, type, row) {
                    return row.product.price * row.volume;
                }
            },
            /*            {
                            "render": renderEditBtn,
                            "defaultContent": "",
                            "orderable": false
                        },*/
            {
                "render": renderDeleteBtn,
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    // makeEditable();
});

function serialData() {
    var table = datatableApi;
    var data;

    table.rows().every(function (rowIdx, tableLoop, rowLoop) {
        data = this.data();

    });
    return data;
}

function renderEditVolume(data, type, row) {
    var art=row.product.article;
    var vol=row.volume;
    var inpId='op'+row.opId;
        return "<input id="+inpId+" type='number' onchange='saveData("+inpId+","+art+")' value='" + vol + "'>";
}

/*$('#vol').onchange(function() {
    clearTimeout($.data(this, 'timer'));
    var wait = setTimeout(saveData, 500); // delay after user types
    $(this).data('timer', wait);
});*/
/*function delayChange(article) {
    //clearTimeout($.data(this, 'timer'));
    var wait = setTimeout(saveData(article), 10000);
    //$(this).data('timer', wait);
}*/

function saveData(inpId, article){
    var data1="&orderId="+$("#orderId").val()+"&article="+article+"&volume="+$(inpId).val();
    $.ajax({
        type: "POST",
        url: ajaxUrl+"editV",
        data: data1,
        success: function () {
            updateTable();
            // successNoty("common.saved");
        }
    });
}

/*function editVol() {
    var editRow = $("td + input")

    var OriginalContent = $(editRow).text();

    $(editRow).addClass("cellEditing");
    $(editRow).html("<input type='text' value=" + OriginalContent + " />");
    $(editRow).children().first().focus();

    $(editRow).children().first().keypress(function (e) {
        if (e.which == 13) {
            var newContent = $(editRow).val();
            $(editRow).parent().text(newContent);
            $(editRow).parent().removeClass("cellEditing");
        }
    });

    $(editRow).children().first().blur(function () {
        $(editRow).parent().text(OriginalContent);
        $(editRow).parent().removeClass("cellEditing");
    });
}*/



function updateTable() {
    $.get(ajaxUrl + $("#orderId").val(), function (data) {
        datatableApi.clear().rows.add(data).draw();
    });
}

function updateRow(row) {
    editVol();
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.opId + ");'>" +
            "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
    }
}

/*function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.opId + ");'>" +
            "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
    }
}*/
