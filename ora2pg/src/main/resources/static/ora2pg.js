(function ($, window, document) {

    // The $ is now locally scoped 

    // Listen for the jQuery ready event on the document
    $(function () {
        // The DOM is ready!
        console.log('The DOM is ready!');
        ora2PgExec = function() {
            $.ajax({
                url: "http://localhost:8080/api/ora2pg",
                type: "get",
                data: {
                    originalSqlStmt: $('.ora2pg-originalSqlStmt').val()
                },
                success: function (data) {
                    $('.ora2pg-id').val(data.id);
                    $('.ora2pg-convertedSqlStmt').val(data.convertedSqlStmt);
                }
            });
        }
    });

    // The rest of code goes here!
    console.log('The DOM may not be ready!');

}(window.jQuery, window, document));