package org.pitest.mutationtest.verify;

import org.pitest.mutationtest.CoverageDatabase;

public interface BuildVerifier {

  public void verify(CoverageDatabase coverageDatabase);

}
