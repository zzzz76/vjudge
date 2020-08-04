package judge.action.request;

import judge.action.response.DataTablesPage;
import judge.bean.Description;
import judge.bean.Problem;
import judge.bean.Submission;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: zzzz76
 * Date: 2020-07-15
 */
public class Request {
    private Integer id;    //problemId
    private Integer isOpen;
    private Integer res;    //result
    private String OJId;
    private String probNum;
    private String title;
    private Description description;
    private String language;
    private String source;
    private String un;
    private Integer isSup;
    private Map<String, String> paraMap;

    public Integer getId() {
        return id;
    }

    public Request setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public Request setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
        return this;
    }

    public Integer getRes() {
        return res;
    }

    public Request setRes(Integer res) {
        this.res = res;
        return this;
    }

    public String getOJId() {
        return OJId;
    }

    public Request setOJId(String OJId) {
        this.OJId = OJId;
        return this;
    }

    public String getProbNum() {
        return probNum;
    }

    public Request setProbNum(String probNum) {
        this.probNum = probNum;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Request setTitle(String title) {
        this.title = title;
        return this;
    }

    public Description getDescription() {
        return description;
    }

    public Request setDescription(Description description) {
        this.description = description;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public Request setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getSource() {
        return source;
    }

    public Request setSource(String source) {
        this.source = source;
        return this;
    }

    public String getUn() {
        return un;
    }

    public Request setUn(String un) {
        this.un = un;
        return this;
    }

    public Integer getIsSup() {
        return isSup;
    }

    public Request setIsSup(Integer isSup) {
        this.isSup = isSup;
        return this;
    }

    public Map<String, String> getParaMap() {
        return paraMap;
    }

    public Request setParaMap(Map<String, String> paraMap) {
        this.paraMap = paraMap;
        return this;
    }
}
