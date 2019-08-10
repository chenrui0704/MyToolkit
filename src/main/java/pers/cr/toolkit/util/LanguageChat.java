package pers.cr.toolkit.util;

import java.util.*;

public class LanguageChat {

    private static Map<String, String> replaceMap = new HashMap<>();

    public static void main(String[] args) {
        initReplaceMap();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            System.out.println(handle(input));
        }

    }

    // 字符处理
    public static String handle(String input) {
        input = specialCharacterHandle(input);
        List<String> handleList = new ArrayList<>();
        for (String key : replaceMap.keySet()) {
            if (input.contains(key) && !handleList.contains(key)) {
                input = input.replaceAll(key, replaceMap.get(key));
                handleList.add(replaceMap.get(key));
            }
        }
        return input;
    }

    // 特殊字符处理
    public static String specialCharacterHandle(String input) {
        return input.replaceAll("\\?", "!")
                .replaceAll("\\？", "");
    }

    // 更替map初始化
    public static void initReplaceMap() {
        replaceMap.put("怎么样", "挺好的");
        replaceMap.put("吗", "");
        replaceMap.put("你", "我");
        replaceMap.put("我", "你");
    }

}
