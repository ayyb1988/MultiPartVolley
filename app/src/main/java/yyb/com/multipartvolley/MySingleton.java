package yyb.com.multipartvolley;

/**
 * Created by yyb on 15-2-3.
 */

import android.content.Context;
import android.util.DisplayMetrics;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import yyb.com.multipartvolley.Multipart.MultiPartStack;

public class MySingleton {
    private static RequestQueue requestQueue;
    private static RequestQueue httpStackRequestQueue;
    private static Context mCtx;
    private static MySingleton mInstance;
    public static int ScreenW;
    public static int ScreenH;
    public static float ScreenD;

    private MySingleton(Context context) {
        mCtx = context;
        //网络请求队列
        requestQueue = getRequestQueue();
        //用于multipart 的请求队列
        httpStackRequestQueue = getHttpStackRequestQueue();
        //手机屏幕宽高密度
        setScreeParmas(context);

    }

    private void setScreeParmas(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        ScreenW = dm.widthPixels;
        ScreenH = dm.heightPixels;
        ScreenD = dm.density;
    }

    public static synchronized MySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, Object TAG) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public RequestQueue getHttpStackRequestQueue() {
        if (httpStackRequestQueue == null) {
            httpStackRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext(), new MultiPartStack());
        }
        return httpStackRequestQueue;
    }

    public <T> void addHttpStackToRequestQueue(Request<T> req, Object TAG) {
        req.setTag(TAG);
        getHttpStackRequestQueue().add(req);
    }


    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public <T> void removeFromRequestQueue(Object tag) {
        getRequestQueue().cancelAll(tag);
    }
}
