package judge.remote.provider.mxt;

import judge.BaseJunitTest;
import judge.remote.RemoteOj;
import judge.remote.status.SubmissionRemoteStatus;
import judge.remote.submitter.SubmissionInfo;
import judge.tool.Handler;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 对码学堂轮询器进行测试
 *
 * @author zzzz76
 */
public class MXTQuerierTest extends BaseJunitTest {
    private final static Logger log = LoggerFactory.getLogger(MXTQuerierTest.class);

    @Autowired
    private MXTQuerier querier;

    // 信号量
    private CountDownLatch doneSignal;

    @Test
    public void testQuery() throws Exception {
        doneSignal = new CountDownLatch(1);
        querier.query(fakeSubmissionInfo(), new Handler<SubmissionRemoteStatus>() {
            @Override
            public void handle(SubmissionRemoteStatus remoteStatus) {
                printRemoteStatus(remoteStatus);
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

    private void printRemoteStatus(SubmissionRemoteStatus remoteStatus) {
        System.err.println(remoteStatus.statusType.name());
        System.err.println(StringUtils.capitalize(remoteStatus.rawStatus));
        System.err.println(remoteStatus.executionTime);
        System.err.println(remoteStatus.executionMemory);
        System.err.println(StringUtils.left(remoteStatus.compilationErrorInfo, 10000));
        System.err.println(remoteStatus.failCase);
    }

    private SubmissionInfo fakeSubmissionInfo() {
        SubmissionInfo info = new SubmissionInfo();
        info.remoteOj = RemoteOj.MXT;
        info.remoteProblemId = "1691";
        info.remoteRunId= "683841";
        info.remoteAccountId = "ecnuv4@163.com";
        return info;
    }
}
