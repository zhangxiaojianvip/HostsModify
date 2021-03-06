package file.reader;

import constants.HostsModifyConstant;
import exception.BreakException;
import exception.ContinueException;
import file.AbstractFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(TotalCountReader.class);

    /**
     * 赋值对应环境的文件路径
     *
     * @param contextMap 上下文参数
     */
    @Override
    protected void preData(Map<String, Object> contextMap) {
        String env = (String) contextMap.get(HostsModifyConstant.ENVIRONMENT);
        this.setDataPath(FilePathSupport.getFilePath(env));
    }

    /**
     * 获取文件中有多少数据需要替换
     *
     * @param line       行内容
     * @param contextMap 处理后数据
     * @param outStream  输出流
     */
    @Override
    protected void lineDeal(String line, Map<String, Object> contextMap, CharArrayWriter outStream) {
        logger.info("TotalCountReader 当前行内容={}", line);
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
