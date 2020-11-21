package com.jeethink.requestutil.entity.useface;

public class verificationFace {
    private String lokerUuid;
    private String faceImageBase64;

    public String getLokerUuid() {
        return lokerUuid;
    }

    public void setLokerUuid(String lokerUuid) {
        this.lokerUuid = lokerUuid;
    }

    public String getFaceImageBase64() {
        return faceImageBase64;
    }

    public void setFaceImageBase64(String faceImageBase64) {
        this.faceImageBase64 = faceImageBase64;
    }
}
