package com.fantow.Remoting;

/**
 * AbstractLifeCycle用来表示一个Remoting实例的运行状态
 * 1.start 正在运行
 * 2.terminated 已经关闭
 *
 * 3.start 正处于启动状态
 * 4.stoping 正在关闭
 */
public class AbstractLifeCycle implements LifeCycle {


    @Override
    public void start() {

    }

    @Override
    public void terminated() {

    }

    @Override
    public void starting() {

    }

    @Override
    public void stoping() {

    }
}
