package com.hwj.bean;
import org.apache.commons.fileupload.FileItem;

import java.util.Map;

/**
 * @author houwenjing
 * @description
 * @date 2021/8/24 3:59 下午
 */
public class UploadParams {
    private Map<String, String> params;
    private Map<String, FileItem> fileParams;

    public UploadParams(Map<String, String> params, Map<String, FileItem> fileParams) {
        this.params = params;
        this.fileParams = fileParams;
    }

    public UploadParams() {
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Map<String, FileItem> getFileParams() {
        return fileParams;
    }

    public void setFileParams(Map<String, FileItem> fileParams) {
        this.fileParams = fileParams;
    }
}
