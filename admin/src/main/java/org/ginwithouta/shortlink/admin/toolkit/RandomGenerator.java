package org.ginwithouta.shortlink.admin.toolkit;

import java.security.SecureRandom;

/**
 * @Package : org.ginwithouta.shortlink.admin.toolkit
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 分组ID随机生成器
 */
public final class RandomGenerator {

    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int CODE_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * 生成随机分组ID
     * @return 分组ID
     */
    public static String generateRandomCode() {
        StringBuilder codeBuilder = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }

        return codeBuilder.toString();
    }
}
