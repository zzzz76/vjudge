package judge.remote.provider.jsk;

import judge.remote.RemoteOjInfo;
import judge.remote.crawler.RawProblemInfo;
import judge.remote.crawler.SimpleCrawler;
import judge.tool.GsonUtil;
import judge.tool.Tools;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * User: zzzz76
 * Date: 2020-10-03
 */

@Component
public class JSKCrawler extends SimpleCrawler {

    @Override
    public RemoteOjInfo getOjInfo() {
        return JSKInfo.INFO;
    }

    @Override
    protected String getProblemUrl(String problemId) {
        return getHost().toURI() + "/t/" + problemId;
    }

    @Override
    protected void preValidate(String problemId) {
        Validate.isTrue(problemId.matches("[1-9]\\d*"));
    }

    @Override
    protected void populateProblemInfo(RawProblemInfo info, String problemId, String html) {
        String json = Tools.regFind(html, "var problem=(\\{[\\s\\S]*?\\});var").trim();
        info.title = GsonUtil.getStrMem(json, "title");
        info.timeLimit = Integer.parseInt(GsonUtil.getStrMem(json, "time_limit"));
        info.memoryLimit = Integer.parseInt(GsonUtil.getStrMem(json, "mem_limit"));
        info.description = GsonUtil.getStrMem(json, "description");
        info.sampleInput = GsonUtil.getStrMem(json, "sample_input");
        info.sampleOutput = GsonUtil.getStrMem(json, "sample_output");
        info.hint = GsonUtil.getStrMem(json, "hint");
        info.source = GsonUtil.getStrMem(json, "source");
    }
}
