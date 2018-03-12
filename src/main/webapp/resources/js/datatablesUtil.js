function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: "DELETE",
        success: function () {
            updateTable();
            // successNoty("Deleted");
        }
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

function serialData() {
    var table = datatableApiProd;
    var data;

    table.rows().every(function (rowIdx, tableLoop, rowLoop) {
        data = this.data();

    });
    return data;
}

function add() {
    $("#modalTitle").html(i18n["product.add"]);
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
