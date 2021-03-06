package support;

import constants.HostsModifyConstant;

import java.io.File;

/**
 * 文件路径生成辅助类
 *
 * @author 张晓键 (472694060@qq.com)
 * @since 2020年11月04日
 */
public final class FilePathSupport {
    private FilePathSupport(){}
    /**
     * 获取替换项文件路径
     *
     * @param enableOrDisable 启用或停用
     * @return 文件路径
     */
    public static String getFilePath(String enableOrDisable) {
        String fileName = HostsModifyConstant.EMPTY_STRING;
        if(enableOrDisable != null && !HostsModifyConstant.EMPTY_STRING.equals(enableOrDisable.trim())) {
            fileName = enableOrDisable;
        }
        String contextPath = new File(HostsModifyConstant.EMPTY_STRING).getAbsolutePath();
        return contextPath + File.separator + fileName ;
    }
}
