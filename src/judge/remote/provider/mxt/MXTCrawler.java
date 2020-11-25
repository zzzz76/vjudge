package judge.remote.provider.mxt;

import judge.remote.RemoteOjInfo;
import judge.remote.crawler.RawProblemInfo;
import judge.remote.crawler.SimpleCrawler;
import judge.tool.Tools;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * User: zzzz76
 * Date: 2020-11-22
 */
@Component
public class MXTCrawler extends SimpleCrawler {

    @Override
    public RemoteOjInfo getOjInfo() {
        return MXTInfo.INFO;
    }

    @Override
    protected String getProblemUrl(String problemId) {
        return getHost().toURI() + "/course/" + problemId + ".html";
    }

    @Override
    protected void preValidate(String problemId) {
        Validate.isTrue(problemId.matches("[1-9]\\d*"));
    }

    @Override
    protected void populateProblemInfo(RawProblemInfo info, String problemId, String html) {
        info.title = Tools.regFind(html, "<h2>P" + problemId +" ([\\s\\S]*?)</h2>").trim();
        info.timeLimit = 1000;
        info.memoryLimit = 32768;
        info.description = Tools.regFind(html, "<div class=\"panel-heading\"><b>描述</b></div>([\\s\\S]*?</div>)</div>");
        info.input = Tools.regFind(html, "<div class=\"panel-heading\"><b>输入</b></div>([\\s\\S]*?</div>)</div>");
        info.output = Tools.regFind(html, "<div class=\"panel-heading\"><b>输出</b></div>([\\s\\S]*?</div>)</div>");
        info.sampleInput = Tools.regFind(html, "<div class=\"panel-heading\"><b>样例输入 </b></div>([\\s\\S]*?</div>)</div>");
        info.sampleOutput = Tools.regFind(html, "<div class=\"panel-heading\"><b>样例输出 </b></div>([\\s\\S]*?</div>)</div>");
        info.hint = Tools.regFind(html, "<div class=\"panel-heading\"><b>提示</b></div>([\\s\\S]*?</div>)</div>");
        info.source = Tools.regFind(html, "<b>来源 : </b><span>([\\s\\S]*?)</span>");
    }
}