package judge.remote.provider.mxt;

import judge.httpclient.DedicatedHttpClient;
import judge.httpclient.HttpStatusValidator;
import judge.remote.RemoteOjInfo;
import judge.remote.account.RemoteAccount;
import judge.remote.crawler.AuthenticatedCrawler;
import judge.remote.crawler.RawProblemInfo;
import judge.tool.GsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.http.client.methods.HttpPost;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * User: zzzz76
 * Date: 2020-11-28
 */
@Component
public class MXTCrawler extends AuthenticatedCrawler {

    @Override
    public RemoteOjInfo getOjInfo() {
        return MXTInfo.INFO;
    }

    protected void preValidate(String problemId) {
        Validate.isTrue(problemId.matches("[1-9]\\d*"));
    }

    @Override
    protected RawProblemInfo crawl(String problemId, RemoteAccount remoteAccount, DedicatedHttpClient client) throws Exception {
        HttpPost post = new HttpPost("/problem/7/" + problemId + "/one");
        String post_json = client.execute(post, HttpStatusValidator.SC_OK).getBody();
        GsonUtil gsonUtil = new GsonUtil(post_json);

        RawProblemInfo info = new RawProblemInfo();
        info.title = gsonUtil.getMemStr("title");
        info.timeLimit = Integer.parseInt(gsonUtil.getMemStr("time_limit")) * 1000;
        info.memoryLimit = Integer.parseInt(gsonUtil.getMemStr("memory_limit")) * 1024;
        info.description = "<link rel=\"stylesheet\" href=\"https://www.maxuetang.cn/lxojres/mxt-editor/css/mxt-editor.min.css?v=20190117\"/>";
        info.description += gsonUtil.getMemStr("description");
        info.input = gsonUtil.getMemStr("input");
        info.output = gsonUtil.getMemStr("output");
        info.sampleInput = gsonUtil.getMemStr("sample_input");
        info.sampleOutput = gsonUtil.getMemStr("sample_output");
        info.hint = gsonUtil.getMemStr("hint");
        info.source = gsonUtil.getMemStr("source");
        info.url = getHost().toURI() + "/course/" + problemId + ".html";

        Validate.isTrue(!StringUtils.isBlank(info.title));
        Validate.isTrue(info.timeLimit > 2);
        return info;
    }
}
