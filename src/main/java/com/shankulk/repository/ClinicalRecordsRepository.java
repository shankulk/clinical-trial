package com.shankulk.repository;

import com.shankulk.model.BaseUser;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sk09778 on 12/13/2018.
 */
public class ClinicalRecordsRepository {
    private static Set<BaseUser> clinicalTrialUsers = new HashSet<BaseUser>();

    public static BaseUser putClinicalTrialUser(BaseUser user) {
        clinicalTrialUsers.add(user);
        return user;
    }

    public static Set<BaseUser> getClinicalTrialUsers() {
        return clinicalTrialUsers;
    }

    public static void removeUser(BaseUser user) {
        clinicalTrialUsers.remove(user);
    }
}
