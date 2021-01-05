package file.writer;

import constants.HostsModifyConstant;
import exception.ContinueException;
import file.AbstractFileReader;
import file.reader.ReplaceItemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(LineReplaceWriter.class);

    private Map<String, Object> replaceItemSourceMap = new HashMap<>();
    private Map<String, Object> replaceItemTargetMap = new HashMap<>();
    private Map<String, Object> replaceItemKeyMap = new HashMap<>();

    /**
     * 执行替换行数据的前置步骤，先把行替换项数据获取出来
     *
     * @param contextMap 上下文参数
     */
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
        abstractFileReader.getContextMap().put(HostsModifyConstant.ENVIRONMENT, contextMap.get(HostsModifyConstant.ENVIRONMENT));
        abstractFileReader.execute();
        replaceItemSourceMap = ClazzSupport.cast(abstractFileReader.getContextMap().get(HostsModifyConstant.REPLACE_ITEM_SOURCE_VALUE));
        replaceItemTargetMap = ClazzSupport.cast(abstractFileReader.getContextMap().get(HostsModifyConstant.REPLACE_ITEM_TARGET_VALUE));
        replaceItemKeyMap = ClazzSupport.cast(abstractFileReader.getContextMap().get(HostsModifyConstant.REPLACE_ITEM_KEY));
    }

    /**
     * 替换每行数据
     *
     * @param line       行内容
     * @param contextMap 处理后数据
     * @param outStream  输出流
     */
    @Override
    protected void lineDeal(String line, Map<String, Object> contextMap, CharArrayWriter outStream) {
        logger.info("LineReplaceWriter 当前行内容={}", line);
        replaceItemSourceMap.forEach((key, value) -> {
            if(line.contains((String) value)) {
                String targetKey = (String) replaceItemKeyMap.get(key);
                try {
                    logger.info("原值：{}", value);
                    logger.info("替换后：{}", replaceItemTargetMap.get(targetKey));
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
