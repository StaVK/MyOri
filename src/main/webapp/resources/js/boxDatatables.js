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
                "mData": null,
                "render": function (data, type, row) {
                    return row.customer.people.name +" "+ row.customer.people.surname;
                }
            },
/*            {
                "mData": "storageSet"
                // "defaultContent": "test"
            },*/
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
    $("#modalTitle").html(i18n["customer.selectCustomer"]);
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