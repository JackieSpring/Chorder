package com.jackiespring.chorder.service;

import kotlin.collections.ArrayDeque;

public class IFCService {

    public static class IFCMessage {
        private Object data;
        private Object src;

        public IFCMessage ( Object _data ) {
            this( _data, null );
        }

        public IFCMessage( Object _data, Object _src ) {
            data = _data;
            src = _src;
        }

        public Object getData() {
            return data;
        }

        public Object getSrc() {
            return src;
        }
    }

    private static IFCService instance;

    public static IFCService getInstance() {
        if ( instance == null )
            instance = new IFCService();

        return instance;
    }



    private ArrayDeque<IFCMessage> msgQueue = new ArrayDeque<IFCMessage>();
    private volatile boolean flagAccess = false;

    private void lock() throws InterruptedException {
        if ( flagAccess ) wait();
        flagAccess = true;
    }
    private void unlock() {
        flagAccess = false;
        notify();
    }

    public synchronized void putMsg( IFCMessage msg ) throws InterruptedException {
        lock();
        msgQueue.add( msg );
        unlock();
    }

    public synchronized IFCMessage getMsg(  ) throws InterruptedException {
        lock();
        IFCMessage ret = msgQueue.removeFirst();
        unlock();
        return ret;
    }

    public synchronized int size() {
        try {
            lock();
        } catch ( InterruptedException ex ) {
            Thread.currentThread().interrupt();
        }
        int ret = msgQueue.size();
        unlock();

        return ret;
    }



}
