package top.tsview.cli.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class FileUtil {

    /**
     * 读取
     *
     * @param resourcePath 资源地址
     * @return 文本内容
     */
    public static String readTxtByResource(String resourcePath) {
        StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = FileUtil.class.getClassLoader().getResourceAsStream(resourcePath)) {
            assert inputStream != null;
            InputStreamReader reader = new InputStreamReader(inputStream);
            char[] chars = new char[1024];
            int length;
            while ((length = reader.read(chars)) != -1) {
                sb.append(chars, 0, length);
            }
        } catch (IOException ignored) {
        }
        return sb.toString();
    }

    /**
     * 从全路径读取文本
     *
     * @param fullPath 相对路径即项目路径 绝对路径需要确定盘符
     * @return 文本内容
     */
    public static String readTxtByPath(String fullPath) {
        StringBuilder sb = new StringBuilder();
        File file = new File(fullPath);
        try (InputStreamReader reader = new FileReader(file)) {
            char[] chars = new char[1024];
            int length;
            while ((length = reader.read(chars)) != -1) {
                sb.append(chars, 0, length);
            }
        } catch (IOException ignored) {
        }
        return sb.toString();
    }

    /**
     * 读取二进制文件并写入到指定地址
     *
     * @param path       读取文件路径
     * @param targetPath 写入文件名
     */
    public static void readAndWriteBinary(String path, String targetPath) {
        try (InputStream in = Files.newInputStream(Paths.get(path));
             OutputStream out = Files.newOutputStream(Paths.get(targetPath + getSuffix(path)))) {
            byte[] bytes = new byte[1024];
            int length;
            while ((length = in.read(bytes)) != -1) {
                out.write(bytes, 0, length);
            }
        } catch (IOException ignored) {
        }
    }

    public static String getSuffix(String path) {
        return path.substring(path.lastIndexOf("."));
    }
}
