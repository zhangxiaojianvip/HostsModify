import constants.HostsModifyConstant;
import file.AbstractFileReader;
import file.writer.*;

/**
 * windows系统hosts文件启用和停用某个配置工具
 * 如：
 * 127.0.0.1 www.baidu.com  修改成  #127.0.0.1 www.baidu.com
 * #127.0.0.1 www.baidu.com 修改成  127.0.0.1 www.baidu.com
 *
 * @author 张晓键(472694060 @ qq.com)
 * @date 2020年11月3日
 */
public class Main {

    public static void main(String[] args) {
        AbstractFileReader abstractFileReader = new LineReplaceWriter();
        //根据命令行参数来判断是使用enable_data还是disable_data配置文件
        if(args != null && args.length > 0) {
            String enableOrDisable = args[0];
            abstractFileReader.getContextMap().put(HostsModifyConstant.ENABLE_OR_DISABLE, enableOrDisable);
        }
        abstractFileReader.execute();
    }
}
