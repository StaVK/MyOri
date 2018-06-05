var ajaxUrl = "ajax/box/";
var datatableBox;

$(function () {
    datatableBox = $("#boxDatatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "aoColumns": [
            {
                "mData": "boxId"
            },
            {
                "mData": "forUser.name"
            },
            {
                "render": renderReadBtn,
                "defaultContent": "",
                "orderable": false
            }/*,
            {
                "render": renderDeleteBtn,
                "defaultContent": "Delete",
                "orderable": false
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

function addBox() {
    $("#editRow").modal();
}

function selectCustomer(customerId) {
    // var orderId=$("#orderId").val();
    $.ajax({
        type: "POST",
        url: ajaxUrl+"addBox?customerId="+customerId,
        success: function () {
            updateTable();
            // successNoty("Deleted");
        }
    });
    $("#editRow").modal("hide");
}

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableBox.clear().rows.add(data).draw();
    });
}

function renderReadBtn(data, type, row) {
    if (type === "display") {
        return "<a href=box/" + row.boxId + "><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
    }
}