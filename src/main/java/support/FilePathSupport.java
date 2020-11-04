package support;

import constants.HostsModifyConstant;

import java.io.File;

/**
 * 文件路径生成辅助类
 *
 * @author 张晓键 (xiaojian.zhang@ucarinc.com)
 * @since 2020年11月04日
 */
public class FilePathSupport {
    /**
     * 获取替换项文件路径
     *
     * @param enableOrDisable 启用或停用
     * @return 文件路径
     */
    public static String getFilePath(String enableOrDisable) {
        String prefix = HostsModifyConstant.EMPTY_STRING;
        if(enableOrDisable != null && !HostsModifyConstant.EMPTY_STRING.equals(enableOrDisable.trim())) {
            prefix = enableOrDisable;
        }
        String contextPath = new File(HostsModifyConstant.EMPTY_STRING).getAbsolutePath();
        return contextPath + File.separator + prefix + HostsModifyConstant.REPLACE_ITEM_FILE_LAST;
    }
}
