package com.cristian.buildingblocks.domain;

public interface Identifier extends Comparable<Identifier>, ValueObject {
    String toString();
}
