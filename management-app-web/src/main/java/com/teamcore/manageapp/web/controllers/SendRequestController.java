package com.teamcore.manageapp.web.controllers;


import com.teamcore.manageapp.service.service.UserService;
import com.teamcore.manageapp.service.service.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/addDeveloper")
public class SendRequestController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public SendRequestController() {
    }

    @PostMapping
    public ResponseEntity<?> sendRequest(UriComponentsBuilder ucb) {
        // Project savedProject = projectService.save(project);
        final String hostMail = "smtp.gmail.com";
        final String fromEmail = "epamservice@gmail.com";
        final String fromFullname = "epamservice";
        final String emailUser = userService.getById(0L).getEmail();
        final String fullnameUser = userService.getById(0L).getName();
        final String username = "epamservice@gmail.com";
        final String password = "labs1234567";
        final String subject = "New request";

        // String cutRef = "/confirmation"+confNum;
        final String body = "Dear, " + userService.getById(0L).getName() + "!" + "\n" + "There is one new request.\n" +
                "Please, see it in the ProjectRequest database\n";

        EmailSender emailSender = new EmailSender(hostMail,fromEmail,fromFullname,emailUser,fullnameUser,username,password,subject,body);
        emailSender.sendMail();

        return (ResponseEntity<?>) ResponseEntity.ok();
    }

}

