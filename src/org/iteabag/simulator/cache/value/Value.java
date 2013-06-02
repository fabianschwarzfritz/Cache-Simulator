package org.iteabag.simulator.cache.value;

import java.io.Serializable;

public interface Value extends Serializable {

    boolean equals(Object obj);

    int hashCode();
}
