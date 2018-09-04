var ajaxUrlCustomerProduct = "ajax/cp/";
var datatableApiCustomerProduct;

$(function () {
    datatableApiCustomerProduct = $("#customerProductDatatable").DataTable({
        "ajax": {
            "url": ajaxUrlCustomerProduct+$('#customerId').val(),
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
                // render: function(data){return '<input type="number" id="volume" name="volume">'}
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