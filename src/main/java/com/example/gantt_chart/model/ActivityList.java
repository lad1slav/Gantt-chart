package com.example.gantt_chart.model;

import com.example.gantt_chart.model.activity.Activity;
import com.example.gantt_chart.model.activity.Dates;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class ActivityList implements Convertible {

    private ArrayList<Activity> allActivities;


    public Dates calculateCriticalPath() {
        return null;
    }

    public JsonObject toJson() {
        return null;
    }
}