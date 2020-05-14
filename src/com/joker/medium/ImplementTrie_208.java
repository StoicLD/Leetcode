package com.joker.medium;

import java.util.HashMap;

public class ImplementTrie_208
{
}

/**
 * 第一种解法
 */
class Trie {
    Character c;
    boolean isEnd;
    HashMap<Character, Trie> map;
    /** Initialize your data structure here. */
    public Trie() {
        map = new HashMap<>();
    }

    public Trie(char c, boolean isEnd)
    {
        this();
        this.c = c;
        this.isEnd = isEnd;
    }

    /** Inserts a word into the trie. */
    public void insert(String word)
    {
        Trie next = this;
        for(int i = 0; i < word.length(); i++)
        {
            if(next.map.get(word.charAt(i)) == null)
                next.map.put(word.charAt(i), new Trie(word.charAt(i), i == word.length() - 1));
            next.map.get(word.charAt(i)).isEnd |= (i == word.length() - 1);
            next = next.map.get(word.charAt(i));
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie next = this;
        for(int i = 0; i < word.length(); i++)
        {
            if(next.map.get(word.charAt(i)) == null)
                return false;
            next = next.map.get(word.charAt(i));
        }
        return next.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie next = this;
        for(int i = 0; i < prefix.length(); i++)
        {
            if(next.map.get(prefix.charAt(i)) == null)
                return false;
            next = next.map.get(prefix.charAt(i));
        }
        return next != null;
    }
}

/**
 * 第二种解法
 */
class TrieNode {

    // R links to node children
    private TrieNode[] links;

    private final int R = 26;

    private boolean isEnd;

    public TrieNode() {
        links = new TrieNode[R];
    }

    public boolean containsKey(char ch) {
        return links[ch -'a'] != null;
    }
    public TrieNode get(char ch) {
        return links[ch -'a'];
    }
    public void put(char ch, TrieNode node) {
        links[ch -'a'] = node;
    }
    public void setEnd() {
        isEnd = true;
    }
    public boolean isEnd() {
        return isEnd;
    }
}
class Trie2 {
    private TrieNode root;

    public Trie2() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }

    // search a prefix or whole key in trie and
    // returns the node where search ends
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.containsKey(curLetter)) {
                node = node.get(curLetter);
            } else {
                return null;
            }
        }
        return node;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
}