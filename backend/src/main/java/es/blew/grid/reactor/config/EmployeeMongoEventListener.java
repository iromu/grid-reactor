package es.blew.grid.reactor.config;

import es.blew.grid.reactor.integration.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static es.blew.grid.reactor.config.WebSocketConfiguration.MESSAGE_PREFIX;

public class EmployeeMongoEventListener extends AbstractMongoEventListener<Employee> {
    private final SimpMessagingTemplate websocket;


    @Autowired
    public EmployeeMongoEventListener(SimpMessagingTemplate websocket) {
        this.websocket = websocket;
    }

    @Override
    public void onAfterSave(AfterSaveEvent<Employee> event) {
        super.onAfterSave(event);
        this.websocket.convertAndSend(
                MESSAGE_PREFIX + "/updateEmployee", event.getSource());
    }

    @Override
    public void onAfterLoad(AfterLoadEvent<Employee> event) {
        super.onAfterLoad(event);
        this.websocket.convertAndSend(
                MESSAGE_PREFIX + "/newEmployee", event.getSource());
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<Employee> event) {
        super.onAfterDelete(event);
        this.websocket.convertAndSend(
                MESSAGE_PREFIX + "/deleteEmployee", event.getSource());
    }

    @Override
    public void onAfterConvert(AfterConvertEvent<Employee> event) {
        super.onAfterConvert(event);
        this.websocket.convertAndSend(
                MESSAGE_PREFIX + "/newEmployee", event.getSource());
    }


}