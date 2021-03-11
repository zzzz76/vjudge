package judge.remote.provider.ecn;

import judge.remote.RemoteOj;
import judge.remote.RemoteOjInfo;
import org.apache.http.HttpHost;

/**
 * @author zzzz76
 */
public class ECNInfo {
    public static final RemoteOjInfo INFO = new RemoteOjInfo(
            RemoteOj.ECN,
            "ECN",
            new HttpHost("acm.ecnu.edu.cn", 443, "https")
    );

    static {
        INFO.faviconUrl = "images/remote_oj/ZOJ_favicon.ico";
    }
}
