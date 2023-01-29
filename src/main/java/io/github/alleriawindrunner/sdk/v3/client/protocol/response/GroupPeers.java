/*
 * Copyright 2014-2020  [fisco-dev]
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package io.github.alleriawindrunner.sdk.v3.client.protocol.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import io.github.alleriawindrunner.sdk.v3.model.JsonRpcResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupPeers extends JsonRpcResponse<List<String>> {
    public List<String> getGroupPeers() {
        return getResult();
    }
}