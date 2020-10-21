package judge.remote.provider.jsk;

import judge.httpclient.DedicatedHttpClient;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;

/**
 * Created with IDEA
 * User: zzzz76
 * Date: 2020-10-09
 */
public class JSKTokenUtil {
    private static String token = null;

    public static String getToken(DedicatedHttpClient client) {
        if (token == null || token.isEmpty()) {
            CookieStore cookieStore = (CookieStore) client.getContext().getAttribute(HttpClientContext.COOKIE_STORE);
            for (Cookie cookie : cookieStore.getCookies()) {
                if (cookie.getName().equals("XSRF-TOKEN")) {
                    token = cookie.getValue();
                }
            }
        }
        return token;
    }
}
