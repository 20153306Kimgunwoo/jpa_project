package com.example.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UploadFile {
   @Id
   private int seq;
   private String originFileName;
   private String uuid;
   private String memberId;
   private String secretFlag;
   private String regDate;

}
