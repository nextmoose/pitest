/*
 * Copyright 2010 Henry Coles
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.concurrent.Callable;

import org.junit.Before;
import org.junit.Test;
import org.pitest.mutationtest.engine.Mutant;
import org.pitest.mutationtest.engine.gregor.MutatorTestBase;

public class RemoveSynchronizedMutatorTest extends MutatorTestBase {

  @Before
  public void setupEngineToMutateOnlySynchronized() {
    createTesteeWith(RemoveSynchronizedMutator.REMOVE_SYNCHRONIZED_MUTATOR);
  }

    private static class HasSynchronized implements Callable<String> {
	public HasSynchronized(String lock) {
	    super();
	    this.lock = lock;
	}

	private final String lock;

	public void HasSynchronizedBlock() {
	    synchronized(lock) {
		System.out.println(500/100);
	    }
	}
	
	@Override
	public String call() {
	    return "Synchronized on \""+lock+"\".";
	}
    }

    @Test
    public void shouldRemoveSynchronized() throws Exception {
	final Mutant mutant = getFirstMutant(HasSynchronized.class);
	assertMutantCallableReturns(new HasSynchronized("my-lock"), mutant, "Synchronized on my-lock.");
    }
}
