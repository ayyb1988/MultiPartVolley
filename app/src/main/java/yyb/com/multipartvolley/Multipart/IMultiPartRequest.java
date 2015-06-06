package yyb.com.multipartvolley.Multipart;

import java.io.File;
import java.util.Map;
/**
 * Created by yyb
 * on 15-2-4.
 */
public interface IMultiPartRequest {


    public Map<String,File> getFileUploads();
    
    public Map<String,String> getStringUploads();

    //add by yyb
    public void setFileBodyContentType(String contentType);

    public String getFileBodyContentType();
}