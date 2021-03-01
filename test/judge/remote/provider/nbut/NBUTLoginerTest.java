package judge.remote.provider.nbut;

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
 * 对宁波工程学院登入器进行测试
 *
 * @author zzzz76
 */
public class NBUTLoginerTest extends BaseJunitTest {

    @Autowired
    private NBUTLoginer loginer;

    private HttpContext getNewContext() {
        CookieStore cookieStore = new BasicCookieStore();
        HttpContext context = new BasicHttpContext();
        context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
        return context;
    }

    @Test
    public void testLogin() throws Exception {
        RemoteAccount account = new RemoteAccount(
                RemoteOj.NBUT,
                "jianghuchuan",
                "123456ecnu",
                "",
                getNewContext()
        );
        loginer.login(account);
        System.err.println(1);
    }

}
