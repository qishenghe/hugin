package com.qishenghe.hugin.util;

import java.io.*;

/**
 * 序列化工具
 *
 * @author qishenghe
 * @date 6/29/22 9:32 AM
 * @change 6/29/22 9:32 AM by shenghe.qi@relxtech.com for init
 */
public class SerializableUtil {

    /**
     * 序列化对象
     * 
     * @param object object
     * @return byte array
     * @since 1.0.0
     * @author qishenghe
     * @date 6/29/22 9:35 AM
     * @change 6/29/22 9:35 AM by shenghe.qi@relxtech.com for init
     */
    public static byte[] serialize (Object object) {

        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(object);

            return byteOut.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("序列化过程异常，原因：" + e.getMessage());
        }

    }

    /**
     * 反序列化
     * 
     * @param bytes bytes
     * @param clazz 类
     * @return result
     * @since 1.0.0
     * @author qishenghe
     * @date 6/29/22 9:43 AM
     * @change 6/29/22 9:43 AM by shenghe.qi@relxtech.com for init
     */
    public static <T> T unSerialize (byte[] bytes, Class<T> clazz) {
        try {
            ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
            ObjectInputStream in = new ObjectInputStream(byteIn);
            return clazz.cast(in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("反序列化过程异常，原因：" + e.getMessage());
        }
    }

    /**
     * 反序列化
     * 
     * @param bytes bytes
     * @return object
     * @since 1.0.0
     * @author qishenghe
     * @date 6/29/22 9:42 AM
     * @change 6/29/22 9:42 AM by shenghe.qi@relxtech.com for init
     */
    public static Object unSerialize (byte[] bytes) {

        try {
            ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
            ObjectInputStream in = new ObjectInputStream(byteIn);
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("反序列化过程异常，原因：" + e.getMessage());
        }

    }

}
