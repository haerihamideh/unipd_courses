$(document).ready(function() {
    $('.search-box input[type="text"]').on('keyup', function() {
        const searchText = $(this).val().toLowerCase();
        $('table tbody tr').each(function() {
            const rowText = $(this).text().toLowerCase();
            $(this).toggle(rowText.indexOf(searchText) > -1);
        });
    });
});