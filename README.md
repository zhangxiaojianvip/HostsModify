Windows下的hosts文件内容替换工具
--

使用方法：
--
1、项目打包后生成HostsModify.jar（也可以下载已打包好的文件/out/artifacts/HostModify/HostsModify.jar）

2、编辑enable_data和disable_data文件（样例在代码的/src/main/resources/目录下）

3、保持HostsModify.jar、enable_data、disable_data、enable.bat、disable.bat五个文件处于同一个目录

4、双击enable.bat或disable.bat即可实现切换

<a href="./HostsModify.zip">（使用示例）</a>

文件说明
--
enable_data 配置规则(disable_data同理)

1、total=2

描述：total=2表示有hosts文件里有两行内容需要替换

2、

source[0]=#127.0.0.1 xxx.com

target[0]=127.0.0.1 xxx.com

描述：

source[0]=#127.0.0.1 xxx.com 表示要被替换的第一个值为“#127.0.0.1 xxx.com”

target[0]=127.0.0.1 xxx.com 表示把source[0]的值替换成“127.0.0.1 xxx.com”

注意
--
1、total=2 

“total”和“=”  中间不能有空格

2、source[0]=、target[0]=

“source[0]”和“=” 中间不能有空格

“target[0]”和“=” 中间不能有空格

