import com.shankulk.model.BaseUser;
import com.shankulk.model.FreshUser;
import com.shankulk.model.Gender;
import com.shankulk.model.OptedInUser;
import com.shankulk.model.RegisteredUser;
import com.shankulk.repository.ClinicalRecordsRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * Created by sk09778 on 12/13/2018.
 */
public class ClinicalTrialTest {
    //(^\d{3}-?\d{2}-?\d{4}$|^XXX-XX-XXXX$)

    DateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testUserIsAbleToOptIn() throws ParseException {
        BaseUser user = new FreshUser("123-12-1234", Gender.MALE, sdf.parse("15/02/1991"));
        user.optInUser();
        Assert.assertTrue(ClinicalRecordsRepository.getClinicalTrialUsers().contains(user));
    }

    @Test
    public void testIfOptedInUserIsAbleToRegister() throws Exception {
        BaseUser user = new OptedInUser("111-23-9955", Gender.MALE, sdf.parse("15/02/1991"));
        user.registerUser();
        UUID MRN = user.getMRN();

        Assert.assertNotNull(MRN);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIfNonOptedUserIsNotAbleToRegister() throws Exception {
        BaseUser user = new FreshUser("123-45-6789", Gender.MALE, sdf.parse("15/02/1991"));
        user.registerUser();
        expectedException.expect(UnsupportedOperationException.class);
        expectedException.expectMessage("This user is not allowed to register");

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIfAlreadyRegisteredUserIsNotAbleToRegister() throws Exception {
        BaseUser user = new RegisteredUser("123-45-9876", Gender.MALE, sdf.parse("15/02/1991"));
        user.registerUser();

        expectedException.expect(UnsupportedOperationException.class);
        expectedException.expectMessage("This user is not allowed to register");

    }


    @Test
    public void testIfUserIsAbleToUndergoTrial() throws Exception {
        BaseUser user = new RegisteredUser("555-66-3344", Gender.FEMALE, sdf.parse("21/10/1990"));
        int initialCount = user.getTrialCount();
        user.trial();
        int countAfterTrial = user.getTrialCount();
        Assert.assertEquals(initialCount + 1, countAfterTrial);

    }

    @Test
    public void testIfRegisteredUserCanForgetHimself() throws Exception {
        BaseUser user = new RegisteredUser("555-66-3344", Gender.FEMALE, sdf.parse("21/10/1990"));
        user.unregisterUser();
        Assert.assertFalse(ClinicalRecordsRepository.getClinicalTrialUsers().contains(user));

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIfFreshBlocekUserIsNotAbleToOptIn() throws ParseException {
        BaseUser user = new FreshUser("555-66-3344", Gender.FEMALE, sdf.parse("21/10/1990"));
        user.setBlocked();
        user.optInUser();
        expectedException.expect(UnsupportedOperationException.class);
    }


    @Test(expected = UnsupportedOperationException.class)
    public void testIfOptedInBlocekUserIsNotAbleToRegister() throws ParseException {
        BaseUser user = new OptedInUser("555-66-3344", Gender.FEMALE, sdf.parse("21/10/1990"));
        user.setBlocked();
        user.optInUser();
        expectedException.expect(UnsupportedOperationException.class);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIRegisterdBlocekUserIsNotAbleToTrial() throws ParseException {
        BaseUser user = new RegisteredUser("555-66-3344", Gender.FEMALE, sdf.parse("21/10/1990"));
        user.setBlocked();
        user.optInUser();
        expectedException.expect(UnsupportedOperationException.class);
    }
}
