package com.example.DoctorApp.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpOutput {

    boolean signUpStatus;
    String signUpStatusMessage;
}
