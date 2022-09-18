package com.cs.nlp.service;


import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CoreNlpEvaluator {



    public Map<String,String>  getActionItems(String text) {
        Map<String,String>  actionListMAp=new HashMap<>();
        Properties props = new Properties();


        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation document = new Annotation(text);
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {
            boolean containsHandle=false;
            CoreMap currentSentence=sentence;
            List<String> wordList=new ArrayList<>();
            String mention="";
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                wordList.add(word);
                if("HANDLE".equals(ne)){
                    containsHandle=true;
                    mention=mention.concat(word);
                }
            }
            if(containsHandle) {
                //String processedSentence=sentence.toString().replaceAll(".(?!$)", "$0 ");
                String actualMsg = prepareActionMessageString(mention, sentence.toString(),wordList);
                actionListMAp.put(mention, actualMsg);
            }
        }
        return actionListMAp;
    }


    private String prepareActionMessageString(String word,String sentence,List<String> wordList){
        String  actualSentence[]=sentence.split(" ");
        String actionMsg="";
        for(String msgWord:actualSentence){
            if(!word.equals(msgWord) && wordList.indexOf(word)<wordList.indexOf(msgWord) ){
                actionMsg=actionMsg+msgWord+" ";
            }
        }
        return  actionMsg;
    }
}