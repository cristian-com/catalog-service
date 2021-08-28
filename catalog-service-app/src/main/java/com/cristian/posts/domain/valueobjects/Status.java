package com.cristian.posts.domain.valueobjects;

import com.cristian.buildingblocks.domain.ValueObject;

public record Status(StatusValue name) implements ValueObject {

    public static Status active() {
        return new Status(StatusValue.ACTIVE);
    }

    public static Status inactive() {
        return new Status(StatusValue.INACTIVE);
    }

}
