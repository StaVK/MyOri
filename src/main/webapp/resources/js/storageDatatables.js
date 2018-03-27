var ajaxUrlStorage = "ajax/storage/";
var datatableStorage;

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableStorage.clear().rows.add(data).draw();
    });
}

// $(document).ready(function () {
$(function () {
    datatableStorage = $("#storageDatatable").DataTable({
        "ajax": {
            "url": ajaxUrlStorage,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
        // "initComplete": makeEditable
    });
});
