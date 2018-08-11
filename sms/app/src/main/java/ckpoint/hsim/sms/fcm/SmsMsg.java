package ckpoint.hsim.sms.fcm;

public class SmsMsg {
    private Long id;
    private String message ;
    private String sendNumber;
    private String recvNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendNumber() {
        return sendNumber;
    }

    public void setSendNumber(String sendNumber) {
        this.sendNumber = sendNumber;
    }

    public String getRecvNumber() {
        return recvNumber;
    }

    public void setRecvNumber(String recvNumber) {
        this.recvNumber = recvNumber;
    }
}
