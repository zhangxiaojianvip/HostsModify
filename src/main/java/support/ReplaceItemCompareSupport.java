package support;

/**
 * 替换项对比辅助类
 *
 * @author 张晓键 (472649060@qq.com)
 * @since 2020年11月04日
 */
public class ReplaceItemCompareSupport {
    /**
     * 替换项源匹配
     *
     * @param line  文件行内容
     * @param index 索引号
     * @return 是否匹配 true-配置 false-不匹配
     */
    public static boolean equalsSource(String line, int index) {
        return line.contains(StringSupport.sourceFormat(index));
    }

    /**
     * 替换项目标匹配
     *
     * @param line  文件行内容
     * @param index 索引号
     * @return 是否匹配 true-配置 false-不匹配
     */
    public static boolean equalsTarget(String line, int index) {
        return line.contains(StringSupport.targetFormat(index));
    }

    /**
     * 替换项源不匹配
     *
     * @param line  文件行内容
     * @param index 索引号
     * @return 是否匹配 true-不匹配 false-匹配
     */
    public static boolean notEqualsSource(String line, int index) {
        return !equalsSource(line, index);
    }

    /**
     * 替换项目标不匹配
     *
     * @param line  文件行内容
     * @param index 索引号
     * @return 是否匹配 true-不匹配 false-匹配
     */
    public static boolean notEqualsTarget(String line, int index) {
        return !equalsTarget(line, index);
    }
}
