package com.sifasystems.model;

public class ScanLogModel {

    private int logId;
    private String userId;
    private String coordinatorId;
    private String deviceId;
    private int status;

    public ScanLogModel(){

    }


    public ScanLogModel(int logId, String userId, String coordinatorId, String deviceId, int status) {
        this.logId = logId;
        this.userId = userId;
        this.coordinatorId = coordinatorId;
        this.deviceId = deviceId;
        this.status = status;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(String coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
