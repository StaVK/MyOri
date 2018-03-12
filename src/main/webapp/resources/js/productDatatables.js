var ajaxUrlProduct = "ajax/product/";
var datatableApiProd;

$(function () {
    datatableApiProd = $("#productDatatable").DataTable({
        "ajax": {
            "url": ajaxUrlProduct,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "aoColumns": [
            {
                "mData": "article"
            },
            {
                "mData": "description"
            },
            {
                "mData": "price"
            },
            {
                mData: "volume",
                render: function(data){return '<input type="number" id="volume" name="volume">'}
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



