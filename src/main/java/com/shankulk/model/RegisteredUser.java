package com.shankulk.model;

import com.shankulk.repository.ClinicalRecordsRepository;

import java.util.Date;

/**
 * Created by sk09778 on 12/13/2018.
 */
public class RegisteredUser extends BaseUser {
    public RegisteredUser(String ssn, Gender gender, Date dateOfBirth) {
        super(ssn, gender, dateOfBirth);
    }

    @Override
    public void unregisterUser() {
        ClinicalRecordsRepository.removeUser(this);
    }

    @Override
    public void trial() {
        if(this.isBlocked())
            throw new UnsupportedOperationException();
        this.setTrialCount(this.getTrialCount() + 1);
        ClinicalRecordsRepository.putClinicalTrialUser(this);
    }
}
