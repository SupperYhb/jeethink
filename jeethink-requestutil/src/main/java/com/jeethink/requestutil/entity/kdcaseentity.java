package com.jeethink.requestutil.entity;

/**
 * 科达案卷接口实体
 * */
public class kdcaseentity {

    private String no; //案卷编号
    private String name; //案卷名称
    private String crimeAddress; //案发地址
    private String briefCase; //简要案情
    private String acceptanceTime; //受理时间
    private String suspectName; //涉案人员名称
    private String suspectCode; //涉案人员编号
    private String mainPoliceName; //主办民警名称
    private String mainPoliceCode; //主办民警编号
    private String assistPoliceName; //辅办民警名称
    private String assistPoliceCode; //辅办民警编号

    public void setNo(String no) {
        this.no = no;
    }

    public String getNo() {
        return no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCrimeAddress(String crimeAddress) {
        this.crimeAddress = crimeAddress;
    }

    public String getCrimeAddress() {
        return crimeAddress;
    }

    public void setBriefCase(String briefCase) {
        this.briefCase = briefCase;
    }

    public String getBriefCase() {
        return briefCase;
    }

    public void setAcceptanceTime(String acceptanceTime) {
        this.acceptanceTime = acceptanceTime;
    }

    public String getAcceptanceTime() {
        return acceptanceTime;
    }

    public void setSuspectCode(String suspectCode) {
        this.suspectCode = suspectCode;
    }

    public String getSuspectCode() {
        return suspectCode;
    }

    public void setSuspectName(String suspectName) {
        this.suspectName = suspectName;
    }

    public String getSuspectName() {
        return suspectName;
    }

    public void setMainPoliceCode(String mainPoliceCode) {
        this.mainPoliceCode = mainPoliceCode;
    }

    public String getMainPoliceCode() {
        return mainPoliceCode;
    }

    public void setMainPoliceName(String mainPoliceName) {
        this.mainPoliceName = mainPoliceName;
    }

    public String getMainPoliceName() {
        return mainPoliceName;
    }

    public void setAssistPoliceCode(String assistPoliceCode) {
        this.assistPoliceCode = assistPoliceCode;
    }

    public String getAssistPoliceCode() {
        return assistPoliceCode;
    }

    public void setAssistPoliceName(String assistPoliceName) {
        this.assistPoliceName = assistPoliceName;
    }

    public String getAssistPoliceName() {
        return assistPoliceName;
    }
}
