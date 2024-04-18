package com.TgBotMOEVM.security;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PKCEUtil {

    private static final String CODE_CHALLENGE_METHOD = "S256";
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateCodeVerifier() {
        byte[] code = new byte[32];
        secureRandom.nextBytes(code);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(code);
    }

    public static String generateCodeChallenge(String codeVerifier) {
        try {
            byte[] bytes = codeVerifier.getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(bytes);
            return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            // Обработка исключения
            e.printStackTrace();
            return null; // или любое другое действие по вашему усмотрению
        }
    }
}
