package io.github.alleriawindrunner.sdk.v3.codec.scale.reader;

import io.github.alleriawindrunner.sdk.v3.codec.scale.ScaleCodecReader;
import io.github.alleriawindrunner.sdk.v3.codec.scale.ScaleReader;

public class UByteReader implements ScaleReader<Integer> {
    @Override
    public Integer read(ScaleCodecReader rdr) {
        byte x = rdr.readByte();
        if (x < 0) {
            return 256 + (int) x;
        }
        return (int) x;
    }
}
