package file.writer;

import constants.HostsModifyConstant;
import exception.ContinueException;
import file.AbstractFileReader;
import file.reader.*;
import support.ClazzSupport;
import support.SystemSupport;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件内容转换器
 *
 * @author 张晓键 (472694060@qq.com)
 * @since 2020年11月03日
 */
public class LineReplaceWriter extends AbstractFileReader {

    private Map<String, Object> replaceItemSourceMap = new HashMap<>();
    private Map<String, Object> replaceItemTargetMap = new HashMap<>();
    private Map<String, Object> replaceItemKeyMap = new HashMap<>();


    @Override
    protected void preData(Map<String, Object> contextMap) {
        if(SystemSupport.isWindows()) {
            this.setDataPath(HostsModifyConstant.WINDOWS_HOST_FILE_PATH);
        } else if(SystemSupport.isLinux()) {
            this.setDataPath(HostsModifyConstant.LINUX_HOST_FILE_PATH);
        }
        this.reWriteFile();

        //获取替换项
        AbstractFileReader abstractFileReader = new ReplaceItemReader();
        abstractFileReader.getContextMap().put(HostsModifyConstant.ENABLE_OR_DISABLE, contextMap.get(HostsModifyConstant.ENABLE_OR_DISABLE));
        abstractFileReader.execute();
        replaceItemSourceMap = ClazzSupport.cast(abstractFileReader.getContextMap().get(HostsModifyConstant.REPLACE_ITEM_SOURCE_VALUE));
        replaceItemTargetMap = ClazzSupport.cast(abstractFileReader.getContextMap().get(HostsModifyConstant.REPLACE_ITEM_TARGET_VALUE));
        replaceItemKeyMap = ClazzSupport.cast(abstractFileReader.getContextMap().get(HostsModifyConstant.REPLACE_ITEM_KEY));
    }

    @Override
    protected void lineDeal(String line, Map<String, Object> contextMap, CharArrayWriter outStream) {
        replaceItemSourceMap.forEach((key, value) -> {
            if(line.contains(key)) {
                String targetKey = (String) replaceItemKeyMap.get(key);
                try {
                    outStream.write((String) replaceItemTargetMap.get(targetKey));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                outStream.append(HostsModifyConstant.LINE_SEPARATOR);
                throw new ContinueException();
            }
        });
        try {
            outStream.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        outStream.append(HostsModifyConstant.LINE_SEPARATOR);
    }
}
