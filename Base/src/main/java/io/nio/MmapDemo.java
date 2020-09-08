package io.nio;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Description:NIO的内存映射文件示例
 * @Author:BigRedCaps
 */
public class MmapDemo
{
    public static void main (String[] args) throws Exception
    {
        FileChannel inChannel = FileChannel.open(Paths.get("D:/logo.png"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("D:/logo_cp.png"), StandardOpenOption.CREATE,
                StandardOpenOption.READ,StandardOpenOption.WRITE);

        // 内存映射文件操作
        MappedByteBuffer inMapBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        // 读取和写入操作
        byte[] bytes = new byte[inMapBuffer.limit()];
        inMapBuffer.get(bytes);
        outMapBuffer.put(bytes);

        // 关闭channel
        inChannel.close();
        outChannel.close();

    }
}
