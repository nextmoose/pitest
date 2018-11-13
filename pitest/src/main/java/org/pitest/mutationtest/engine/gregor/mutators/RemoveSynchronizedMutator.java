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

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum RemoveSynchronizedMutator implements MethodMutatorFactory {

  REMOVE_SYNCHRONIZED_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
      final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new SynchronizedBlockVisitor(this, context, methodVisitor);
  }

  @Override
  public String getGloballyUniqueId() {
    return this.getClass().getName();
  }

  @Override
  public String getName() {
    return name();
  }

}

class SynchronizedBlockVisitor extends AbstractJumpMutator {

  private static final String                     DESCRIPTION = "negated conditional";
  private static final Map<Integer, Substitution> MUTATIONS   = new HashMap<>();

  static {
    MUTATIONS.put(Opcodes.IDIV, new Substitution(Opcodes.IDIV, DESCRIPTION));
    MUTATIONS.put(Opcodes.IDIV, new Substitution(Opcodes.NOP, DESCRIPTION));
    MUTATIONS.put(Opcodes.NOP, new Substitution(Opcodes.MONITORENTER, DESCRIPTION));
    MUTATIONS.put(Opcodes.NOP, new Substitution(Opcodes.MONITOREXIT, DESCRIPTION));
  }

  SynchronizedBlockVisitor(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(factory, context, delegateMethodVisitor);
  }

  @Override
  protected Map<Integer, Substitution> getMutations() {
       if (true) {
          throw new RuntimeException("FUCK THIS");
      }
   return MUTATIONS;
  }

}
