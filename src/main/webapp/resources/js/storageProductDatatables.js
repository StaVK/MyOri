var ajaxUrlStorageProduct = "ajax/sp/";
var datatableApiStorageProduct;

$(function () {
    datatableApiStorageProduct = $("#storageProductDatatable").DataTable({
        "ajax": {
            "url": ajaxUrlStorageProduct+$('#storageId').val(),
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
                "mData": "price"
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
var form = $("#detailsForm");

function add() {
    $("#modalTitle").html(i18n["addTitle"]);
    form.find(":input").val("");
    $("#editRow").modal();
}

function save() {
    var data="&storageId="+$("#storageId").val()+"&"+form.serialize();
    $.ajax({
        type: "POST",
        url: ajaxUrlStorageProduct,
        data: data,
        success: function () {
            $("#editRow").modal("hide");
            updateTable();
            // successNoty("common.saved");
        }
    });
}

function updateTable() {
    $.get(ajaxUrlStorageProduct+$("#storageId").val(), function (data) {
        datatableApiStorageProduct.clear().rows.add(data).draw();
    });
}