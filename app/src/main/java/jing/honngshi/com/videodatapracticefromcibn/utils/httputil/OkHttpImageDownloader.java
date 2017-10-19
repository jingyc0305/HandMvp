package jing.honngshi.com.videodatapracticefromcibn.utils.httputil;

import com.orhanobut.logger.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import jing.honngshi.com.videodatapracticefromcibn.utils.otherutil.FileUtil;
import okhttp3.Request;

/**
 * Created by JIngYuchun on 2017/10/19.
 */

public class OkHttpImageDownloader {

    public static void download(String url){
        final Request request = new Request.Builder().url(url).build();
        RetrofitFactory.getOKHttpClient().newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Logger.d(e);
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Logger.i("下载图片成功");
                FileUtil.createSdDir();
                String url = response.request().url().toString();
                int index = url.lastIndexOf("/");
                String pictureName = url.substring(index+1);
                if(FileUtil.isFileExist(pictureName)){
                    return;
                }
                Logger.i("pictureName="+pictureName);
                FileOutputStream fos = new FileOutputStream(FileUtil.createFile(pictureName));
                InputStream in = response.body().byteStream();
                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = in.read(buf))!=-1){
                    fos.write(buf,0,len);
                }
                fos.flush();
                in.close();
                fos.close();
            }
        });
    }
}
