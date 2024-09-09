
package com.bkartisan.be.ExceptionHandler;

import com.bkartisan.be.Constant.ErrorMessage;

public class UserNameExistsException extends RuntimeException {
    public UserNameExistsException() {
        super(ErrorMessage.USER_ALREADY_EXIST);
    }
}