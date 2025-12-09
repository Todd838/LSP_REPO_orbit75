package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/** Generates alphanumeric passwords using SecureRandom. */
public class EnhancedPasswordAlgorithm implements PasswordAlgorithm {
    public String generate(int length) {
        String c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom s = new SecureRandom(); StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) sb.append(c.charAt(s.nextInt(c.length())));
        return sb.toString();
    }
}