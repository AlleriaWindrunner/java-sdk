package io.github.alleriawindrunner.sdk.v3.contract.precompiled.sysconfig;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import io.github.alleriawindrunner.sdk.v3.client.Client;
import io.github.alleriawindrunner.sdk.v3.codec.datatypes.Function;
import io.github.alleriawindrunner.sdk.v3.codec.datatypes.Type;
import io.github.alleriawindrunner.sdk.v3.codec.datatypes.TypeReference;
import io.github.alleriawindrunner.sdk.v3.codec.datatypes.Utf8String;
import io.github.alleriawindrunner.sdk.v3.codec.datatypes.generated.Int32;
import io.github.alleriawindrunner.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple1;
import io.github.alleriawindrunner.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple2;
import io.github.alleriawindrunner.sdk.v3.contract.Contract;
import io.github.alleriawindrunner.sdk.v3.crypto.CryptoSuite;
import io.github.alleriawindrunner.sdk.v3.crypto.keypair.CryptoKeyPair;
import io.github.alleriawindrunner.sdk.v3.model.CryptoType;
import io.github.alleriawindrunner.sdk.v3.model.TransactionReceipt;
import io.github.alleriawindrunner.sdk.v3.model.callback.TransactionCallback;
import io.github.alleriawindrunner.sdk.v3.utils.StringUtils;

@SuppressWarnings("unchecked")
public class SystemConfigPrecompiled extends Contract {
    public static final String[] BINARY_ARRAY = {};

    public static final String BINARY = StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {
        "[{\"constant\":false,\"inputs\":[{\"name\":\"key\",\"type\":\"string\"},{\"name\":\"value\",\"type\":\"string\"}],\"name\":\"setValueByKey\",\"outputs\":[{\"name\":\"\",\"type\":\"int32\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"
    };

    public static final String ABI = StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_SETVALUEBYKEY = "setValueByKey";

    protected SystemConfigPrecompiled(
            String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt setValueByKey(String key, String value) {
        final Function function =
                new Function(
                        FUNC_SETVALUEBYKEY,
                        Arrays.<Type>asList(new Utf8String(key), new Utf8String(value)),
                        Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void setValueByKey(String key, String value, TransactionCallback callback) {
        final Function function =
                new Function(
                        FUNC_SETVALUEBYKEY,
                        Arrays.<Type>asList(new Utf8String(key), new Utf8String(value)),
                        Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSetValueByKey(String key, String value) {
        final Function function =
                new Function(
                        FUNC_SETVALUEBYKEY,
                        Arrays.<Type>asList(new Utf8String(key), new Utf8String(value)),
                        Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, String> getSetValueByKeyInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function =
                new Function(
                        FUNC_SETVALUEBYKEY,
                        Arrays.<Type>asList(),
                        Arrays.<TypeReference<?>>asList(
                                new TypeReference<Utf8String>() {},
                                new TypeReference<Utf8String>() {}));
        List<Type> results =
                this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, String>(
                (String) results.get(0).getValue(), (String) results.get(1).getValue());
    }

    public Tuple1<BigInteger> getSetValueByKeyOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function =
                new Function(
                        FUNC_SETVALUEBYKEY,
                        Arrays.<Type>asList(),
                        Arrays.<TypeReference<?>>asList(new TypeReference<Int32>() {}));
        List<Type> results =
                this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>((BigInteger) results.get(0).getValue());
    }

    public static SystemConfigPrecompiled load(
            String contractAddress, Client client, CryptoKeyPair credential) {
        return new SystemConfigPrecompiled(contractAddress, client, credential);
    }
}
