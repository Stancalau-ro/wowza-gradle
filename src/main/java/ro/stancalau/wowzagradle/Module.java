package ro.stancalau.wowzagradle;

import com.wowza.wms.amf.AMFDataList;
import com.wowza.wms.application.IApplicationInstance;
import com.wowza.wms.client.IClient;
import com.wowza.wms.module.ModuleBase;
import com.wowza.wms.request.RequestFunction;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

import java.util.Collections;
import java.util.List;

public class Module extends ModuleBase {

    private static final String url = "http://en.wikipedia.org/wiki/Text_file";

    public void onAppStart(IApplicationInstance appInstance) {
        String fullname = appInstance.getApplication().getName() + "/"
            + appInstance.getName();
        getLogger().info("onAppStart: " + fullname);
    }

    public void onAppStop(IApplicationInstance appInstance) {
        String fullname = appInstance.getApplication().getName() + "/"
            + appInstance.getName();
        getLogger().info("onAppStop: " + fullname);
    }

    public void onConnect(IClient client, RequestFunction function, AMFDataList params) {

        List<NameValuePair> parameters = Collections.emptyList();
        HttpResponse response = HttpUtil.callServiceHttp(url, parameters);

        if (response != null) {
            getLogger().info("HTTP code on calling " + url + " is: " + response.getStatusLine().getStatusCode());
        }
    }
}