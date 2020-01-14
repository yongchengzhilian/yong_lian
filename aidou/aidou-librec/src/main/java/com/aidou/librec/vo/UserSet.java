package com.aidou.librec.vo;

import java.util.ArrayList;
import java.util.List;

public class UserSet {
	
	public List<User> users = new ArrayList<User>();
	 
    public UserSet() {
    }
 
    public User put(String username) {
        return new User(username);
    }
 
 
    public User getUser(int position) {
        return users.get(position);
    }
 
    public User getUser(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }
 
 
    public final class User {
        public String username;
        public List<Set> list = new ArrayList<Set>();
 
        private User(String username) {
            this.username = username;
        }
 
        public User set(String username, double score) {
            this.list.add(new Set(username, score));
            return this;
        }
 
        public void create() {
            users.add(this);
        }
 
        public Set find(String username) {
            for (Set set : list) {
                if (set.username.equals(username)) {
                    return set;
                }
            }
            return null;
        }
 
        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    '}';
        }
    }


}
