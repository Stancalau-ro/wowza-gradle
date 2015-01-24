package ro.stancalau.wowzagradle;

import com.wowza.wms.logging.WMSLogger;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class HttpUtil {

    private static final HttpClientBuilder BUILDER = HttpClientBuilder.create();
    private static final Logger LOGGER = WMSLogger.getLogger(HttpUtil.class);

    public static HttpResponse callServiceHttp(String url, List<NameValuePair> params) {

        CloseableHttpClient httpclient = BUILDER.build();
        HttpPost httppost = new HttpPost(url);
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params));
            return httpclient.execute(httppost);
        } catch (IOException e) {
            LOGGER.error("Error calling " + url, e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                LOGGER.error("Cannot close http client.", e);
            }
        }
        return null;
    }
}
