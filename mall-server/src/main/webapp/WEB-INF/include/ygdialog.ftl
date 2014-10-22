<div id="mainModal" class="modal fade">
    <div id="dialogWidth" class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 id="dialogTitle" class="modal-title"></h4>
            </div>
            <iframe width="100%" id="dialogFrame" scrolling="auto" frameborder="0"
                    border="0" name="dialogFrame"></iframe>
            <div class="modal-footer">
                <button id="yesbutton" hidden="hidden" type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<script type="text/javascript">
    function openWindow(title, url, w, h) {
        $("#dialogTitle").html(title);
        if (w != '') {
            $("#dialogWidth").attr("style", "width:" + w + "px");
        }
        if (h != '') {
            //56和78分别是header和footer的高度
            $("#dialogFrame").attr("height", h - 56 - 78);
        }
        $("#dialogFrame").attr("src", url);
        $("#mainModal").modal('show');
    }
    function bangdingyesfn(yesfn) {
        $("#yesbutton").show();
        $("#yesbutton").unbind('click');
        $("#yesbutton").bind('click', yesfn);
    }
    function closeWindow() {
        $("#mainModal").modal('hide');
    }
    // 刷新父页面
    function refreshpage(url) {
        try {
            setTimeout(function () {
                if (url == '' || url == null || typeof (url) == "undefined") {
                    window.document.mainframe.location.reload();
                } else {
                    window.document.mainframe.location = url;
                }
            }, 200);
        } catch (e) {
        }
    }
    // /提交父页面
    function submitParentPage(url) {
        try {
            setTimeout(function () {
                var parentForm = window.document.mainframe.document.forms[0];
                if (parentForm) {
                    if (url) {
                        parentForm.action = url;
                    }
                    parentForm.submit();
                }
                else {
                    window.document.mainframe.location.reload();
                }
            }, 200);
        } catch (e) {
        }
    }
</script>