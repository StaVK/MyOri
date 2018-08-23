var ajaxUrl = "ajax/customer/";
var datatableCustomers;

$(function () {
    datatableCustomers = $("#customersDatatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "mData": "people.name"
            },
            {
                "mData": "people.surname"
            },
            {
                "mData": "people.patronymic"
            },
            {
                "mData": "people.phoneNumber"
            },
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
        // "initComplete": makeEditable
    });
});

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: "DELETE",
        success: function () {
            updateTable();
            successNoty("Deleted");
        }
    });
}

function renderDeleteBtn(data, type, row) {
    if (row.status === 1) {
        return "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span>";
    }
    else if (type === "display") {
        return "<a onclick='deleteRow(" + row.opId + ");'>" +
            "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
    }
}

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableCustomers.clear().rows.add(data).draw();
    });
}
var form = $("#detailsForm");
function add() {
    $("#modalTitle").html(i18n["common.add"]);
    form.find(":input").val("");
    $("#editRow").modal();
}

function saveCustomer() {
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