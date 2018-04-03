var ajaxUrl = "ajax/storage/";
var datatableStorage;


function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableStorage.clear().rows.add(data).draw();
    });
}

// $(document).ready(function () {
$(function () {
    datatableStorage = $("#storageDatatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<a class='btn btn-info' href='sp/" + row.storageId + "'>" + row.name + "</a>";
                    }
                    return data;
                }
            },
            {
                "render": renderEditBtn,
                "defaultContent": "",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
        // "initComplete": makeEditable
    });
});

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.storageId + ");'>" +
            "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
    }
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.storageId + ");'>" +
            "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
    }
}

var form = $("#detailsForm");

function updateRow(id) {
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        // $.each(data, function (key, value) {
        // form.find("input[name='" + key + "']").val(value);
        form.find("input[name='storageId']").val(data.storageId);
        form.find("input[name='name']").val(data.name);
        // });
        $("#editRow").modal();
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
}