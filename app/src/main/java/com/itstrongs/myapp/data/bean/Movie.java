package com.itstrongs.myapp.data.bean;

import java.util.List;

/**
 * Created by itstrongs on 2017/10/16.
 */
public class Movie {

    public int count;
    public int start;
    public int total;
    public List<video> subjects;
    public String title;

    public class video {

        public Rate rating;
        public String title;
        public String collect_count;
        public String original_title;
        public String subtype;
        public String year;
        public MovieImage images;

        public class Rate {
            public int max;
            public float average;
            public String stars;
            public int min;
        }

        public class MovieImage {
            public String small;
            public String large;
            public String medium;
        }

    }

}
