package imocc.com.genericsdk.okhttp;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import imocc.com.genericsdk.okhttp.https.HttpsUtils;
import imocc.com.genericsdk.okhttp.response.CommonJsonCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author liuhe
 * @function 用来发送get, post请求的工具类，包括设置一些请求的共用参数
 */

public class CommonHttpClient {

   private static final int TIME_OUT = 30;
   private static OkHttpClient mOkhttpClient;

   static {

       //创建OkHttpClient的构建者
       OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
       //设置链接超市参数
       okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
       okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
       okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
       //支持重定向
       okHttpClientBuilder.followRedirects(true);
       //设置支持Https,新人所有证书
       okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
           @Override
           public boolean verify(String hostname, SSLSession session) {
               return true;
           }
       });
       okHttpClientBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());

       //生成OKHttpClient对象
       mOkhttpClient = okHttpClientBuilder.build();
   }

    /**
     * 发送具体的Http/Https请求
     * @param request
     * @param callback
     * @return Call
     */
   public static Call senRequest(Request request, CommonJsonCallback callback){
       Call call = mOkhttpClient.newCall(request);
       call.enqueue(callback);
       return call;
   }

}
