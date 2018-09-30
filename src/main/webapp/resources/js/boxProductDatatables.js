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
    $.ajax({
        type: "GET",
        url: ajaxUrlBoxProduct+"sendToCustomer/"+$("#boxId").val(),
        success: function () {
            $('#sendToCustomerButton').remove();
            successNoty("box.send");
        }
    })
}

//======================================
var failedNote;
function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(key) {
    closeNoty();
    new Noty({
        text: "<span class='glyphicon glyphicon-ok'></span> &nbsp;" + i18n[key],
        type: "success",
        layout: "bottomRight",
        timeout: 1000
    }).show();
}