package judge.remote.provider.jsk;

import judge.BaseJunitTest;
import judge.remote.RemoteOj;
import judge.remote.submitter.SubmissionInfo;
import judge.remote.submitter.SubmissionReceipt;
import judge.tool.Handler;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 对计蒜客提交器进行测试
 *
 * @author zzzz76
 */
public class JSKSubmitterTest extends BaseJunitTest {
    private final static Logger log = LoggerFactory.getLogger(JSKSubmitterTest.class);

    @Autowired
    private JSKSubmitter submitter;

    // 信号量
    private CountDownLatch doneSignal;

    @Test
    public void testSubmitCode() throws Exception {
        doneSignal = new CountDownLatch(1);
        submitter.submitCode(fakeSubmissionInfo(),new Handler<SubmissionReceipt>() {
            @Override
            public void handle(SubmissionReceipt receipt) {
                printReceipt(receipt);
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

    private SubmissionInfo fakeSubmissionInfo() {
        SubmissionInfo info = new SubmissionInfo();
        info.remotelanguage = "c";
        info.remoteOj = RemoteOj.JSK;
        info.remoteProblemId = "34486";
        info.sourceCode =
                "#include <stdio.h>\n" +
                "int main() {\n" +
                "    int A, B;\n" +
                "    scanf(\"%d %d\", &A, &B);\n" +
                "    printf(\"%d\\n\", A + B);\n" +
                "    return 0;\n" +
                "}";
        return info;
    }

    private void printReceipt(SubmissionReceipt receipt) {
        System.err.println(receipt.remoteRunId);
        System.err.println(receipt.remoteAccountId);
        System.err.println(receipt.errorStatus);
        System.err.println(receipt.submitTime);
    }
}
