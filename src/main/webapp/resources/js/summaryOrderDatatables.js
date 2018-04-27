var ajaxUrl = "ajax/op/";
var datatableApiProd;

$(function () {
    datatableApiProd = $("#summaryOrderDatatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
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
                "mData": "volume"
            }/*,
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }*/
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

/*

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.prodId + ");'>" +
            "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
    }
}
function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: "DELETE",
        success: function () {
            updateTable();
            // successNoty("Deleted");
        }
    });
}
function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.prodId + ");'>" +
            "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
    }
}

var form = $("#detailsForm");

function updateRow(id) {
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $("#editRow").modal();
    });
}


function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableApiProd.clear().rows.add(data).draw();
    });
}

function save() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $("#editRow").modal("hide");
            updateTable();
            // successNoty("common.saved");
        }
    });
}

function add() {
    $("#modalTitle").html(i18n["addTitle"]);
    form.find(":input").val("");
    $("#editRow").modal();
}*/
