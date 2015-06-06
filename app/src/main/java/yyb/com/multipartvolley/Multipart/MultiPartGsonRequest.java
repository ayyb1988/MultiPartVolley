package yyb.com.multipartvolley.Multipart;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import yyb.com.multipartvolley.GsonRequest;

/**
 * Created by yyb
 * on 15-3-26.
 */
public class MultiPartGsonRequest<T> extends GsonRequest<T> implements IMultiPartRequest {


    /* To hold the parameter name and the File to upload */
    private Map<String, File> fileUploads = new HashMap<String, File>();
    private String fileBoyeType = "";

    public static String Gzip = "application/zip";
    public static String Mpeg3 = "application/mpeg3";


    /**
     * Creates a new request with the given method.
     *
     * @param method        the request {@link com.android.volley.Request.Method} to use
     * @param url           URL to fetch the string at
     * @param listener      Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public MultiPartGsonRequest(int method, String url, Class<T> clazz, Map<String, File> fileUploads, Map<String, String> params, Listener<T> listener,
                                ErrorListener errorListener) {
        super(method, url, params, clazz, listener, errorListener);
        this.fileUploads = fileUploads;
    }


    /**
     * 要上传的文件
     */
    public Map<String, File> getFileUploads() {
        return fileUploads;
    }

    /**
     * 要上传的参数
     */
    public Map<String, String> getStringUploads() {
        return params;
    }

    @Override
    public void setFileBodyContentType(String contentType) {
        this.fileBoyeType = contentType;
    }

    @Override
    public String getFileBodyContentType() {
        return fileBoyeType;
    }


    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        return super.parseNetworkResponse(response);
    }

    @Override
    protected void deliverResponse(T response) {
        super.deliverResponse(response);
    }

    /**
     * 空表示不上传
     * 这个返回null，很重要。在multipartstack的createMultiPartRequest方法中
     * if (request.getBodyContentType() != null),如果返回的不为空,就会添加普通交互的contenttype。
     */
    public String getBodyContentType() {
        return null;
    }
}
