package com.teamcore.manageapp.service.service.exceptions.skill;

public class SkillAddException extends SkillServiceException {

   public SkillAddException() {
      this.message = "Failed to add new skill";
   }
}
