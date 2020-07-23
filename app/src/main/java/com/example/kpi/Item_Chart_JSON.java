package com.example.kpi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item_Chart_JSON {
    @SerializedName("KPI_RECORD_ID")
    @Expose
    public String kPIRECORDID;
    @SerializedName("KPI_DATE_NAME")
    @Expose
    public String kPIDATENAME;
    @SerializedName("KPI_DATE")
    @Expose
    public String kPIDATE;
    @SerializedName("SERIES_01_NAME")
    @Expose
    public String sERIES01NAME;
    @SerializedName("SERIES_01_VALUE")
    @Expose
    public float sERIES01VALUE;
    @SerializedName("SERIES_02_NAME")
    @Expose
    public String sERIES02NAME;
    @SerializedName("SERIES_02_VALUE")
    @Expose
    public String sERIES02VALUE;
    @SerializedName("CHART_COLOR")
    @Expose
    public String cHARTCOLOR;
    @SerializedName("KPI_DATE_LABEL")
    @Expose
    public String kPIDATELABEL;
    @SerializedName("KPI_NAME")
    @Expose
    public String kPINAME;
    @SerializedName("TARGET_STATUS")
    @Expose
    public String tARGETSTATUS;
    @SerializedName("TREND_STATUS")
    @Expose
    public String tRENDSTATUS;
    @SerializedName("LEVEL")
    @Expose
    public String lEVEL;
    @SerializedName("LEVEL_NAME")
    @Expose
    public String lEVELNAME;
    @SerializedName("TARGET_VALUE")
    @Expose
    public String tARGETVALUE;

}
