var ajaxUrlCustomer = "ajax/admin/users/";
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
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "id",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='radio' name='customerId' onclick='selectCustomer("+row.id+")'/>";
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



