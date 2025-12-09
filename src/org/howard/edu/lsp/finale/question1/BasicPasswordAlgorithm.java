package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/** Generates digit-only passwords using Random. */
public class BasicPasswordAlgorithm implements PasswordAlgorithm {
    public String generate(int length) {
        String d = "0123456789"; Random r = new Random(); StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) sb.append(d.charAt(r.nextInt(d.length())));
        return sb.toString();
    }
}