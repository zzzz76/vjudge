package judge.remote.provider.hdu;

import judge.BaseJunitTest;
import judge.remote.RemoteOj;
import judge.remote.account.RemoteAccount;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 对杭电题库登入器进行测试
 *
 * @author zzzz76
 */
public class HDULoginerTest extends BaseJunitTest {

    @Autowired
    private HDULoginer loginer;

    private HttpContext getNewContext() {
        CookieStore cookieStore = new BasicCookieStore();
        HttpContext context = new BasicHttpContext();
        context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
        return context;
    }

    @Test
    public void testLogin() throws Exception {
        RemoteAccount account = new RemoteAccount(
                RemoteOj.TKOJ,
                "gongtengxinyi",
                "123456ecnu",
                "",
                getNewContext()
        );
        loginer.login(account);
        System.err.println(1);
    }

}
