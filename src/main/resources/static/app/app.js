var App = function () {
    // 默认的 Dropzone 参数
    var defaultDropzoneOpts = {
        url: "",
        paramName: "dropFile",
        maxFiles: 1, // 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1, // 一次上传的文件数量
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传 1 个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消"
    };


    /**
     * 初始化 Dropzone
     * @param opts
     */
    var handlerInitDropzone = function (opts) {
        // 关闭 Dropzone 的自动发现功能
        Dropzone.autoDiscover = false;
        // 继承
        $.extend(defaultDropzoneOpts, opts);
        new Dropzone(defaultDropzoneOpts.id, defaultDropzoneOpts);
    };


    return {
        /**
         * 初始化 Dropzone
         * @param opts
         */
        initDropzone: function(opts) {
            handlerInitDropzone(opts);
        },
    }
}();

/*
$(document).ready(function () {
   App.init();
});*/
