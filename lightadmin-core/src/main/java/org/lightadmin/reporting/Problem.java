/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lightadmin.reporting;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Problem {

    private final String message;

    private final Throwable rootCause;

    public Problem(final String message) {
        this(message, null);
    }

    public Problem(final String message, final Throwable rootCause) {
        this.message = message;
        this.rootCause = rootCause;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getRootCause() {
        return rootCause;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("message", message).append("rootCause", rootCause).toString();
    }
}