package com.example.kpi;

public class ExampleItem {
    private String Kpi_name;
    private String level;
    private String upd;
    private String level_name;
    private String period;
    private String target;
    private String value;
    private String owner;
    private String Trend_Status;
    private String Target_Status;
    private String Updated;
    private String Target_value;
    private String Target_Result;
    private String Target_details;
    private String Trend_value;
    private String Trend_details;
    private String Trend_Result;
    private String updatedby;
    private String DataOwner;
    private String SignOff_ops;
    private String SignOff_exec;
    private String Action_status;
    private String report_id;

    public ExampleItem() {

    }

    public String getTarget_Status() {
        return Target_Status;
    }

    public void setTarget_Status(String target_Status) {
        Target_Status = target_Status;
    }

    public ExampleItem(String kpi_name, String level, String level_name, String period, String target, String value, String owner, String upd, String trend_Status, String target_Status,String updated,String target_value,String target_Result,String target_details,String trend_value,String trend_details, String updatedby, String signOff_ops, String signOff_exec,String trend_Result,String action_status,String report_id) {
        Kpi_name = kpi_name;
        this.level = level;
        this.upd = upd;
        this.level_name = level_name;
        this.period = period;
        this.target = target;
        this.value = value;
        this.owner = owner;
        Trend_Status = trend_Status;
        Target_Status = target_Status;
        Updated = updated;
        Target_value = target_value;
        Target_Result = target_Result;
        Target_details = target_details;
        Trend_value = trend_value;
        Trend_details = trend_details;
        this.updatedby = updatedby;
        SignOff_ops = signOff_ops;
        SignOff_exec = signOff_exec;
        Trend_Result = trend_Result;
        Action_status = action_status;
        this.report_id = report_id;
    }

    public String getReport_id() {
        return report_id;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

    public String getKpi_name() {
        return Kpi_name;
    }

    public void setKpi_name(String kpi_name) {
        Kpi_name = kpi_name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUpd() {
        return upd;
    }

    public void setUpd(String upd) {
        this.upd = upd;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getTrend_Status() {
        return Trend_Status;
    }

    public void setTrend_Status(String trend_Status) {
        Trend_Status = trend_Status;
    }

    public String getUpdated() {
        return Updated;
    }

    public void setUpdated(String updated) {
        Updated = updated;
    }

    public String getTarget_value() {
        return Target_value;
    }

    public void setTarget_value(String target_value) {
        Target_value = target_value;
    }

    public String getTarget_Result() {
        return Target_Result;
    }

    public void setTarget_Result(String target_Result) {
        Target_Result = target_Result;
    }

    public String getTarget_details() {
        return Target_details;
    }

    public void setTarget_details(String target_details) {
        Target_details = target_details;
    }

    public String getTrend_value() {
        return Trend_value;
    }

    public void setTrend_value(String trend_value) {
        Trend_value = trend_value;
    }

    public String getTrend_details() {
        return Trend_details;
    }

    public void setTrend_details(String trend_details) {
        Trend_details = trend_details;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public String getDataOwner() {
        return DataOwner;
    }

    public void setDataOwner(String dataOwner) {
        DataOwner = dataOwner;
    }

    public String getSignOff_ops() {
        return SignOff_ops;
    }

    public void setSignOff_ops(String signOff_ops) {
        SignOff_ops = signOff_ops;
    }

    public String getSignOff_exec() {
        return SignOff_exec;
    }

    public void setSignOff_exec(String signOff_exec) {
        SignOff_exec = signOff_exec;
    }

    public String getTrend_Result() {
        return Trend_Result;
    }

    public void setTrend_Result(String trend_Result) {
        Trend_Result = trend_Result;
    }

    public String getAction_status() {
        return Action_status;
    }

    public void setAction_status(String action_status) {
        Action_status = action_status;
    }
}
