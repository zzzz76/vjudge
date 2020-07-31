package judge.action.response;

import org.apache.struts2.json.annotations.JSON;

/**
 * Created with IDEA
 * User: zzzz76
 * Date: 2020-07-13
 */
public class Response {
    private BaseResult baseResult;
    private String message;

    @JSON(name = "data")
    public BaseResult getBaseResult() {
        return baseResult;
    }

    public void setBaseResult(BaseResult baseResult) {
        this.baseResult = baseResult;
    }

    @JSON(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
