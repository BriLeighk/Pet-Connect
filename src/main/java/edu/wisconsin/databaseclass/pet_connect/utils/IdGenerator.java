package edu.wisconsin.databaseclass.pet_connect.utils;

import java.security.SecureRandom;
import java.util.Random;

public class IdGenerator {
    private static final String CHARACTERS = "0123456789abcdef";
    private static final int ID_LENGTH = 8;
    private static final Random RANDOM = new SecureRandom();

    public static String generateRandomId() {
        StringBuilder sb = new StringBuilder(ID_LENGTH);
        for (int i = 0; i < ID_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}