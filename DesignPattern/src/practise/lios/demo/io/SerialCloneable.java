package practise.lios.demo.io;

import java.io.*;

/**
 * @author liaiguang
 * @date 2020/7/25
 */
public class SerialCloneable implements Cloneable, Serializable {
    @Override
    public Object clone() throws CloneNotSupportedException{
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            try (ObjectOutputStream out = new ObjectOutputStream(bout)) {
                out.writeObject(this);
            }

            try (ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray())) {
                ObjectInputStream inputStream = new ObjectInputStream(bin);
                return inputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            CloneNotSupportedException exception = new CloneNotSupportedException();
            exception.initCause(e);

            throw exception;
        }
    }
}