package file.reader;

import constants.HostsModifyConstant;
import exception.BreakException;
import exception.ContinueException;
import file.AbstractFileReader;
import support.FilePathSupport;
import support.StringSupport;

import java.io.CharArrayWriter;
import java.util.Map;

/**
 * 需要替换项的总数量去取器
 *
 * @author 张晓键 (472694060@qq.com)
 * @since 2020年11月03日
 */
public class TotalCountReader extends AbstractFileReader {


    @Override
    protected void preData(Map<String, Object> contextMap) {
        String enableOrDisable = (String) contextMap.get(HostsModifyConstant.ENABLE_OR_DISABLE);
        this.setDataPath(FilePathSupport.getFilePath(enableOrDisable));
    }

    @Override
    protected void lineDeal(String line, Map<String, Object> contextMap, CharArrayWriter outStream) {
        if(line.contains(HostsModifyConstant.REPLACE_ITEM_TOTAL_KEY)) {
            String totalNumStr = StringSupport.totalSubString(line);
            if(totalNumStr.trim().equals(HostsModifyConstant.EMPTY_STRING)) {
                throw new ContinueException();
            }
            contextMap.put(HostsModifyConstant.REPLACE_ITEM_TOTAL_KEY, totalNumStr.trim());
            throw new BreakException();
        }
    }
}