package judge.remote.provider.tkoj;

import judge.httpclient.DedicatedHttpClient;
import judge.httpclient.HttpStatusValidator;
import judge.remote.RemoteOjInfo;
import judge.remote.querier.SyncQuerier;
import judge.remote.status.SubmissionRemoteStatus;
import judge.remote.status.SubstringNormalizer;
import judge.remote.submitter.SubmissionInfo;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * User: zzzz76
 * Date: 2020-12-10
 */
@Component
public class TKOJQuerier extends SyncQuerier {

    private static final String[] statusArray = new String[]{
            "Pending","Pending Rejudging","Compiling","Running Judging","Accepted","Presentation Error","Wrong Answer","Time Limit Exceed","Memory Limit Exceed","Output Limit Exceed","Runtime Error","Compile Error","Compile OK", "Runtime Finish"
    };

    @Override
    public RemoteOjInfo getOjInfo() {
        return TKOJInfo.INFO;
    }

    @Override
    protected SubmissionRemoteStatus query(SubmissionInfo info) throws Exception {
        DedicatedHttpClient client = dedicatedHttpClientFactory.build(getOjInfo().mainHost, getOjInfo().defaultChaset);
        String result = client.get("/status-ajax.php?solution_id=" + info.remoteRunId, HttpStatusValidator.SC_OK).getBody();
        String[] results = result.split(",");

        SubmissionRemoteStatus status = new SubmissionRemoteStatus();
        status.rawStatus = statusArray[Integer.parseInt(results[0])];
        status.statusType = SubstringNormalizer.DEFAULT.getStatusType(status.rawStatus);
        status.executionMemory = Integer.parseInt(results[1]);
        status.executionTime = Integer.parseInt(results[2]);

        return status;
    }
}
