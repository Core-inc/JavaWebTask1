package com.teamcore.manageapp.service.services.skill.exceptions;

public class SkillsNotFoundException extends SkillServiceException {

   public SkillsNotFoundException() {
      this.message = "Skills not available";
   }
}
