package judge.bean;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: zzzz76
 * Date: 2020-07-15
 */
public class Request {
    private Integer id;    //problemId
    private Integer uid;
    private Integer isOpen;
    private Integer res;    //result
    private String OJId;
    private String probNum;
    private String title;
    private String problem;
    private Description description;
    private Submission submission;
    private List dataList;
    private String language;
    private String source;
    private String redir;
    private String un;
    private String _64Format;
    private Integer isSup;
    private DataTablesPage dataTablesPage;
    private Map<String, String> languageList;
    private String submissionInfo;
    private Map<String, String> paraMap;

    public Integer getId() {
        return id;
    }

    public Request setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUid() {
        return uid;
    }

    public Request setUid(Integer uid) {
        this.uid = uid;
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

    public String getProblem() {
        return problem;
    }

    public Request setProblem(Problem problem) {
        if (problem != null) {
            this.problem = problem.getTitle();
        }
        return this;
    }

    public Description getDescription() {
        return description;
    }

    public Request setDescription(Description description) {
        this.description = description;
        return this;
    }

    public Submission getSubmission() {
        return submission;
    }

    public Request setSubmission(Submission submission) {
        this.submission = submission;
        return this;
    }

    public List getDataList() {
        return dataList;
    }

    public Request setDataList(List dataList) {
        this.dataList = dataList;
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

    public String getRedir() {
        return redir;
    }

    public Request setRedir(String redir) {
        this.redir = redir;
        return this;
    }

    public String getUn() {
        return un;
    }

    public Request setUn(String un) {
        this.un = un;
        return this;
    }

    public String get_64Format() {
        return _64Format;
    }

    public Request set_64Format(String _64Format) {
        this._64Format = _64Format;
        return this;
    }

    public Integer getIsSup() {
        return isSup;
    }

    public Request setIsSup(Integer isSup) {
        this.isSup = isSup;
        return this;
    }

    public DataTablesPage getDataTablesPage() {
        return dataTablesPage;
    }

    public Request setDataTablesPage(DataTablesPage dataTablesPage) {
        this.dataTablesPage = dataTablesPage;
        return this;
    }

    public Map<String, String> getLanguageList() {
        return languageList;
    }

    public Request setLanguageList(Map<String, String> languageList) {
        this.languageList = languageList;
        return this;
    }

    public String getSubmissionInfo() {
        return submissionInfo;
    }

    public Request setSubmissionInfo(String submissionInfo) {
        this.submissionInfo = submissionInfo;
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
