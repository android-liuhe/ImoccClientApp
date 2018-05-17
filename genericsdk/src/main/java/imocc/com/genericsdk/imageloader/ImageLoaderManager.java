package imocc.com.genericsdk.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import imocc.com.genericsdk.R;

/**
 * @author liuhe
 * @fuction 初始化UniverImageLoader,用于加载网络图片
 */

public class ImageLoaderManager {

    private static final int THREAD_COUNT = 4; //最大线程数
    private static final int PROPRITY = 2; //图片加载优先级
    private static final int DISK_CACHE_SIZE = 50 * 1024; //最多缓存图片大小
    private static final int CONNECTION_TIME = 5 * 1000; //链接超时时间
    private static final int READ_TIME_OUT = 30 * 1000; //读取超时时间

    private static ImageLoaderManager mInstance;
    private static ImageLoader mImageLoader;

    public static ImageLoaderManager getInstance(Context context){

        if (mInstance == null){
            synchronized (ImageLoaderManager.class){
                if (mInstance == null){
                    mInstance = new ImageLoaderManager(context);
                }
            }
        }
        return mInstance;
    }

    private ImageLoaderManager(Context context){

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
                .Builder(context)
                .threadPoolSize(THREAD_COUNT) //设置最大线程数
                .threadPriority(Thread.NORM_PRIORITY - PROPRITY) //设置优先级
                .denyCacheImageMultipleSizesInMemory() //防止缓存多套尺寸图片到我们的内存当中
                .memoryCache(new WeakMemoryCache()) //使用弱引用缓存内存
                .diskCacheSize(DISK_CACHE_SIZE) //分配硬盘缓存大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //使用MD5命名文件
                .tasksProcessingOrder(QueueProcessingType.LIFO) // 图片下载顺序
                .defaultDisplayImageOptions(getDefaultOptions()) //默认图片加载Options
                .imageDownloader(new BaseImageDownloader(context, CONNECTION_TIME
                    , READ_TIME_OUT)) //设置图片下载器
                .writeDebugLogs() //debug环境会输出日志
                .build();

        ImageLoader.getInstance().init(configuration);
        mImageLoader = ImageLoader.getInstance();

    }

    /**
     * 实现我们默认的Options
     * @return
     */
    public DisplayImageOptions getDefaultOptions() {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.xadsdk_img_error) //在我们图片地址为空时显示的默认图片
                .showImageOnFail(R.drawable.xadsdk_img_error) //图片下载失败所显示的默认图片
                .cacheInMemory(true) //设置图片可以缓存到内存
                .cacheOnDisk(true) //设置图片可以缓存到硬盘
                .bitmapConfig(Bitmap.Config.RGB_565) //使用的图片解码类型
                .decodingOptions(new BitmapFactory.Options()) //图片解码配置
                .build();

        return options;
    }

    /**
     * 封装加载图片api
     * @param url
     * @param imageView
     * @param options
     * @param loadingListener
     */
    public void displayImage(String url, ImageView imageView
                            , DisplayImageOptions options
                            , ImageLoadingListener loadingListener){
        if (mImageLoader != null){
            mImageLoader.displayImage(url, imageView, options, loadingListener);
        }
    }

    public void displayImage(String url, ImageView imageView
                            , ImageLoadingListener loadingListener){
        displayImage(url, imageView, null, loadingListener);
    }

    public void displayImage(String url, ImageView imageView){
        displayImage(url,imageView, null, null);
    }
}
