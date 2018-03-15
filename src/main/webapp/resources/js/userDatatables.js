var ajaxUrl = "ajax/admin/users/";
var datatableUser;

/*function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}*/

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableUser.clear().rows.add(data).draw();
    });
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $.ajax({
        url: ajaxUrl + id,
        type: "POST",
        data: "enabled=" + enabled,
        success: function () {
            chkbox.closest("tr").toggleClass("disabled");
            successNoty(enabled ? "common.enabled" : "common.disabled");
        },
        error: function () {
            $(chkbox).prop("checked", !enabled);
        }
    });
}

// $(document).ready(function () {
$(function () {
    datatableUser = $("#userDatatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<a href='mailto:" + data + "'>" + data + "</a>";
                    }
                    return data;
                }
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='enable($(this)," + row.id + ");'/>";
                    }
                    return data;
                }
            },
            {
                "data": "registered",
                "render": function (date, type, row) {
                    if (type === "display") {
                        var dateObject=new Date(date);
                        return dateObject.toISOString().substring(0,10);
                        // return date.substring(0, 10);
                    }
                    return date;
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.enabled) {
                $(row).addClass("disabled");
            }
        }
        // "initComplete": makeEditable
    });
});



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

function saveUser() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $("#editRow").modal("hide");
            updateTable();
            // successNoty("common.saved");
        }
    });
}

/*function serialData() {
    var table = datatableUser;
    var data;

    table.rows().every(function (rowIdx, tableLoop, rowLoop) {
        data = this.data();

    });
    return data;
}*/
var form = $("#detailsForm");
function add() {
    $("#modalTitle").html(i18n["common.add"]);
    form.find(":input").val("");
    $("#editRow").modal();
}

/*function renderDeleteBtn(data, type, row) {
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
}*/
