package coffeemachine.coffeemachine.user;

import coffeemachine.coffeemachine.handler.ResponseIdentifier;

public class User {

    private Long authorId;
    private ResponseIdentifier lastResponseIdentifier;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public ResponseIdentifier getLastResponseIdentifier() {
        return lastResponseIdentifier;
    }

    public void setLastResponseIdentifier(ResponseIdentifier lastResponseIdentifier) {
        this.lastResponseIdentifier = lastResponseIdentifier;
    }
}
