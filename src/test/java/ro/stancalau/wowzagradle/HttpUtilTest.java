package ro.stancalau.wowzagradle;

import junit.framework.TestCase;
import org.apache.http.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HttpUtilTest extends TestCase {

    private static final String workingUrl = "http://en.wikipedia.org/wiki/Text_file";
    private static final String brokenUrl = "sdfdsgfdgd";
    private static final String missingUrl = "http://en.wikipedia.org/my_missing_page.txt";

    @Test
    public void testCallServiceHttpWithValidService(){
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        HttpResponse response = HttpUtil.callServiceHttp(workingUrl, parameters);
        final int statusCode = response.getStatusLine().getStatusCode();
        assertEquals(HttpStatus.SC_OK, statusCode);
    }

    @Test
    public void testCallServiceHttpWithBrokenService(){
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        HttpResponse response = HttpUtil.callServiceHttp(brokenUrl, parameters);
        assertNull(response);
    }

    @Test
    public void testCallServiceHttpWithMissingService(){
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        HttpResponse response = HttpUtil.callServiceHttp(missingUrl, parameters);
        final int statusCode = response.getStatusLine().getStatusCode();
        assertEquals(HttpStatus.SC_NOT_FOUND, statusCode);
    }
}