package cn.geny.health.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/7 11:57
 */
public class CaptchaUtil {

    private final static ArrayList<Character> DEFAULT_CODE_GROUPS = new ArrayList<>();
    private final static int DEFAULT_CODE_LENGTH = 6;

    static {
        for (int singleCode = 'A'; singleCode <= 'Z'; singleCode++) {
            DEFAULT_CODE_GROUPS.add((char) singleCode);
        }
        for (int singleCode = '0'; singleCode <= '9'; singleCode++) {
            DEFAULT_CODE_GROUPS.add((char) singleCode);
        }
    }

    public static String generateCaptchaCode() {
        return generateCaptchaCode(DEFAULT_CODE_LENGTH);
    }

    public static String generateCaptchaCode(int codeLength) {
        return generateCaptchaCode(codeLength, DEFAULT_CODE_GROUPS);
    }

    public static String generateCaptchaCode(int codeLength, List<Character> codeGroups) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < codeLength; index++) {
            int randomIndex = random.nextInt(codeGroups.size());
            char randomChar = DEFAULT_CODE_GROUPS.get(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

}