package decode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.nio.ByteBuffer;

@Getter
@Setter
@AllArgsConstructor
class UserInfo implements Serializable {

    private String userName;
    private int userId;


    byte[] code() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] bytes = this.userName.getBytes();
        buffer.putInt(bytes.length);
        buffer.put(bytes);
        buffer.putInt(this.userId);
        buffer.flip();
        return new byte[buffer.remaining()];
    }



}


