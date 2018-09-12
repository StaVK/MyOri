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
                "mData": "opId",
                'visible': false
            },
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
            {
                "mData": "reserve",
                "defaultContent": "zero",
                "render": renderReserved
            },
            {
                "mData": "available",
                "defaultContent": "ZeroAvailable"
            },
            {
                "mData": "executedVolume"
            },
            {
                "render": renderDeleteBtn,
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        //TODO Подсчитывать итого:
/*        "footerCallback": function (row, data, start, end, display) {
            var api = this.api(), data;

            // Remove the formatting to get integer data for summation
            var intVal = function ( i ) {
                return typeof i === 'string' ?
                    i.replace(/[\$,]/g, '')*1 :
                    typeof i === 'number' ?
                        i : 0;
            };
            // Total over all pages
            total = api
                .column( 4 )
                .data()
                .reduce( function (a, b) {
                    // return intVal(a) + intVal(b);
                    return "test";
                }, 0 );

            // Total over this page
            pageTotal = api
                .column( 4, { page: 'current'} )
                .data()
                .reduce( function (a, b) {
                    //return intVal(a) + intVal(b);
                    return "test";
                }, 0 );

            // Update footer
            $( api.column( 4 ).footer() ).html(
                '$'+pageTotal +' ( $'+ total +' total)'
            );
        },*/
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    // makeEditable();
});

function renderReserved(data, type, row) {
    // var reserveSet=new Set(row.reserve);
    var reserve=row.reserve;

    if (row.volume !== row.executedVolume) {

        return "<input id=rp" + row.opId + " type='number' onchange='changeReserve(" + row.opId + ")' value='" + reserve + "'>";
    }
    // for (let item of reserveSet) summ=summ+item.reserveVolume;
    else return reserve;
}

function changeReserve(opId) {

    var idTmp = '#rp' + opId;
    var data1 = "&opId=" + opId + "&reserveVolume=" + $(idTmp).val();
    $.ajax({
        type: "POST",
        url: "ajax/rp/editV",
        data: data1,
        success: function () {
            updateTable();
            // successNoty("common.saved");
        }
    });
}

function serialData() {
    var table = datatableApi;
    var data;

    table.rows().every(function (rowIdx, tableLoop, rowLoop) {
        data = this.data();

    });
    return data;
}

function renderEditVolume(data, type, row) {

    if($("#status").val()==0){
    // if (row.status === 0) {
    //     var art = row.product.article;
        var vol = row.volume;
        var opId = 'op' + row.opId;
        return "<input id=" + opId + " type='number' onchange='saveData(" + row.opId + ")' value='" + vol + "'>";
    }
    else return row.volume;

}

function saveData(opId) {
    // var data1="&orderId="+$("#orderId").val()+"&article="+article+"&volume="+$(opId).val();
    var idTmp = '#op' + opId;
    var data1 = "&opId=" + opId + "&volume=" + $(idTmp).val();
    $.ajax({
        type: "POST",
        url: ajaxUrl + "editV",
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
    if (row.status === 2) {
        return "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span>";
    }
    else if (type === "display") {
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
function selectCustomer(customerId) {
    var orderId = $("#orderId").val();
    $.get("ajax/order/chgCust?orderId=" + orderId + "&customerId=" + customerId, chgCustomerInForm);
    $("#editRow").modal("hide");
}

function chgCustomerInForm(data) {
    $('#customer').val(data.name);
}

