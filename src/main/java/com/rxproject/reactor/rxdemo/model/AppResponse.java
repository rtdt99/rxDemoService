package com.rxproject.reactor.rxdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;


public class AppResponse {
    @JsonProperty("message")
    String message;
    @JsonProperty("quote")
    String quote;

    public AppResponse() {
    }

    public AppResponse(Message message,Quote quote) {
        this.message = message.getMessage();
        this.quote = quote.getQuote();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message.getMessage();
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote.getQuote();
    }
}
