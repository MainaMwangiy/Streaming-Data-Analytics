package com.microsoft.example;


import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

public class PositiveTweetBolt implements Serializable {
    public static final long serialVersionUID = 42L;
    private Set<String> postiveWords;
    private static PositiveTweetBolt _singleton;

    private PositiveTweetBolt() {
        this.postiveWords = new HashSet<String>();
        BufferedReader rdln = null;
        try {
            rdln = new BufferedReader(
                    new InputStreamReader(
                            this.getClass().getResourceAsStream("/positive-words.txt")));
            String line;
            while ((line = rdln.readLine()) != null)
                this.postiveWords.add(line);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass())
                    .error("IO error!!", ex);
        } finally {
            try {
                if (rdln != null) rdln.close();
            } catch (IOException ex) {
            }
        }
    }

    private static PositiveTweetBolt get() {
        if (_singleton == null)
            _singleton = new PositiveTweetBolt();
        return _singleton;
    }

    public static Set<String> getWords() {
        return get().postiveWords;
    }
}