package com.example.springintroexercise.data.models.binding.user;

import com.example.springintroexercise.validations.applicant.CVLInkValidation;
import com.example.springintroexercise.validations.applicant.NameValidation;
import com.example.springintroexercise.validations.applicant.PhoneNumberValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantModel
{
    @NameValidation
    private String firstName;

    @NameValidation
    private String lastName;

    @CVLInkValidation
    private String linkToCV;

    @PhoneNumberValidation
    private String phoneNumber;

    private String requestedPosition;
}
