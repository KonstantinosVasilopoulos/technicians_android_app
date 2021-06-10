package gr.aueb.team14.ui;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import gr.aueb.team14.R;
import gr.aueb.team14.dao.TechnicianDAO;
import gr.aueb.team14.domain.Job;
import gr.aueb.team14.domain.Specialty;
import gr.aueb.team14.domain.Technician;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * The purpose of this test class is to determine whether the activity responsible for
 * registering new technicians works as intended.
 */
public class TechnicianRegistrationTest {
    private String address;
    private String from;
    private String to;
    private Job job;
    private String username;
    private String password;
    private String phone;


    @Rule
    public ActivityScenarioRule<TechnicianRegistration> activityRule =
            new ActivityScenarioRule<>(TechnicianRegistration.class);

    @Before
    public void setUp() throws Exception {
        address = "Kati 3";
        from = "10/5/2021";
        to = "11/5/2021";
        job = new Job("Gardening", 50.0);
        username = "Jason";
        password = "testing321";
        phone = "6999999999";
    }

    /**
     * Tests the creation of new address fragments.
     * An address fragment handles user input for the creation of new addresses.
     */
    @Test
    public void createNewAddressFieldTest() {
        // Click the button to create a new address entry
        onView(withId(R.id.addAddressBtn)).perform(click());

        // Check that the new address entry has been created
        String tagValue = "addressFragment1";
        try {
            onView(withTagValue(is((Object) tagValue)));
        } catch (NoMatchingViewException e) {
            fail("Address fragment isn't created");
        }
    }

    /**
     * Register a new technician, check whether the technician was saved in the DAO and check the
     * values of the new technician instance.
     */
    @Test
    public void registerNewTechnicianTest() {
        // Type the new technician's data
        onView(withId(R.id.gardenerBox)).perform(scrollTo(), click());
        onView(withId(R.id.addressField)).perform(typeText(address), closeSoftKeyboard());
        onView(withId(R.id.fromField)).perform(typeText(from), closeSoftKeyboard());
        onView(withId(R.id.toField)).perform(typeText(to), closeSoftKeyboard());
        onView(withId(R.id.jobNameField)).perform(typeText(job.getName()), closeSoftKeyboard());
        onView(withId(R.id.jobPriceField)).perform(typeText(String.valueOf(job.getPrice())), closeSoftKeyboard());

        // Choose SMS on the spinner
        onView(withId(R.id.TypeSpinner)).perform(click());
        onView(withText("SMS")).perform(click());
        onView(withId(R.id.ValueField)).perform(typeText(phone), closeSoftKeyboard());

        // Type the username and the password
        onView(withId(R.id.usernameField)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.password1Field)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.password2Field)).perform(typeText(password), closeSoftKeyboard());

        // Click the button to finish the registration
        onView(withId(R.id.technicianRegistrationBtn)).perform(click());

        // Check whether the registration was successful
        Technician technician = TechnicianDAO.getInstance().find(username);
        assertNotNull(technician);
        assertEquals(Specialty.Gardener, technician.getSpecialties().get(0));
        assertEquals(address, technician.getAddresses().get(0));
        assertEquals(1, technician.getJobs().size());
    }
}