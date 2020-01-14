package com.aidou.librec.vo;

import lombok.Data;

@Data
public class Set  implements Comparable<Set> {
	public String username;
    public double score;

    public Set(String username, double score) {
        this.username = username;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Set{" +
                "username='" + username + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(Set o) {
        return  score > o.score ? -1 : 1;
    }

   


}
