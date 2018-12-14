package com.shankulk.model;

import com.shankulk.repository.ClinicalRecordsRepository;

import java.util.Date;
import java.util.UUID;

/**
 * Created by sk09778 on 12/13/2018.
 */
public class OptedInUser extends BaseUser {
    public OptedInUser(String ssn, Gender gender, Date dateOfBirth) {
        super(ssn, gender, dateOfBirth);
    }


    @Override
    public void registerUser() {
        if(this.isBlocked())
            throw new UnsupportedOperationException();
        this.setMRN(UUID.randomUUID());
        ClinicalRecordsRepository.putClinicalTrialUser(this);
    }

}
