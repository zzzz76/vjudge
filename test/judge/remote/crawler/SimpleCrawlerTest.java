package judge.remote.crawler;

import judge.BaseJunitTest;
import judge.tool.Handler;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author zzzz76
 */
public abstract class SimpleCrawlerTest extends BaseJunitTest {
    private final static Logger log = LoggerFactory.getLogger(SimpleCrawlerTest.class);

    @Test
    public abstract void testCrawl() throws Exception;

    protected Handler<RawProblemInfo> getHandler() {
        return new Handler<RawProblemInfo>() {
            @Override
            public void handle(RawProblemInfo info) {
                printProblem(info);
            }

            @Override
            public void onError(Throwable t) {
                log.error(t.getMessage(), t);
            }
        };
    }

    private void printProblem(RawProblemInfo info) {
        log.error(info.title);
        log.error(String.valueOf(info.timeLimit));
        log.error(String.valueOf(info.memoryLimit));
        log.error(info.source);
        log.error(info.url);
        log.error(info.description);
        log.error(info.input);
        log.error(info.output);
        log.error(info.hint);
        log.error(info.sampleInput);
        log.error(info.sampleOutput);
        log.error(String.valueOf(new Date()));
    }
}
