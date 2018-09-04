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
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<a class='glyphicon glyphicon-shopping-cart' href='customerProducts/" + row.customerId + "'></a>";
                    }
                    return data;
                },
                "orderable": false,
                "defaultContent": "test"
            },
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
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
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

/*function renderCustomerStorage()
{
    return "<a onclick='customerStorage()'><span class='glyphicon glyphicon-shopping-cart' aria-hidden='true'></span></a>"
}

function customerStorage(data,row,type){
    if (type === "display") {
        return "<a class='btn btn-info' href='storage/" + row.storageId + "'>" + row.name + "</a>";
    }
    return data;
}*/

function deleteRow(customerId) {
    $.ajax({
        url: ajaxUrl + customerId,
        type: "DELETE",
        success: function () {
            updateTable();
            successNoty("Deleted");
        }
    });
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.customerId + ");'>" +
            "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
    }
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.customerId + ");'>" +
            "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
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