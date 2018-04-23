var ajaxUrl = "ajax/op/";
var datatableApi;


// $(document).ready(function () {
$(function () {
    datatableApi = $("#orderProductsDatatable").DataTable({
        "ajax": {
            "url": ajaxUrl + $("#orderId").val(),
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
                "mData": "volume",
                "render": renderEditVolume
            },
            {
                "mData": null,
                "render": function (data, type, row) {
                    return row.product.price * row.volume;
                }
            },
            /*            {
                            "render": renderEditBtn,
                            "defaultContent": "",
                            "orderable": false
                        },*/
            {
                "render": renderDeleteBtn,
                "defaultContent": "Delete",
                "orderable": false
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

function serialData() {
    var table = datatableApi;
    var data;

    table.rows().every(function (rowIdx, tableLoop, rowLoop) {
        data = this.data();

    });
    return data;
}

function renderEditVolume(data, type, row) {
    var art=row.product.article;
    var vol=row.volume;
    var inpId='op'+row.opId;
        return "<input id="+inpId+" type='number' onchange='saveData("+inpId+","+art+")' value='" + vol + "'>";
}

function saveData(inpId, article){
    var data1="&orderId="+$("#orderId").val()+"&article="+article+"&volume="+$(inpId).val();
    $.ajax({
        type: "POST",
        url: ajaxUrl+"editV",
        data: data1,
        success: function () {
            updateTable();
            // successNoty("common.saved");
        }
    });
}

function updateTable() {
    $.get(ajaxUrl + $("#orderId").val(), function (data) {
        datatableApi.clear().rows.add(data).draw();
    });
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.opId + ");'>" +
            "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
    }
}

/*function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.opId + ");'>" +
            "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
    }
}*/
