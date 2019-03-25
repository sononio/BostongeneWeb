package com.sononio.bostongene.web.api.users;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
 * Response status container for DELETE methods.
 */
@Data
@AllArgsConstructor
public class RemoveResponseStatus {
    @Setter(AccessLevel.NONE)
    Long removedId;
}
