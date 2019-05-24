package io.simpolor.event.listener;

import io.simpolor.event.domain.StudentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StudentEventListener {

    @EventListener
    public void studentEventListener(StudentEvent event){
        log.info("StudentEvent name : {}, age : {}", event.getName(), event.getAge());
    }
}
