package judge.remote.provider.jsk;

import judge.BaseJunitTest;
import judge.bean.Description;
import judge.bean.Problem;
import judge.remote.crawler.RawProblemInfo;
import judge.tool.Handler;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 对计蒜客爬取器进行测试
 *
 * @author zzzz76
 */
public class JSKCrawlerTest extends BaseJunitTest{
    private final static Logger log = LoggerFactory.getLogger(JSKCrawlerTest.class);

    @Autowired
    private JSKCrawler crawler;

    @Test
    public void testCrawl() throws Exception {
        crawler.crawl("34486", new Handler<RawProblemInfo>() {
            @Override
            public void handle(RawProblemInfo info) {
                printProblem(info);
            }

            @Override
            public void onError(Throwable t) {
                log.error(t.getMessage(), t);
            }
        });
        System.err.println(1);
    }

    private void printProblem(RawProblemInfo info) {
        System.err.println(info.title);
        System.err.println(info.timeLimit);
        System.err.println(info.memoryLimit);
        System.err.println(info.source);
        System.err.println(info.url);
        System.err.println(info.description);
        System.err.println(info.input);
        System.err.println(info.output);
        System.err.println(info.hint);
        System.err.println(info.sampleInput);
        System.err.println(info.sampleOutput);
        System.err.println(new Date());
    }


}
