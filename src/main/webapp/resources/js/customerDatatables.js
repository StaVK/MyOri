var ajaxUrlCustomer = "ajax/users/";
var datatableCustomer;


// $(document).ready(function () {
$(function () {
    datatableCustomer = $("#customerDatatable").DataTable({
        "ajax": {
            "url": ajaxUrlCustomer,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "people.name"
            },
            {
                "data": "people.surname",
                "defaultContent": "test"
            },
            {
                "data": "customerId",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='radio' name='customerId' onclick='selectCustomer("+row.customerId+")'/>";
                    }
                    return data;
                }
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

function selectCustomer(customerId) {
    // var orderId=$("#orderId").val();
    $.ajax({
        type: "POST",
        url: ajaxUrl+"create?customerId="+customerId,
        success: function () {
            updateTable();
            // successNoty("Deleted");
        }
    });
    $("#editRow").modal("hide");
}



