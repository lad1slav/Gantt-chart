package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class Progress implements Convertible {
    private int percents;

    public Progress(int percents) {
        this.percents = percents;
    }

    public int getPercents() {
        return percents;
    }

    public void setPercents(int percents) {
        this.percents = percents;
    }
    // TODO: 01.06.2019 0-percents-100

    public JsonElement toJson() {
        return new JsonPrimitive(percents);
    }
}
