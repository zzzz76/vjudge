package judge.remote.provider.jsk;

import judge.remote.crawler.SimpleCrawlerTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 对计蒜客爬取器进行测试
 *
 * @author zzzz76
 */
public class JSKCrawlerTest extends SimpleCrawlerTest{
    private final static Logger log = LoggerFactory.getLogger(JSKCrawlerTest.class);

    @Autowired
    private JSKCrawler crawler;

    @Override
    public void testCrawl() throws Exception {
        crawler.crawl("34486", getHandler());
        System.err.println(1);
    }
}
