package com.aidou.api.common.cache;

import java.text.MessageFormat;

public enum    RedisKey {
    MESSAGE_FROM_("message_from_@{0}"),
    ACCESS_TOKEN("ACCESS_TOKEN"),
    TOPIC_TEXT_TOKEN("TOPIC_TEXT_TOKEN"),
    TOPIC_TEXT_VISITS("TOPIC_TEXT_VISITS:{0}"),
    TOPIC_COMMENT_VISITS("TOPIC_COMMENT_VISITS:{0}"),
    TOPIC_COMMENT_ID_SET("TOPIC_COMMENT_ID_SET:{0}"),
    TOPIC_TEXT_THUMBUP("TOPIC_TEXT_THUMBUP:{0}"),
    TOPIC_VOTE_THUMBUP("TOPIC_VOTE_THUMBUP:{0}"),
    TOPIC_TEXT_COMMENT("TOPIC_TEXT_COMMENT:{0}"),
    USER_INFO("USER_INFO:{0}");

    private String pattern;
    private String suffix;

    RedisKey(String pattern) {
        this.pattern = pattern;
    }

    public RedisKey suffix(String suffix){
        this.suffix = suffix;
        return this;
    }
    @Override
    public String toString(){
       return MessageFormat.format(this.pattern,suffix);
    }
}
