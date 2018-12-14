package com.shankulk.model;

/**
 * Created by sk09778 on 12/13/2018.
 */
public interface User {

    default void optInUser() {
        throw new UnsupportedOperationException("This user is not allowed to opt in");
    }
    default void registerUser() {
        throw new UnsupportedOperationException("This user is not allowed to register");
    }
    default void unregisterUser() {
        throw new UnsupportedOperationException("This user is not allowed to unregister");
    }
    default void trial() {
        throw new UnsupportedOperationException("this user is not allowed to trial");
    }
}
