package io.github.alleriawindrunner.sdk.v3.codec.scale.writer;

import java.io.IOException;
import java.math.BigInteger;

import io.github.alleriawindrunner.sdk.v3.codec.scale.ScaleCodecWriter;
import io.github.alleriawindrunner.sdk.v3.codec.scale.ScaleWriter;
import io.github.alleriawindrunner.sdk.v3.codec.scale.CompactMode;

public class CompactBigIntWriter implements ScaleWriter<BigInteger> {
    private static final CompactULongWriter LONG_WRITER = new CompactULongWriter();

    @Override
    public void write(ScaleCodecWriter wrt, BigInteger value) throws IOException {
        CompactMode mode = CompactMode.forNumber(value);

        byte[] data = value.toByteArray();
        int length = data.length;
        int pos = data.length - 1;
        int limit = 0;

        if (mode != CompactMode.BIGINT) {
            LONG_WRITER.write(wrt, value.longValue());
            return;
        }

        // skip the first byte if it's 0
        if (data[0] == 0x00) {
            length--;
            limit++;
        }

        wrt.directWrite(((length - 4) << 2) + mode.getValue());
        while (pos >= limit) {
            wrt.directWrite(data[pos]);
            pos--;
        }
    }
}
