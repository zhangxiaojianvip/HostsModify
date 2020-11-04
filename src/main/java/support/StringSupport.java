package support;

import constants.HostsModifyConstant;

/**
 * 字符串辅助类
 *
 * @author 张晓键 (xiaojian.zhang@ucarinc.com)
 * @since 2020年11月04日
 */
public class StringSupport {
    /**
     * 截取total的字符串值，如果长度不够则返回空字符串
     *
     * @param source 需要截取的字符串
     */
    public static String totalSubString(String source) {
        if(source.length() <= HostsModifyConstant.REPLACE_ITEM_TOTAL_KEY.length() + 1) {
            return HostsModifyConstant.EMPTY_STRING;
        }
        return source.substring(HostsModifyConstant.REPLACE_ITEM_TOTAL_KEY.length() + 1);
    }

    /**
     * 截取替换项的字符串值，如果长度不够则返回空字符串
     *
     * @param source 需要截取的字符串
     */
    public static String replaceItemSubString(String source) {
        if(source.length() <= HostsModifyConstant.REPLACE_ITEM_SUBSTRING_LENGTH) {
            return HostsModifyConstant.EMPTY_STRING;
        }
        return source.substring(HostsModifyConstant.REPLACE_ITEM_SUBSTRING_LENGTH).trim();
    }

    /**
     * 替换项源模板格式化
     *
     * @param index 索引号
     * @return 源字符串
     */
    public static String sourceFormat(Integer index) {
        return String.format(HostsModifyConstant.SOURCE, index);
    }

    /**
     * 替换项模板模板格式化
     *
     * @param index 索引号
     * @return 模板字符串
     */
    public static String targetFormat(Integer index) {
        return String.format(HostsModifyConstant.TARGET, index);
    }
}
