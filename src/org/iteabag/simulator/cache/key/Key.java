package org.iteabag.simulator.cache.key;

import java.io.Serializable;

public interface Key extends Serializable {

    boolean equals(Object obj);

    int hashCode();
}
