var ajaxUrlCustomer = "ajax/admin/users/";
var datatableCustomer;

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableUser.clear().rows.add(data).draw();
    });
}

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
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "id",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='radio' name='customerId' onclick='changeCustomer("+row.id+")'/>";
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

function changeCustomer(customerId) {
    var orderId=$("#orderId").val();
    $.get("ajax/order/chgCust?orderId="+orderId+"&customerId="+customerId, chgCustomerInForm);
    $("#editRow").modal("hide");
}
function chgCustomerInForm(data) {
    $('#customer').val(data.name);
}