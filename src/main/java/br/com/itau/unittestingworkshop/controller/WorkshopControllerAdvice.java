package br.com.itau.unittestingworkshop.controller;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

@ControllerAdvice
public class WorkshopControllerAdvice {

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<Object> handleConversionFailedException(ConversionFailedException ex, WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Request Parameter inválido");
        body.put("request", StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(request.getParameterNames(), Spliterator.ORDERED),
                false).map(key -> {
            Map<String, Object> parameter = new LinkedHashMap<>();
            parameter.put("parameter", key);
            parameter.put("vallue", request.getParameter(key));
            return parameter;
        }).toArray());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
