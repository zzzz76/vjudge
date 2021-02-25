package judge.remote.provider.hdu;

import judge.BaseJunitTest;
import judge.remote.RemoteOj;
import judge.remote.provider.tkoj.TKOJSubmitter;
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
 * 对杭电题库提交器进行测试
 *
 * @author zzzz76
 */
public class HDUSubmitterTest extends BaseJunitTest {
    private final static Logger log = LoggerFactory.getLogger(HDUSubmitterTest.class);

    @Autowired
    private HDUSubmitter submitter;

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
        info.remotelanguage = "2";
        info.remoteOj = RemoteOj.HDU;
        info.remoteProblemId = "1003";
        info.sourceCode =
                "#include <cstdio>\n" +
                "#include <cstdlib>\n" +
                "int main() {\n" +
                "    int a, b;\n" +
                "    while (scanf(\"%d%d\", &a, &b) != EOF) {\n" +
                "        printf(\"%d\\n\", a + b);\n" +
                "    }\n" +
                "    return EXIT_SUCCESS;\n" +
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
