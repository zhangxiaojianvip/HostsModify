package constants;

/**
 * 项目常量
 *
 * @author 张晓键 (472694060@qq.com)
 * @since 2020年11月03日
 */
public class HostsModifyConstant {
    /** 要替换的域名映射总数量 */
    public static final String REPLACE_ITEM_TOTAL_KEY = "total";
    /** 本地开发或测试环境标识key */
    public static final String ENVIRONMENT = "environment";
    /** 替换项的源模板 %s 根据具体的数字替换 */
    public static final String SOURCE = "source[%s]=";
    /** 替换项的目标模板 %s 根据具体的数字替换 */
    public static final String TARGET = "target[%s]=";
    /** 替换项的源值集合，使用map存储如：[{"source[0]=","127.0.0.1 xxx.com"},...] */
    public static final String REPLACE_ITEM_SOURCE_VALUE = "replaceItemSourceValue";
    /** 替换项的目标值集合，使用map存储如：[{"target[0]=","#127.0.0.1 xxx.com"},...] */
    public static final String REPLACE_ITEM_TARGET_VALUE = "replaceItemTargetValue";
    /** 替换项的source target映射，如：[{"source[0]=":"target[0]="},...] */
    public static final String REPLACE_ITEM_KEY = "replaceItemKey";

    /** 空字符串 */
    public static final String EMPTY_STRING = "";

    /** 替换项截取字符串长度 */
    public static final Integer REPLACE_ITEM_SUBSTRING_LENGTH = 10;

    /** windows hosts文件位置 */
    public static final String WINDOWS_HOST_FILE_PATH = "C:\\Windows\\System32\\drivers\\etc\\hosts";
    /** linux hosts文件位置 (涉及操作权限问题) */
    public static final String LINUX_HOST_FILE_PATH = "/etc/hosts";

    /** 行分隔符 */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    /** 操作系统名 */
    public static final String OS_NAME = System.getProperties().getProperty("os.name");
    /** linux标识 */
    public static final String LINUX_START = "linux";
    /** windows标识 */
    public static final String WINDOWS_START = "win";
}
