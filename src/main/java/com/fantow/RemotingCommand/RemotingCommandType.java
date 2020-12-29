package com.fantow.RemotingCommand;

public enum RemotingCommandType {
    REMOTING_REQUEST((byte)0),
    REMOTING_RESPONSE((byte)1);

    private byte value;

    RemotingCommandType(byte value){
        this.value = value;
    }

    public static RemotingCommandType valueOf(byte code){
        for(RemotingCommandType type : RemotingCommandType.values()){
            if(type.value == code){
                return type;
            }
        }
        return null;
    }
}
