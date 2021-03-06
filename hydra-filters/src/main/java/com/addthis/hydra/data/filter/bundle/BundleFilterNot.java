/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.addthis.hydra.data.filter.bundle;

import com.addthis.bundle.core.Bundle;
import com.addthis.bundle.core.BundleField;
import com.addthis.codec.Codec;

/**
 * This {@link BundleFilter BundleFilter} <span class="hydra-summary">returns true if a specified field is not set</span>.
 * <p/>
 * <p>Example:</p>
 * <pre>
 *   {op:"not", field: "USERNAME"},
 * </pre>
 *
 * @user-reference
 * @hydra-name not
 */
public class BundleFilterNot extends BundleFilter {

    public BundleFilterNot setField(String field) {
        this.field = field;
        return this;
    }

    /**
     * The field to test. This field is required.
     */
    @Codec.Set(codable = true, required = true)
    private String field;

    private String fields[];

    @Override
    public void initialize() {
        fields = new String[]{field};
    }

    @Override
    public boolean filterExec(Bundle bundle) {
        BundleField bound[] = getBindings(bundle, fields);
        return bundle.getValue(bound[0]) == null;
    }
}
