package judge.remote.provider.hust;

import java.util.HashMap;
import java.util.LinkedHashMap;

import judge.remote.RemoteOjInfo;
import judge.remote.language.LanguageFinder;
import judge.tool.Handler;

import org.springframework.stereotype.Component;

@Component
public class HUSTLanguageFinder implements LanguageFinder {

    @Override
    public RemoteOjInfo getOjInfo() {
        return HUSTInfo.INFO;
    }

    @Override
    public boolean isDiverse() {
        return false;
    }

    @Override
    public void getLanguages(String remoteProblemId, Handler<LinkedHashMap<String, String>> handler) {
        // TODO Auto-generated method stub
    }

    @Override
    public LinkedHashMap<String, String> getDefaultLanguages() {
        LinkedHashMap<String, String> languageList = new LinkedHashMap<String, String>();
        languageList.put("0", "C");
        languageList.put("1", "C++");
        languageList.put("2", "Pascal");
        languageList.put("3", "Java");
        return languageList;
    }

    @Override
    public HashMap<String, String> getLanguagesAdapter() {
        // TODO Auto-generated method stub
        return null;
    }

}
