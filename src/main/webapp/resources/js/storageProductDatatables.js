var ajaxUrlStorageProduct = "ajax/sp/";
var datatableApiStorageProduct;

var fileSelect = document.getElementById('file');
var uploadButton = document.getElementById('upload-button');

$(function () {

    datatableApiStorageProduct = $("#storageProductDatatable").DataTable({
        "ajax": {
            "url": ajaxUrlStorageProduct + $('#storageId').val(),
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
            },
            {
                "mData": "reserve",
                "render": function (data, type, row) {
                    var reserveSet = new Set(row.reserve);
                    var summ = 0;

                    for (let item of reserveSet) summ = summ + item.reserveVolume;
                    return summ;
                }
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

function selectFileXLS() {
    $('#fileXLS').trigger('click');
}


function importNewProductInStorageFromXLS(event) {
    event.preventDefault();

    // Update button text.
    uploadButton.innerHTML = i18n["common.load"];

    // The rest of the code will go here...
    var files = fileSelect.files;

    // Create a new FormData object.
    var formData = new FormData();

    var file=files[0];
    // Add the file to the request.
    formData.append('file', file, file.name);
    formData.append('storage', $("#storageId").val());

    $.ajax({
        url: ajaxUrlStorageProduct + "upload",
        type: 'POST',
        data: formData,
        async: true,
        cache: false,
        contentType: false,
        enctype: 'multipart/form-data',
        processData: false,
        success: function () {
            // $('#upload-button').html(i18n["addTitle"])
            fileSelect.value="",
            uploadButton.innerHTML=i18n["common.import"],
            updateTable();
        }
    });
return false;
}

function save() {
    var data = "&storageId=" + $("#storageId").val() + "&" + form.serialize();
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
    $.get(ajaxUrlStorageProduct + $("#storageId").val(), function (data) {
        datatableApiStorageProduct.clear().rows.add(data).draw();
    });
}

function printSpReport() {
    $.get(ajaxUrlStorageProduct+"printSpReport/"+$("#storageId").val());
}