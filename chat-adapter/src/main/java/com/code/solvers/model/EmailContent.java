package com.code.solvers.model;

import java.util.List;

public class EmailContent {

    private String subject = "MoM - dailystandup";

    private String participants;

    private List<String> summaryPoints;

    private List<String> actionItems;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public List<String> getSummaryPoints() {
        return summaryPoints;
    }

    public void setSummaryPoints(List<String> summaryPoints) {
        this.summaryPoints = summaryPoints;
    }

    public List<String> getActionItems() {
        return actionItems;
    }

    public void setActionItems(List<String> actionItems) {
        this.actionItems = actionItems;
    }


}
