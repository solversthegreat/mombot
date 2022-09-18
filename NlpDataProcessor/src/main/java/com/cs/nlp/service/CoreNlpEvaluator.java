package com.cs.nlp.service;

import com.cs.nlp.model.ActionItem;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreNLPProtos;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CoreNlpEvaluator {



    public ActionItem getActionItems(String text) {
        Map<String,String>  actionListMAp=new HashMap<>();
        ActionItem actionItem=new ActionItem();
        Properties props = new Properties();
        List<String> neList = new ArrayList<>();

        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation document = new Annotation(text);
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {
            boolean containsHandle=false;
            CoreMap currentSentence=sentence;
            String mention="";
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                if("HANDLE".equals(ne)){
                    containsHandle=true;
                  mention=mention+word;
                    }
            }
            if(containsHandle) {
                String actualMsg = prepareActionMessageString(mention, sentence.toString());
                actionListMAp.put(mention, actualMsg);
            }
            }
       actionItem.setActionItems(actionListMAp);
        return actionItem;
    }


    private String prepareActionMessageString(String word,String sentence){
        String  actualSentence[]=sentence.split(" ");
        String actionMsg="";
         for(String msgWord:actualSentence){
             if(!word.equals(msgWord)){
                 actionMsg=actionMsg+msgWord+" ";
             }
         }
     return  actionMsg;
    }
}