package imocc.com.genericsdk.imageloader;

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
}
