var ajaxUrl = "ajax/op/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#orderProductsDatatable").DataTable({
        "paging": false,
        "info": true,
        "aoColumns": [
            {
                "mData": "aricle"
            },
            {
                "mData": "description"
            },
            {
                "mData": "price"
            },
            {
                "mData": "volume"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
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

/*$("#filterForm").submit(function () {
    filter();
    return false;
});
function filter() {
    $.get(ajaxUrl+"filter", function (data) {
        datatableApi.clear().rows.add(data).draw();
    });
}
function updateTable() {
    filter();
}
function resetFilter() {
    $("#filterForm").find(":input").val("");
    return false;
}*/
