package judge.remote.provider.mxt;

import judge.BaseJunitTest;
import judge.bean.Description;
import judge.bean.Problem;
import judge.remote.crawler.RawProblemInfo;
import judge.remote.provider.tkoj.TKOJCrawler;
import judge.tool.Handler;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 对码学堂爬取器进行测试
 *
 * @author zzzz76
 */
public class MXTCrawlerTest extends BaseJunitTest{
    private final static Logger log = LoggerFactory.getLogger(MXTCrawlerTest.class);

    @Autowired
    private MXTCrawler crawler;

    // 信号量
    private CountDownLatch doneSignal;

    @Test
    public void testCrawl() throws Exception {
        doneSignal = new CountDownLatch(1);
        crawler.crawl("1008", new Handler<RawProblemInfo>() {
            @Override
            public void handle(RawProblemInfo info) {
                printProblem(info);
                doneSignal.countDown();
            }

            @Override
            public void onError(Throwable t) {
                log.error(t.getMessage(), t);
                doneSignal.countDown();
            }
        });
        doneSignal.await(5, TimeUnit.MINUTES);
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
