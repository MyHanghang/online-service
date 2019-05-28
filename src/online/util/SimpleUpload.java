package online.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;

import java.io.File;


/**
 * @ClassName QiniuUploadUtil
 * @Description TODO
 * @Author 我叫航航航啊
 * @Date 2019/5/10 15:46
 * @Version V2.0
 **/
public class SimpleUpload {
    private   String ACCESSKEY = "AKIDg7nLGu7WTGqJU9uRs2xe9QTTE0YzGRju";
    private   String SECRETKEY = "9dATjrwojgGD8NdmXafe3Y0No12vf9Pl";
    private   String BUCKETNAME = "online-service-video-1259035924";
    private   String REGIONID = "ap-chengdu";

    /**
     * 初始化CosClient相关配置， appid、accessKey、secretKey、region
     *
     * @return
     */

    public String SimpleUploadFileFromLocal(String s) {
        COSCredentials cred = new BasicCOSCredentials(ACCESSKEY, SECRETKEY);
        ClientConfig clientConfig = new ClientConfig(new Region(REGIONID));
        COSClient cosclient = new COSClient(cred, clientConfig);
        String name = s;
        System.out.println("进入腾讯云上传");
        System.out.println("连接成功！！");
        File filename = new File("H:/apache-tomcat-7.0.73/apache-tomcat-7.0.73/webapps/upload/" + name);
        String uploadName = NoUtils.getNo() + ".jpg";
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKETNAME, uploadName, filename);
        putObjectRequest.setStorageClass(StorageClass.Standard_IA);
        try {
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            String var8 = putObjectResult.getETag();
            System.out.println(var8);
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        cosclient.shutdown();
        return uploadName;
    }

}

