package com.invillia.acme.application.handlers.body;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Return of status 422 (Unprocessable Entity).
 *
 * @author Renan Gigliotti
 * @since 1.0
 */
@XmlRootElement
public class UnprocessableEntity implements Serializable {

    private String message;

    public UnprocessableEntity() {
    }

    public UnprocessableEntity(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
