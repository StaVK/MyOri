var ajaxUrl = "ajax/order/";
var datatableOrder;

$(function () {
    datatableOrder = $("#orderDatatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "aoColumns": [
            {
                "mData": "orderId"
            },
            {
                "mData": "user.name"
            },
            {
                "mData": "forUser.name"
            },
            {
                "render": renderEditBtn,
                "defaultContent": "",
                "orderable": false
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
    });
    // makeEditable();
});

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableOrder.clear().rows.add(data).draw();
    });
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


function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.orderId + ");'>" +
            "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
    }
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a href=orders/orderUpdate?orderId=" + row.orderId + "><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
    }
}