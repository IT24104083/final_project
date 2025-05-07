package com.example.demo1;
import com.example.demo1.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String USERS_FILE = "C:\\Users\\thami\\OneDrive\\Desktop\\final-OOP-project\\project\\src\\main\\webapp\\data\\customers.txt";

    public static synchronized void saveUser(User user) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(USERS_FILE, true))) {
            out.println(user.toFileString());
        }
    }

    public static synchronized List<User> readUsers() throws IOException {
        List<User> users = new ArrayList<>();
        File file = new File(USERS_FILE);

        if (!file.exists()) {
            file.createNewFile();
            return users;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    users.add(User.fromFileString(line));
                }
            }
        }
        return users;
    }

    public static synchronized void updateUsers(List<User> users) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                out.println(user.toFileString());
            }
        }
    }
}