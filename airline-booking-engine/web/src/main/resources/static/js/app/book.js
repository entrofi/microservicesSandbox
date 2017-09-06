$(function() {
    $('#birthday').datetimepicker({
        format: 'LL',
        viewMode: 'years'
    });

    $('#collapseButtonRow').click(
        function () {
            $("#collapseButtonRow").hide();
        }
    )
});