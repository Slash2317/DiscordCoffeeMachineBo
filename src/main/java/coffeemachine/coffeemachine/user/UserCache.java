package coffeemachine.coffeemachine.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCache {

    private List<Long> authorIds = new ArrayList<>();
    private Map<Long, User> authorIdToUser = new HashMap<>();

    public User createUserIfNeeded(Long authorId) {
        if (authorIds.contains(authorId)) {
            authorIds.remove(authorId);
            authorIds.add(authorId);
            return authorIdToUser.get(authorId);
        }
        User user = new User();
        user.setAuthorId(authorId);
        while (authorIds.size() >= 100) {
            authorIdToUser.remove(authorIds.get(0));
            authorIds.remove(0);
        }
        authorIds.add(authorId);
        authorIdToUser.put(authorId, user);
        return user;
    }

    public User getUser(Long authorId) {
        return authorIdToUser.get(authorId);
    }
}
