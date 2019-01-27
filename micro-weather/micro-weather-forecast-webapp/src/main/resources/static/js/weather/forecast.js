/**
 * forecast页面下拉框事件
 */
$(function () {
    $("#selectCityId").change(function () {
        var cityId = $("#selectCityId").val();
        var url = '/forecast/cities/' + cityId;
        window.location.href = url;
    })
});