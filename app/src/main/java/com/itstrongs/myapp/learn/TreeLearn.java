package com.itstrongs.myapp.learn;

import java.util.List;

/**
 * Created by itstrongs on 2017/11/30.
 */

public class TreeLearn {

    public static void main(String[] args) {
//        MusicTreeNode musicTree = new MusicTreeNode(0, null, );
//        musicTree
    }

    static class MusicTreeNode {

        private int music;
        private List<Integer> child;
        private int parent;

        public MusicTreeNode(int music, int parent, List<Integer> child) {
            this.music = music;
            this.child = child;
            this.parent = parent;
        }
    }

}
