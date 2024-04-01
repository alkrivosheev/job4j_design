package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, User> previousMap = new HashMap<>();
        for (User user : previous) {
            previousMap.put(user.getId(), user);
        }
        for (User user : current) {
            User fromPrev = previousMap.get(user.getId());
            if (fromPrev == null) {
                added++;
            } else if (!fromPrev.equals(user)) {
                changed++;
            }
        }
        deleted = previous.size() + added - current.size();
        return new Info(added, changed, deleted);
    }
}
