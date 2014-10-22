/**
 * Created by ylq on 14-5-30.
 */
function openWindow(title, url, w, h) {
    window.parent.openWindow(title, url, w, h);
}
function bangdingyesfn(yesFn) {
    window.parent.bangdingyesfn(yesFn);
}
function closeWindow() {
    window.parent.closeWindow();
}
function refreshpage(url) {
    window.parent.refreshpage();
}
// /提交父页面
function submitParentPage(url) {
    window.parent.submitParentPage();
}