package common.exception;

import common.base.MsgCode;

public class TccException extends RuntimeException {
    private MsgCode msgCode;


    public TccException(MsgCode msgCode) {
        this.msgCode = msgCode;
    }

    public MsgCode getMsgCode() {
        return msgCode;
    }
}
