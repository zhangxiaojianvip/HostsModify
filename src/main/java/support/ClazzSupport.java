package support;

/**
 * 类处理辅助类
 *
 * @author 张晓键 (xiaojian.zhang@ucarinc.com)
 * @since 2020年11月04日
 */
public class ClazzSupport {
    /**
     * 类转换
     *
     * @param obj 源对象
     * @param <T> 目标对象
     * @return 转换结果
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }
}
