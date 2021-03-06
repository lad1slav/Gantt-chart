package com.example.gantt_chart.model.activity;

import com.example.gantt_chart.exceptions.DatesException;
import com.example.gantt_chart.exceptions.DependencyException;
import com.example.gantt_chart.exceptions.IDException;
import com.example.gantt_chart.model.Convertible;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SummaryActivity extends TerminalActivity implements Convertible {
    private SubActivities subactivities = new SubActivities();

    public SummaryActivity() {

    }

    public SummaryActivity(Dates startFinal, Progress progress, IDList nextIds, ExecutorList executors, String title, ID id) {
        super(startFinal, progress, nextIds, executors, title, id);
    }

    public void addSubActivity(TerminalActivity activity) {
        subactivities.add(activity);
    }

    public void addSubActivities(SubActivities activities) {
        subactivities = activities;
    }

    public void checkDateBounds() throws DatesException {
        for (TerminalActivity activity : subactivities) {
            if (activity.getStartFinal().getStart().compareTo(getStartFinal().getStart()) < 0) {
                throw new DatesException(String.format("Sub-activity [%s] can't start earlier than its summary activity", activity.getTitle()));
            }

            if (activity.getStartFinal().getEnd().compareTo(getStartFinal().getEnd()) > 0) {
                throw new DatesException(String.format("Sub-activity [%s] can't end later than its summary activity", activity.getTitle()));
            }

            if (activity instanceof SummaryActivity) {
                ((SummaryActivity) activity).checkDateBounds();
            }
        }
    }

    @Override
    public void checkDependencies(IDList poolIds) throws DependencyException {
        super.checkDependencies(poolIds);
        subactivities.checkDependencies();
    }

    @Override
    public void checkDependenciesIdExistence() throws IDException {
        super.checkDependenciesIdExistence();
        subactivities.checkDependenciesIdExistence();
    }

    @Override
    public void checkDependenciesBounds() throws DependencyException {
        super.checkDependenciesBounds();
        subactivities.checkDependenciesBounds();
    }

    public SubActivities getSubactivities() {
        return subactivities;
    }

    @Override
    public JsonElement toJson() {
        JsonElement jsonElement = super.toJson();
        JsonElement subJson = subactivities.toJson();
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        jsonObject.add("sub-activities", subJson);
        return jsonObject;
    }
}
