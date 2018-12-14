package com.shankulk.model;

import com.shankulk.repository.ClinicalRecordsRepository;

import java.util.Date;

/**
 * Created by sk09778 on 12/13/2018.
 */
public class FreshUser extends BaseUser {

    public FreshUser(String ssn, Gender gender, Date dateOfBirth) {
        super(ssn, gender, dateOfBirth);
    }

    @Override
    public void optInUser() {
        if(this.isBlocked())
            throw new UnsupportedOperationException();
        ClinicalRecordsRepository.putClinicalTrialUser(this);
    }

}
