package mk.com.codefactory.advanced;


import javax.persistence.Embeddable;

import mk.com.codefactory.basic.User;

import java.time.LocalDateTime;

@Embeddable
public class Comment {
    private User author;
    private LocalDateTime createdOn;
    private String message;
}
