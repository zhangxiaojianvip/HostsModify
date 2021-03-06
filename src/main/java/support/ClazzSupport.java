package support;

/**
 * 类处理辅助类
 *
 * @author 张晓键 (472694060@qq.com)
 * @since 2020年11月04日
 */
public final class ClazzSupport {
    private ClazzSupport(){}

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
