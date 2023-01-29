/**
 * Copyright 2014-2020 [fisco-dev]
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.alleriawindrunner.sdk.v3.utils;

import java.util.Arrays;
import java.util.List;

/** Utility functions for working with Collections. */
public class Collection {
    private static final String[] EMPTY_STRING_ARRAY = {};

    public static String[] tail(String[] args) {
        if (args.length == 0) {
            return EMPTY_STRING_ARRAY;
        } else {
            return Arrays.copyOfRange(args, 1, args.length);
        }
    }

    @SafeVarargs
    public static <T> T[] create(T... args) {
        return args;
    }

    public static <T> String join(List<T> list, String separator, Function<T, String> function) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append(function.apply(list.get(i)).trim());
            if (i + 1 < list.size()) {
                result.append(separator);
            }
        }
        return result.toString();
    }

    public static String join(List<String> list, String separator) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append(list.get(i).trim());
            if (i + 1 < list.size()) {
                result.append(separator);
            }
        }
        return result.toString();
    }

    public interface Function<R, S> {
        S apply(R r);
    }
}