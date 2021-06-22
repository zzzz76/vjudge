package judge.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import judge.remote.RemoteOjInfo;
import judge.remote.provider.acdream.ACdreamInfo;
import judge.remote.provider.aizu.AizuInfo;
import judge.remote.provider.codeforces.CodeForcesInfo;
import judge.remote.provider.codeforcesgym.CodeForcesGymInfo;
import judge.remote.provider.csu.CSUInfo;
import judge.remote.provider.fzu.FZUInfo;
import judge.remote.provider.hdu.HDUInfo;
import judge.remote.provider.hust.HUSTInfo;
import judge.remote.provider.hysbz.HYSBZInfo;
import judge.remote.provider.jsk.JSKInfo;
import judge.remote.provider.lightoj.LightOJInfo;
import judge.remote.provider.local.LOCALInfo;
import judge.remote.provider.mxt.MXTInfo;
import judge.remote.provider.nbut.NBUTInfo;
import judge.remote.provider.poj.POJInfo;
import judge.remote.provider.scu.SCUInfo;
import judge.remote.provider.sgu.SGUInfo;
import judge.remote.provider.spoj.SPOJInfo;
import judge.remote.provider.tkoj.TKOJInfo;
import judge.remote.provider.tyvj.TyvjInfo;
import judge.remote.provider.uestc.UESTCInfo;
import judge.remote.provider.uestc_old.UESTCOldInfo;
import judge.remote.provider.ural.URALInfo;
import judge.remote.provider.uva.UVAInfo;
import judge.remote.provider.uvalive.UVALiveInfo;
import judge.remote.provider.zoj.ZOJInfo;
import judge.remote.provider.ztrening.ZTreningInfo;
import judge.service.IBaseService;
import judge.service.JudgeService;

import judge.tool.ApplicationContainer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.struts2.interceptor.ParameterAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 用于公共用途
 * @author Isun
 *
 */
public class BaseAction extends ActionSupport implements ParameterAware {
    private static final long serialVersionUID = 1L;

    protected long limitViewSource = Long.parseLong((String) ApplicationContainer.serveletContext.getAttribute("limit.viewsource"));

    protected Map<String, String[]> paraMap;
    protected Object json;

    protected IBaseService baseService;
    protected JudgeService judgeService;

    static public List<RemoteOjInfo> OJList = new ArrayList<>();
    static public List<String> OJListLiteral = new ArrayList<>();
    static {
        OJList.add(POJInfo.INFO);
//        OJList.add(ZOJInfo.INFO);
//        OJList.add(UVALiveInfo.INFO);
//        OJList.add(SGUInfo.INFO);
//        OJList.add(URALInfo.INFO);
//        OJList.add(HUSTInfo.INFO);
//        OJList.add(SPOJInfo.INFO);
//        OJList.add(TyvjInfo.INFO);
        OJList.add(HDUInfo.INFO);
//        OJList.add(HYSBZInfo.INFO);
//        OJList.add(UVAInfo.INFO);
//        OJList.add(CodeForcesInfo.INFO);
//        OJList.add(CodeForcesGymInfo.INFO);
//        OJList.add(ZTreningInfo.INFO);
//        OJList.add(AizuInfo.INFO);
//        OJList.add(LightOJInfo.INFO);
//        OJList.add(UESTCOldInfo.INFO);
//        OJList.add(UESTCInfo.INFO);
        OJList.add(NBUTInfo.INFO);
//        OJList.add(FZUInfo.INFO);
//        OJList.add(CSUInfo.INFO);
        OJList.add(SCUInfo.INFO);
//        OJList.add(ACdreamInfo.INFO);
        OJList.add(LOCALInfo.INFO);
        OJList.add(JSKInfo.INFO);
        OJList.add(MXTInfo.INFO);
        OJList.add(TKOJInfo.INFO);
        Collections.sort(OJList, new Comparator<RemoteOjInfo>() {
            @Override
            public int compare(RemoteOjInfo oj1, RemoteOjInfo oj2) {
                return oj1.literal.compareTo(oj2.literal);
            }
        });
        
        for (RemoteOjInfo oj : OJList) {
            OJListLiteral.add(oj.toString());
        }
    }

    static public Map<String, String> lf = new HashMap<>();
    static {
        for (RemoteOjInfo oj : OJList) {
            lf.put(oj.toString(), oj._64IntIoFormat);
        }
    }
    
    @Override
    public void setParameters(Map<String, String[]> parameters) {
        this.paraMap= parameters;
    }
    
    protected String getParameter(String name) {
        String[] _value = paraMap.get(name);
        return ArrayUtils.isEmpty(_value) ? null : _value[0];
    }

    public List getOJList() {
        return OJList;
    }
    public IBaseService getBaseService() {
        return baseService;
    }
    public void setBaseService(IBaseService baseService) {
        this.baseService = baseService;
    }
    public JudgeService getJudgeService() {
        return judgeService;
    }
    public void setJudgeService(JudgeService judgeService) {
        this.judgeService = judgeService;
    }
    public Object getJson() {
        return json;
    }
    public void setJson(Object json) {
        this.json = json;
    }
}
