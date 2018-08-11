package ckpoint.hsim.sms.model;

import java.util.ArrayList;
import java.util.List;

public class SmsText {

    private String message ;
    private int byteSize ;

    public SmsText(String message){
       this.message = message;
       this.byteSize = this.message.getBytes().length;
    }

    public List<String> getSmsMessages(){
        List<String> strs = new ArrayList<>();
        if(this.byteSize < 140 ){
            strs.add(this.message);
            return strs;
        }
        int arrayLength = (int) Math.ceil(this.byteSize / 140.0);
        int strSize = this.message.length() / arrayLength;

        for ( int i =0 ;i < arrayLength ; i ++){
            if( i == arrayLength -1){
                strs.add(this.message.substring(i * strSize));
            }
            else {
                strs.add(this.message.substring(i * strSize, (i + 1) * strSize));
            }
        }

        return strs;

    }
}
