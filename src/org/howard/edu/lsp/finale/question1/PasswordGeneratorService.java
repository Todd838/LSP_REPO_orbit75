package org.howard.edu.lsp.finale.question1;

import java.util.*;

/**
 * Singleton service for generating passwords using swappable algorithms.
 * @author Todd
 */
public class PasswordGeneratorService {
    /* DESIGN PATTERNS: Singleton (single instance via getInstance()) 
     * and Strategy (swappable algorithms via PasswordAlgorithm interface).
     * WHY: Singleton provides single shared access point (Req #5).
     * Strategy supports multiple approaches (Req #1), runtime selection (Req #2),
     * future expansion (Req #3), and swappable behavior (Req #4). */
    private static final PasswordGeneratorService INSTANCE = new PasswordGeneratorService();
    private final Map<String, PasswordAlgorithm> algs = new HashMap<>();
    private PasswordAlgorithm current;
    
    private PasswordGeneratorService() {
        algs.put("basic", new BasicPasswordAlgorithm());
        algs.put("enhanced", new EnhancedPasswordAlgorithm());
        algs.put("letters", new LettersPasswordAlgorithm());
    }
    
    /** Returns singleton instance. */
    public static PasswordGeneratorService getInstance() { return INSTANCE; }
    
    /** Sets algorithm by name. */
    public void setAlgorithm(String name) { current = algs.get(name); }
    
    /** Generates password of specified length. */
    public String generatePassword(int length) {
        if (current == null) throw new IllegalStateException("No algorithm selected");
        return current.generate(length);
    }
}
    
    // Singleton instance (thread-safe initialization)
    private static final PasswordGeneratorService INSTANCE = new PasswordGeneratorService();
    
    // Current selected algorithm
    private PasswordAlgorithm currentAlgorithm;
    
    // Registry of available algorithms
    private final Map<String, PasswordAlgorithm> algorithms;
    
    /**
     * Private constructor to prevent instantiation.
     * Initializes the algorithm registry with supported algorithms.
     */
    private PasswordGeneratorService() {
        algorithms = new HashMap<>();
        algorithms.put("basic", new BasicPasswordAlgorithm());
        algorithms.put("enhanced", new EnhancedPasswordAlgorithm());
        algorithms.put("letters", new LettersPasswordAlgorithm());
        currentAlgorithm = null;
    }
    
    /**
     * Returns the singleton instance of PasswordGeneratorService.
     * 
     * @return the single instance of this service
     */
    public static PasswordGeneratorService getInstance() {
        return INSTANCE;
    }
    
    /**
     * Sets the password generation algorithm to use.
     * 
     * @param name the name of the algorithm ("basic", "enhanced", or "letters")
     * @throws IllegalArgumentException if the algorithm name is not recognized
     */
    public void setAlgorithm(String name) {
        if (!algorithms.containsKey(name)) {
            throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
        currentAlgorithm = algorithms.get(name);
    }
    
    /**
     * Generates a password of the specified length using the currently selected algorithm.
     * 
     * @param length the desired length of the password
     * @return a randomly generated password string
     * @throws IllegalStateException if no algorithm has been set
     * @throws IllegalArgumentException if length is less than 1
     */
    public String generatePassword(int length) {
        if (currentAlgorithm == null) {
            throw new IllegalStateException("No algorithm selected. Call setAlgorithm() first.");
        }
        if (length < 1) {
            throw new IllegalArgumentException("Password length must be at least 1");
        }
        return currentAlgorithm.generate(length);
    }
}