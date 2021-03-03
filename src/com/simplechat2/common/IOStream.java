package com.simplechat2.common;

import java.io.*;

public class IOStream {
    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;
    private static IOStream stream;

    private IOStream(InputStream in, OutputStream out){
        try{
            ois = new ObjectInputStream(in);
            oos = new ObjectOutputStream(out);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static IOStream createIO(InputStream in, OutputStream out){
        if(stream == null){
            stream = new IOStream(in,out);
        }
        return stream;
    }

    public void sendObject(Object o){
        try {
            oos.writeObject(o);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Object ReceiveObject(){
        Object ret=null;
        try {
            ret=ois.readObject();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e2){
            e2.printStackTrace();
        }
        return ret;
    }

    public void closeStream(){
        try{
            ois.close();
            oos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
