package support;

import constants.HostsModifyConstant;

/**
 * 操作辅助类
 *
 * @author 张晓键 (xiaojian.zhang@ucarinc.com)
 * @since 2020年11月04日
 */
public class SystemSupport {

    /**
     * 是否windows系统
     *
     * @return true-是 false-否
     */
    public static Boolean isWindows() {
        if(HostsModifyConstant.OS_NAME.toLowerCase().startsWith(HostsModifyConstant.WINDOWS_START)) {
            return true;
        }
        return false;
    }

    /**
     * 是否linux系统
     *
     * @return true-是 false-否
     */
    public static Boolean isLinux() {
        if(HostsModifyConstant.OS_NAME.toLowerCase().contains(HostsModifyConstant.LINUX_START)) {
            return true;
        }
        return false;
    }
}
