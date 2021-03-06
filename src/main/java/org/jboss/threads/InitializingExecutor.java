/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2017 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.threads;

class InitializingExecutor implements DirectExecutor {

    private final Runnable initializer;
    private final DirectExecutor delegate;

    InitializingExecutor(final Runnable initializer, final DirectExecutor delegate) {
        this.initializer = initializer;
        this.delegate = delegate;
    }

    public void execute(final Runnable command) {
        initializer.run();
        delegate.execute(command);
    }

    public String toString() {
        return String.format("%s (init task=%s) -> %s", super.toString(), initializer, delegate);
    }
}
