package io.github.alleriawindrunner.sdk.v3.codec.datatypes.generated;

import java.math.BigInteger;
import io.github.alleriawindrunner.sdk.v3.codec.datatypes.Int;

/**
 * Auto generated code.
 *
 * <p><strong>Do not modifiy!</strong>
 *
 * <p>Please use AbiTypesGenerator in the <a
 * href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 */
public class Int248 extends Int {
    public static final Int248 DEFAULT = new Int248(BigInteger.ZERO);

    public Int248(BigInteger value) {
        super(248, value);
    }

    public Int248(long value) {
        this(BigInteger.valueOf(value));
    }
}
