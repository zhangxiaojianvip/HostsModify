package file;

import constants.HostsModifyConstant;
import exception.BreakException;
import exception.ContinueException;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件读取遍历每行内容抽象类
 *
 * @author 张晓键 (472694060@qq.com)
 * @since 2020年11月03日
 */
public abstract class AbstractFileReader {
    /** 读完是否要修改文件内容 true-修改 false-不修改 */
    private Boolean writeFile = false;
    /** 替换项文件路径 */
    private String dataPath;
    /** 文件读取上下文对象 */
    private Map<String, Object> contextMap = new HashMap<>();

    /**
     * 前置数据赋值
     *
     * @param contextMap 上下文参数
     */
    protected void preData(Map<String, Object> contextMap) {
    }

    /**
     * 每行内容处理逻辑
     *
     * @param line       行内容
     * @param contextMap 处理后数据
     * @param outStream  输出流
     */
    protected void lineDeal(String line, Map<String, Object> contextMap, CharArrayWriter outStream) {
    }

    /**
     * 执行文件读取并根据内容处理
     */
    public final void execute() {
        //前置参数
        preData(contextMap);
        //文件路径处理
        if(dataPath == null || HostsModifyConstant.EMPTY_STRING.equals(dataPath.trim())) {
            throw new RuntimeException("文件路径为空");
        }
        //遍历文件每行内容
        fileCirculation();
    }

    /**
     * 遍历文件每行内容
     */
    private void fileCirculation() {
        File file = new File(dataPath);
        if(!file.exists()) {
            System.out.println(String.format("文件不存在,dataPath=%s", dataPath));
            throw new RuntimeException("文件不存在");
        }
        CharArrayWriter outStream = new CharArrayWriter();
        //读取并根据每行内容进行lineDeal()处理
        try(FileReader fileReader = new FileReader(file); BufferedReader in = new BufferedReader(fileReader)) {
            String line;
            while((line = in.readLine()) != null) {
                if(line == null) {
                    continue;
                }
                line = line.trim();
                if(HostsModifyConstant.EMPTY_STRING.equals(line)) {
                    continue;
                }
                try {
                    lineDeal(line, contextMap, outStream);
                } catch (ContinueException e) {
                    continue;
                } catch (BreakException e) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //重写修改后的文件内容
        try {
            if(writeFile) {
                System.out.println("回写内容：");
                System.out.println(outStream);
                FileWriter out = new FileWriter(file);
                outStream.writeTo(out);
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改标识为需要修改文件
     */
    public void reWriteFile() {
        this.writeFile = true;
    }

    public Map<String, Object> getContextMap() {
        return contextMap;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }
}
