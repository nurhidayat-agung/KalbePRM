package callplan.prm.kalbe.kalbecallplanmobile.model;

import com.activeandroid.serializer.TypeSerializer;

/**
 * Created by rhezaTesar on 9/28/2016.
 * migrated to Android room by ghqp 12/24/2019
 */

public class ByteArraySerializer extends TypeSerializer {

    @Override
    public Class<?> getDeserializedType() {
        return byte[].class;
    }

    @Override
    public Class<?> getSerializedType() {
        return String.class;
    }

    @Override
    public Object serialize(Object o) {
        if ( o != null ) {
            return  new String((byte[]) o);
        }
        return null;
    }

    @Override
    public Object deserialize(Object o) {
        if ( o != null ) {
            String str = (String) o;
            return str.getBytes();
        }
        return null;
    }
}

