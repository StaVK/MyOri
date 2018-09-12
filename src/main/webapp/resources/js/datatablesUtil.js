var form;

/*function makeEditable() {
    form = $("#detailsForm");
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}*/

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: "DELETE",
        success: function () {
            updateTable();
            successNoty("Deleted");
        }
    });
}
function updateRow(id) {
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $("#editRow").modal();
    });
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.id + ");'>" +
            "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
    }
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.id + ");'>" +
            "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
    }
}

function save() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: serialData(),
        success: function () {
            $("#editRow").modal("hide");
            updateTable();
            // successNoty("common.saved");
        }
    });
}

function editCustomer() {
    $("#modalTitle").html(i18n["customer.selectCustomer"]);
    $("#editRow").modal();
}


function addProdInOrder(orderId) {
    var form=$("#addProductInOrderForm");
    var data=form.serialize()+"&orderId="+orderId;
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: data,
        success: function () {
            form.find(":input").val("");
            updateTable();
            // successNoty("common.saved");
        }
    });
}

function changeStatus() {
    var orderId=$("#orderId").val();
    // $.get("ajax/order/setStatusWork?orderId="+orderId);
    $.ajax({
        type: "GET",
        url: "ajax/order/setStatusWork?orderId="+orderId,
        success: function () {
            updateTable();
            $('#addProductInOrderForm').remove();
            $('#statusButton').remove();

        }
    })
}

