package judge.remote.provider.ecn;

import judge.BaseJunitTest;
import judge.httpclient.DedicatedHttpClient;
import judge.httpclient.DedicatedHttpClientFactory;
import judge.remote.RemoteOjInfo;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 对华师大的验证工具进行测试
 *
 * @author zzzz76
 */
public class ECNVerifyUtilTest extends BaseJunitTest {

    @Autowired
    private DedicatedHttpClientFactory dedicatedHttpClientFactory;

    private RemoteOjInfo getOjInfo() {
        return ECNInfo.INFO;
    }

    private HttpContext getNewContext() {
        CookieStore cookieStore = new BasicCookieStore();
        HttpContext context = new BasicHttpContext();
        context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
        return context;
    }

    @Test
    public void testGetCaptcha() throws Exception{
        DedicatedHttpClient client = dedicatedHttpClientFactory.build(getOjInfo().mainHost, getNewContext(), getOjInfo().defaultChaset);
        ECNCaptchaRecognizer.printok(client);
//        System.err.println(res);
    }
}
