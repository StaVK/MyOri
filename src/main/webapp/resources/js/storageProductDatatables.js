var ajaxUrlStorageProduct = "ajax/storage/";
var datatableApiStorageProduct;

$(function () {
    datatableApiStorageProduct = $("#storageProductDatatable").DataTable({
        "ajax": {
            "url": ajaxUrlStorageProduct+"products/"+$('#storageId').val(),
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
                "mData": "product.price"
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