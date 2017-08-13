package com.teamcore.manageapp.web.controllers;


import com.teamcore.manageapp.service.service.ProjectService;
import com.teamcore.manageapp.service.service.utils.EmailSender;
import com.teamcore.manageapp.service.service.utils.ToWhomInvite;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/addDeveloper")
public class DevInviteController {
    private ProjectService projectService;

    public DevInviteController() {
    }

    @PostMapping
    public ResponseEntity<?> sendInvite(@RequestBody ToWhomInvite toWhomInvite, UriComponentsBuilder ucb) {
       // Project savedProject = projectService.save(project);
        final String hostMail = "smtp.gmail.com";
        final String fromEmail = "epamservice@gmail.com";
        final String fromFullname = "epamservice";
        final String emailUser = toWhomInvite.getEmail();
        final String fullnameUser = toWhomInvite.getName();
        final String username = "epamservice@gmail.com";
        final String password = "labs1234567";
        final String subject = "Invite";
        int tokenInt = (int)(Math.random()*100000);
        String token = ""+tokenInt;
        System.out.println("confNum = "+token);
        String ref = "http://localhost:8080/devInvite?inviteToken="+token;
        // String cutRef = "/confirmation"+confNum;
        final String body = "Dear, " + toWhomInvite.getName() + "!" + "\n" + "You will be added to developer's database.\n" +
                "Please, confirm this by clicking the following link:\n"+ref;

        EmailSender emailSender = new EmailSender(hostMail,fromEmail,fromFullname,emailUser,fullnameUser,username,password,subject,body);
        emailSender.sendMail();

        return (ResponseEntity<?>) ResponseEntity.ok();
    }

}

