var ajaxUrlBoxProduct = "ajax/boxProduct/";
var datatableApiBoxProduct;

$(function () {
    datatableApiBoxProduct = $("#boxProductDatatable").DataTable({
        "ajax": {
            'url': ajaxUrlBoxProduct+$("#boxId").val(),
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
            /*            {
                            "mData": "price"
                        },*/
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

function printSpReport() {
    $.get(ajaxUrlBoxProduct+"printSpReport/"+$("#boxId").val());
}

function sendToCustomer() {
    // $.get(ajaxUrlBoxProduct+"sendToCustomer/"+$("#boxId").val());
    $.ajax({
        type: "GET",
        url: ajaxUrlBoxProduct+"sendToCustomer/"+$("#boxId").val(),
        success: function () {
            $('#sendToCustomerButton').remove();
        }
    })
}