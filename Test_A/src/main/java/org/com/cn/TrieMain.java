package org.com.cn;

import java.util.List;

public class TrieMain {

    public static void main(String[] args){
        test();
    }

    public static void test1(){
        Trie1 trie=new Trie1();

        trie.addWord("ibiyzbi");

        System.out.println("----------------------------------------");
        List<String> words=trie.searchWord("bi");
        for(String s: words){
            System.out.println(s);
        }
    }

    public static void test(){
        Trie1 trie=new Trie1();
        trie.addWord("abi");
        trie.addWord("ai");
        trie.addWord("aqi");
        trie.addWord("biiiyou");
        trie.addWord("dqdi");
        trie.addWord("ji");
        trie.addWord("li");
        trie.addWord("liqing");
        trie.addWord("liqq");
        trie.addWord("liqqq");
        trie.addWord("qi");
        trie.addWord("qibi");
        trie.addWord("i");
        trie.addWord("ibiyzbi");
        List<String> list=trie.prefixSearchWord("li");
        for(String s: list){
            System.out.println(s);
        }
        System.out.println("----------------------------------------");
        List<String> li=trie.searchWord("i");
        for(String s: li){
            System.out.println(s);
        }
        System.out.println("----------------------------------------");
        List<String> words=trie.searchWord("bi");
        for(String s: words){
            System.out.println(s);
        }

        System.out.println("----------------------------------------");
        List<String> lst=trie.searchWord("q");
        for(String s: lst){
            System.out.println(s);
        }
    }
}