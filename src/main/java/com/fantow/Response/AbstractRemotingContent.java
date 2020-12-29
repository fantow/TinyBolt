package com.fantow.Response;

import com.fantow.RemotingCommand.RemotingCommandType;

/**
 * 用来封装解析后的消息
 *
 */
public abstract class AbstractRemotingContent {
    /**
     * 作为一个Content应该包含什么？
     *  1.是请求还是响应
     *  2.消息内容
     */

    private int requestId;






    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
}
