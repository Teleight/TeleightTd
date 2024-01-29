package org.teleight.td.api.updates;

import org.teleight.td.api.ApiUpdate;

@FunctionalInterface
public interface UpdateHandler<T extends ApiUpdate.Result> {

    void handle(T wrappedUpdate);

}
