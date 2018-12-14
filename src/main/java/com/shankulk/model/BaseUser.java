package com.shankulk.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by sk09778 on 12/13/2018.
 */
public abstract class BaseUser implements User {
    private final String ssn;
    private final Gender gender;
    private final Date dateOfBirth;
    private UUID MRN;
    private int trialCount;
    private boolean blocked;

    public BaseUser(String ssn, Gender gender, Date dateOfBirth) {
        this.ssn = ssn;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseUser user = (BaseUser) o;

        return ssn.equals(user.ssn);

    }

    @Override
    public int hashCode() {
        return ssn.hashCode();
    }

    public void setMRN(UUID MRN) {
        this.MRN = MRN;
    }

    public UUID getMRN() {
        return MRN;
    }


    public int getTrialCount() {
        return trialCount;
    }

    public void setTrialCount(int trialCount) {
        this.trialCount = trialCount;
    }

    public void setBlocked() {
        this.blocked = true;
    }

    public boolean isBlocked() {
        return blocked;
    }
}
