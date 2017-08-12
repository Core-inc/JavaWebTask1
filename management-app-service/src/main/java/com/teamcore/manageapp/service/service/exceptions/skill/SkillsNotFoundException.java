package com.teamcore.manageapp.service.service.exceptions.skill;

public class SkillsNotFoundException extends SkillServiceException {

   public SkillsNotFoundException() {
      this.message = "Skills not available";
   }
}
