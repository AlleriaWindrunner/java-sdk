package io.github.alleriawindrunner.sdk.v3.codec.scale.reader;

import io.github.alleriawindrunner.sdk.v3.codec.scale.CompactMode;
import io.github.alleriawindrunner.sdk.v3.codec.scale.ScaleCodecReader;
import io.github.alleriawindrunner.sdk.v3.codec.scale.ScaleReader;

public class CompactUIntReader implements ScaleReader<Integer> {
    /**
     * @param rdr reader with the encoded data
     * @return integer value
     * @throws UnsupportedOperationException if the value is encoded with more than four bytes
     */
    @Override
    public Integer read(ScaleCodecReader rdr) {
        int i = rdr.readUByte();
        CompactMode mode = CompactMode.byValue((byte) (i & 0b11));
        if (mode == CompactMode.SINGLE) {
            return i >> 2;
        }
        if (mode == CompactMode.TWO) {
            return (i >> 2) + (rdr.readUByte() << 6);
        }
        if (mode == CompactMode.FOUR) {
            return (i >> 2)
                    + (rdr.readUByte() << 6)
                    + (rdr.readUByte() << (6 + 8))
                    + (rdr.readUByte() << (6 + 2 * 8));
        }
        throw new UnsupportedOperationException("Mode " + mode + " is not implemented");
    }
}