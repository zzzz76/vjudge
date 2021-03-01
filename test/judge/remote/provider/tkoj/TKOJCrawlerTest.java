package judge.remote.provider.tkoj;

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
 * 对TK题库爬取器进行测试
 *
 * @author zzzz76
 */
public class TKOJCrawlerTest extends BaseJunitTest{
    private final static Logger log = LoggerFactory.getLogger(TKOJCrawlerTest.class);

    @Autowired
    private TKOJCrawler crawler;

    private Problem problem = new Problem();
    private Description description = new Description();

    @Test
    public void testCrawl() throws Exception {
        crawler.crawl("1000", new Handler<RawProblemInfo>() {
            @Override
            public void handle(RawProblemInfo info) {
                problem.setTitle(info.title);
                problem.setTimeLimit(info.timeLimit);
                problem.setMemoryLimit(info.memoryLimit);
                problem.setSource(info.source);
                problem.setUrl(info.url);

                description.setDescription(info.description);
                description.setInput(info.input);
                description.setOutput(info.output);
                description.setHint(info.hint);
                description.setSampleInput(info.sampleInput);
                description.setSampleOutput(info.sampleOutput);
                description.setUpdateTime(new Date());

                printProblem();
                printDescription();
            }

            @Override
            public void onError(Throwable t) {
                log.error(t.getMessage(), t);
            }
        });
        System.err.println(1);
    }

    private void printProblem() {
        System.err.println(problem.getTitle());
        System.err.println(problem.getTimeLimit());
        System.err.println(problem.getMemoryLimit());
        System.err.println(problem.getSource());
        System.err.println(problem.getUrl());
    }

    private void printDescription() {
        System.err.println(description.getDescription());
        System.err.println(description.getInput());
        System.err.println(description.getOutput());
        System.err.println(description.getHint());
        System.err.println(description.getSampleInput());
        System.err.println(description.getSampleOutput());
        System.err.println(description.getUpdateTime());
    }
}
