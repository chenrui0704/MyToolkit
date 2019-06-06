package pers.cr.toolkit.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {

    /**
     * 信息: 删除文件
     * 创建时间: 2018年6月4日 14:24:06
     *
     * @param files 要删除的文件
     * @author Cr
     * @since 1.8
     */
    public static void deleteFileToFilesArray(File[] files) {
        for (File file : files) file.delete();
    }

    /**
     * 删除文件
     * 编写时间  2018年7月13日 09:49:53
     * @param list 要删除的文件list
     *
     * @author Cr
     * @since 1.8
     */
    public static void deleteFileToFilesList(List<File> list) {
        for (File file : list) file.delete();
    }

    /**
     * 添加除了指定文件名称的文件
     * 编写时间  2018年7月13日 09:55:26
     * @param list
     *
     * @author Cr
     * @since 1.8
     */
    public static List<File> addNotFileNameFileList(List<File> list, String fileName) {
        List<File> fileList = new ArrayList<>();
        for (File x : list) {
            if (!StringUtil.getFileName(x.getName()).contains(fileName)) {
                fileList.add(x);
            }
        }
        return fileList;
    }


    /**
     * 根据传来的文件夹路径，遍历文件夹下所有文件
     *
     * @param directoryPath 要遍历的路径
     * @return list
     * 编写时间  2018年6月4日 14:24:06
     * @author Cr
     * @since 1.8
     */
    public static List<String> getAllFile(String directoryPath) {
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                list.addAll(getAllFile(file.getAbsolutePath()));
            } else {
                list.add(file.getAbsolutePath());
            }
        }
        return list;
    }

    /**
     * 根据传来的文件夹路径，遍历文件夹下所有文件
     *
     * @param directoryPath 要遍历的路径
     * @return list
     * 编写时间  2018年6月4日 14:24:06
     * @author Cr
     * @since 1.8
     */
    public static List<File> getAllFileToFile(String directoryPath) {
        List<File> list = new ArrayList<>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                list.addAll(getAllFileToFile(file.getAbsolutePath()));
            } else {
                list.add(file);
            }
        }
        return list;
    }

    /**
     * 根据传来的文件夹路径和要遍历的文件格式，遍历文件夹下所有文件返回指定格式的文件
     *
     * @param directoryPath 要遍历的路径
     * @param format        要遍历那些文件格式的文件
     * @return list
     * 编写时间  2018年6月4日 14:24:06
     * @author Cr
     * @since 1.8
     */
    public static List<File> getAllFormatFile(String directoryPath, String format) {
        List<File> list = new ArrayList<>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                list.addAll(getAllFormatFile(file.getAbsolutePath(), format));
            } else {
                if (StringUtil.getFileFormat(file).equals(format)) {//是excel文件就是加进去
                    list.add(file);
                }
            }
        }
        return list;
    }


    /**
     * 方法作用: 根据传来文件获取文件的内容并将内容按行输出成list
     *
     * @param file 目标file
     * @return list  数据
     * 编写时间  2018年6月7日 09:56:13
     * @author Cr
     * @since 1.8
     */
    public static List<String> getContent(File file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 方法作用: 根据传来文件获取文件的内容并将内容按行输出成list
     *
     * @param file 目标file
     * @return list  数据
     * 编写时间  2018年9月6日 21:58:11
     * @author Cr
     * @since 1.8
     */
    public static String getContentToString(File file) {
        String content = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            return readLines(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }


    public static String getContentByFilePath(String path) {
        String content = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(FileUtil.class.getResourceAsStream(path), "UTF-8"));
            return readLines(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 方法作用: 根据传来的inputStream，获取对应的内容
     *
     * @param inputStream 目标io
     * @return 数据内容
     * 编写时间  2018年9月6日 21:58:11
     * @author Cr
     * @since 1.8
     */
    public static String getContentByInputStream(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            return readLines(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String readLines(BufferedReader reader) {
        String content = "";
        try {
            String line = "";
            while ((line = reader.readLine()) != null) {
                content += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return content;
    }


    /**
     * 方法作用: 获取当前文件夹路径下的指定文件格式的文件并将名称添加到集合中
     *
     * @param directoryPath 文件夹路径
     * @param fileFormat    指定文件格式
     * @return list  数据
     * 编写时间  2018年6月7日 09:56:13
     * @author Cr
     * @since 1.8
     */
    public static List<String> getFileNameToDirectoryPath(String directoryPath, String fileFormat) {
        List<String> list = new ArrayList<>();
        File[] fileNode = new File(directoryPath + "\\").listFiles();
        for (File node : fileNode) {
            if (StringUtil.getFileFormat(node).equals(fileFormat)) {
                list.add(StringUtil.getFileName(node.getName()));
            }
        }
        return list;
    }

    /**
     * 方法作用: 根据传过来的文件和文件内容生成对应的文件
     *
     * @param file    文件
     * @param content 文件内容
     *                编写时间  2018年6月7日 16:54:51
     * @author Cr
     * @since 1.8
     */
    public static void generateFile(File file, String content) {
        try (OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "utf-8")) {
            oStreamWriter.append(content);
            oStreamWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param file   文件流，用来生成临时文件
     * @param path   生成路径
     * @param format 生成格式
     * @return map
     * 编写时间  2018年6月5日 10:06:04
     * @author Cr
     * @since 1.8
     */
    public static File generateTemporary(MultipartFile file, String path, String format) {
        File temporary = new File(path.replaceAll(" ", "") + "temporary." + format);
        try {
            file.transferTo(temporary);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temporary;
    }

    /**
     * @param file     文件流，用来生成临时文件
     * @param pathName 生成路径
     * @return map
     * 编写时间  2018年6月5日 10:06:04
     * @author Cr
     * @since 1.8
     */
    public static File generateFileByMultipartFile(MultipartFile file, String pathName) {
        File file2 = new File(pathName);
        try {
            file.transferTo(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file2;
    }


    /**
     * 生成并返回对应的文件名称
     *
     * @param file          上传文件
     * @param path          生成路径
     * @param temporaryPath 临时文件路径
     * @param format        文件格式
     * @return fileName   返回生成文件的名称
     * 编写时间  2018年6月15日 14:55:41
     * @author Cr
     * @since 1.8
     */
    public static String generateFileAndReturnFileName(MultipartFile file, String path, String temporaryPath, String format) {
        String fileName = StringUtil.getFileName(file.getOriginalFilename()).replaceAll(" ", "");
        File folder = new File(path.replaceAll(" ", "") + fileName);
        folder.mkdir();//创建文件夹
        File excel = new File(temporaryPath.replaceAll(" ", "") + "temporary." + format);//临时的excel,读取完数据之后就删除
        try {
            file.transferTo(excel);//转换文件
            excel.renameTo(new File(path.replaceAll(" ", "") + fileName + "/" + fileName + "." + format));//在对应的文件夹创建文件
            excel.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 根据文件夹地址获取当前文件夹指定格式的文件，并根据指定转换格式的格式替换文件真实格式
     *
     * @param path          生成路径
     * @param format        文件真实格式
     * @param convertFormat 要转换成什么格式
     * @return map    key = 文件名称   value = 文件路径
     * 编写时间  2018年6月20日 10:47:28
     * @author Cr
     * @since 1.8
     */
    public static Map<String, String> getAllFileNameAndFilePath(String path, String format, String convertFormat) {
        List<String> list = getAllFile(path.replaceAll(" ", ""));
        Map<String, String> map = new HashMap<>();
        for (String x : list) {
            x = x.replace("." + format, "." + convertFormat);//获取的是模版文件，将模版文件的后缀改成.xml就可以了
            map.put(x.substring(x.lastIndexOf("\\") + 1, x.lastIndexOf(".")), x);//key = 文件名， value = 文件路径

        }
        return map;
    }


}
