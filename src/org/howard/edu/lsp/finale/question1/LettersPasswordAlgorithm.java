package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/** Generates letter-only passwords using Random. */
public class LettersPasswordAlgorithm implements PasswordAlgorithm {
    public String generate(int length) {
        String l = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random r = new Random(); StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) sb.append(l.charAt(r.nextInt(l.length())));
        return sb.toString();
    }
}