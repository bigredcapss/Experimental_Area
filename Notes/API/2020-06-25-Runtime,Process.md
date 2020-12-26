## Runtime,Process

#### 一言以概之：

> java.lang.Runtime----->java.lang.Process------>java.lang.ClassLoader
>
> Runtime:JVM进程类，一个JVM进程对应一个Runtime实例；
>
> Process:本机进程类，通过该实例可以控制进程获得相关信息；
>
> ClassLoader:类加载器，加载.class文件（字节数组），返回对应的java.lang.Class对象.

###### API:

```Java
addShutdownHook(Thread hook)     注册新的虚拟机来关闭挂钩。 

availableProcessors()     向 Java 虚拟机返回可用处理器的数目。   

exec(String command)     在单独的进程中执行指定的字符串命令。   

exec(String[] cmdarray)     在单独的进程中执行指定命令和变量。   

exec(String[] cmdarray, String[] envp)     在指定环境的独立进程中执行指定命令和变量。   

exec(String[] cmdarray, String[] envp, File dir)     在指定环境和工作目录的独立进程中执行指定的命令和变量。   

exec(String command, String[] envp)     在指定环境的单独进程中执行指定的字符串命令。   

exec(String command, String[] envp, File dir)     在有指定环境和工作目录的独立进程中执行指定的字符串命令。   

exit(int status)     通过启动虚拟机的关闭序列，终止当前正在运行的 Java 虚拟机。   

freeMemory()     返回 Java 虚拟机中的空闲内存量。   

gc()     运行垃圾回收器。  

InputStream getLocalizedInputStream(InputStream in)     已过时。 从 JDK 1.1 开始，将本地编码字节流转换为 Unicode 字符流的首选方法是使用 InputStreamReader 和 BufferedReader 类。   

OutputStream getLocalizedOutputStream(OutputStream out)     已过时。 从 JDK 1.1 开始，将 Unicode 字符流转换为本地编码字节流的首选方法是使用 OutputStreamWriter、BufferedWriter 和 PrintWriter 类。   

getRuntime()     返回与当前 Java 应用程序相关的运行时对象。 

halt(int status)     强行终止目前正在运行的 Java 虚拟机。 

load(String filename)     加载作为动态库的指定文件名。  

loadLibrary(String libname)     加载具有指定库名的动态库。   

maxMemory()     返回 Java 虚拟机试图使用的最大内存量。   

removeShutdownHook(Thread hook)     取消注册某个先前已注册的虚拟机关闭挂钩。  

runFinalization()     运行挂起 finalization 的所有对象的终止方法。 

runFinalizersOnExit(value)     已过时。 此方法本身具有不安全性。它可能对正在使用的对象调用终结方法，而其他线程正在操作这些对象，从而导致不正确的行为或死锁。 

totalMemory()     返回 Java 虚拟机中的内存总量。   

traceInstructions(on)     启用／禁用指令跟踪。   

traceMethodCalls(on)     启用／禁用方法调用跟踪。
```



