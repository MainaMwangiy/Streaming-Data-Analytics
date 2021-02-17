package com.microsoft.example;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

public class NegativeTweetBolt implements Serializable {
    public static final long serialVersionUID = 42L;
    private Set<String> negativeWords;
    private static NegativeTweetBolt _singleton;

    private NegativeTweetBolt() {
        this.negativeWords = new HashSet<String>();
        BufferedReader rdln = null;
        try {
            rdln = new BufferedReader(
                    new InputStreamReader(
                            this.getClass().getResourceAsStream("/negative-words.txt")));
            String line;
            while ((line = rdln.readLine()) != null)
                this.negativeWords.add(line);
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

    private static NegativeTweetBolt get() {
        if (_singleton == null)
            _singleton = new NegativeTweetBolt();
        return _singleton;
    }

    public static Set<String> getWords() {
        return get().negativeWords;
    }
}