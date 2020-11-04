package file.reader;

import constants.HostsModifyConstant;
import exception.ContinueException;
import file.AbstractFileReader;
import support.FilePathSupport;
import support.ReplaceItemCompareSupport;
import support.StringSupport;

import java.io.CharArrayWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 替换项读取器
 *
 * @author 张晓键 (472694060@qq.com)
 * @since 2020年11月03日
 */
public class ReplaceItemReader extends AbstractFileReader {

    /**
     * 需要替换总个数
     */
    private Integer totalCount;

    @Override
    protected void preData(Map<String, Object> contextMap) {
        String enableOrDisable = (String) contextMap.get(HostsModifyConstant.ENABLE_OR_DISABLE);

        AbstractFileReader fileReader = new TotalCountReader();
        fileReader.getContextMap().put(HostsModifyConstant.ENABLE_OR_DISABLE, enableOrDisable);
        fileReader.execute();
        Object totalObj = fileReader.getContextMap().get(HostsModifyConstant.REPLACE_ITEM_TOTAL_KEY);
        if(totalObj == null || Integer.parseInt(totalObj.toString()) == 0) {
            throw new RuntimeException("总数total不能为空");
        }
        totalCount = Integer.parseInt(totalObj.toString());

        this.setDataPath(FilePathSupport.getFilePath(enableOrDisable));

        Map<String, String> replaceItemKeyMap = new HashMap<>(2);
        for(int i = 0; i <= totalCount; i++) {
            replaceItemKeyMap.put(StringSupport.sourceFormat(i), StringSupport.targetFormat(i));
        }
        contextMap.put(HostsModifyConstant.REPLACE_ITEM_KEY, replaceItemKeyMap);
    }

    @Override
    protected void lineDeal(String line, Map<String, Object> contextMap, CharArrayWriter outStream) {
        System.out.println("ReplaceItemReader 当前行内容=" + line);
        Map<String, String> replaceItemSourceMap = new HashMap<>(1);
        Map<String, String> replaceItemTargetMap = new HashMap<>(1);
        for(int i = 0; i <= totalCount; i++) {
            if(ReplaceItemCompareSupport.notEqualsSource(line, i) && ReplaceItemCompareSupport.notEqualsTarget(line, i)) {
                continue;
            }

            if(ReplaceItemCompareSupport.equalsSource(line, i)) {
                String value = this.checkAndGet(line);
                replaceItemSourceMap.put(StringSupport.sourceFormat(i), value);
            }

            if(ReplaceItemCompareSupport.equalsTarget(line, i)) {
                String value = this.checkAndGet(line);
                replaceItemTargetMap.put(StringSupport.targetFormat(i), value);
            }
        }

        contextMap.put(HostsModifyConstant.REPLACE_ITEM_SOURCE_VALUE, replaceItemSourceMap);
        contextMap.put(HostsModifyConstant.REPLACE_ITEM_TARGET_VALUE, replaceItemTargetMap);
    }

    /**
     * 获取替换项的真实值
     *
     * @param line 文件行内容
     * @return 每行真实内容
     */
    private String checkAndGet(String line) {
        String value = StringSupport.replaceItemSubString(line);
        if(HostsModifyConstant.EMPTY_STRING.equals(value.trim())) {
            throw new ContinueException();
        }
        return value;
    }
}
